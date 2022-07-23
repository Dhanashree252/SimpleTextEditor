package SimpleTextEditor;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    //  create window for notepad
    JFrame window;
    //  create text Area
    JTextArea textArea;
    //   create scroll bar
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    //    create Menubar
    JMenuBar menuBar;
    //    add to menubar
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    //  add items to the menu elements
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    //    add items to the edit menu
    JMenuItem iCut, iCopy, iPaste, iUndo, iRedo;
    //  add items to the format elements
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont, menuFontSize;
    //    add items to the color element
    JMenuItem iColor1, iColor2, iColor3;
    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Color color = new Function_Color(this);
    UndoManager um = new UndoManager();
    Function_Edit edit = new Function_Edit(this);
    KeyHandler kHandler = new KeyHandler(this);

    public static void main(String[] args) {
        new GUI();
    }
    public GUI(){
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createColorMenu();
        format.SelectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        color.changeColor("White");
        window.setVisible(true);
    }

    //  function to create window for the notepad
    public void createWindow(){
        window = new JFrame("Notepad");
//      600 to 800 window size
        window.setSize(800, 600);
//      To Close window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //    Function to create textArea inside window
    public void createTextArea(){
        textArea = new JTextArea();
        textArea.setFont(format.arial);

        textArea.addKeyListener(kHandler);

        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        um.addEdit(e.getEdit());
                    }
                }
        );

//       Scrollpane initialization
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        To set the border
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
//        add scrollpane to the window
        window.add(scrollPane);
//        window.add(textArea);
    }

    //    Function to create menubar
    public void createMenuBar(){
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);
//        Add file in the menu
        menuFile= new JMenu("File");
        menuBar.add(menuFile);
//      Add edit in the menu
        menuEdit= new JMenu("Edit");
        menuBar.add(menuEdit);
//      Add format in the menu
        menuFormat= new JMenu("Format");
        menuBar.add(menuFormat);
//      Add color in the menu
        menuColor= new JMenu("Color");
        menuBar.add(menuColor);
    }

    //    Add items in the menu elements
    public void createFileMenu(){
//        Add new to file in the menu
        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);
//        Add Open to file in the menu
        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);
//        Add Save to file in the menu
        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");
        menuFile.add(iSave);
//        Add SaveAs to file in the menu
        iSaveAs = new JMenuItem("SaveAs");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);
//        Add Exit to file in the menu
        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");
        menuFile.add(iExit);
    }

    public void createEditMenu(){
        iCut = new JMenuItem("Cut");
        iCut.addActionListener(this);
        iCut.setActionCommand("Cut");
        menuEdit.add(iCut);

        iCopy = new JMenuItem("Copy");
        iCopy.addActionListener(this);
        iCopy.setActionCommand("Copy");
        menuEdit.add(iCopy);

        iPaste = new JMenuItem("Paste");
        iPaste.addActionListener(this);
        iPaste.setActionCommand("Paste");
        menuEdit.add(iPaste);

        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }
    public void createFormatMenu(){
        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("Size8");
        menuFontSize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size12");
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("Size16");
        menuFontSize.add(iFontSize16);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("Size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("Size24");
        menuFontSize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("Size28");
        menuFontSize.add(iFontSize28);
    }

    public void createColorMenu(){
        iColor1 = new JMenuItem("White");
        iColor1.addActionListener(this);
        iColor1.setActionCommand("White");
        menuColor.add(iColor1);

        iColor2 = new JMenuItem("Black");
        iColor2.addActionListener(this);
        iColor2.setActionCommand("Black");
        menuColor.add(iColor2);

        iColor3 = new JMenuItem("Blue");
        iColor3.addActionListener(this);
        iColor3.setActionCommand("Blue");
        menuColor.add(iColor3);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//        get the command
        String command = e.getActionCommand();
        switch(command){
            case "New": file.newFile(); break;
            case "Open": file.open(); break;
            case "Save": file.save(); break;
            case "SaveAs": file.saveAs(); break;
            case "Exit": file.exit();break;
            case "Cut": edit.cut();break;
            case "Copy":edit.copy();break;
            case "Paste": edit.paste();break;
            case "Undo": edit.undo(); break;
            case "Redo": edit.redo(); break;
            case "Word Wrap": format.wordWrap(); break;
            case "Arial": format.setFont(command);break;
            case "Comic Sans MS": format.setFont(command); break;
            case "Times New Roman": format.setFont(command); break;
            case "Size8": format.createFont(8); break;
            case "Size12": format.createFont(12); break;
            case "Size16": format.createFont(16); break;
            case "Size20": format.createFont(20); break;
            case "Size24": format.createFont(24); break;
            case "Size28": format.createFont(28); break;
            case "White": color.changeColor(command); break;
            case "Black": color.changeColor(command); break;
            case "Blue": color.changeColor(command); break;
        }
    }
}
