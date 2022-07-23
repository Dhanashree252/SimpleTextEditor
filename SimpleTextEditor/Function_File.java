package SimpleTextEditor;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {
    GUI gui;
    String fileName;
    String fileAddress;
    public Function_File(GUI gui){
        this.gui = gui;
    }
    public void newFile(){
//        After clicking new remove all the text
        gui.textArea.setText("");
//        and change the title to new
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }
    public void open(){
//        open dialog after clicking save
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

//        select file name from open window
        if(fd.getFile() != null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        try{
//            read file for that file address and file name is provided
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.textArea.setText("");
            String line = null;
            while((line = br.readLine()) != null){
//                readline reads line by line
                gui.textArea.append(line+ "\n");
            }
            br.close();
        }
        catch(Exception e){
            System.out.println("FILE NOT OPENED!");
        }
    }
    public void save(){
        if(fileName==null){
//          Not saved the file previously hence do save as
            saveAs();
        }
        else{
            try{
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            }
            catch(Exception e){
                System.out.println("SOMETHING WRONG!");
            }
        }
    }
    public void saveAs(){
        FileDialog fd = new FileDialog(gui.window, "save", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile() != null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
//          set the file name on window
            gui.window.setTitle(fileName);
        }
        try{
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        }catch(Exception e){
            System.out.println("SOMETHING WRONG");
        }
    }
    public void exit(){
        System.exit(0);
    }
}
