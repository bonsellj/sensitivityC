import java.util.Properties;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.awt.FileDialog;
import java.awt.Frame;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;

//csgo
public class main{
    public static void changeProperty(String filename, String key, String value) throws IOException {
        File tmpFile = new File(filename + ".tmp");
        File file = new File(filename);
        PrintWriter pw = new PrintWriter(tmpFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        boolean found = false;
        final String toAdd = key + " " + value;
        for (String line; (line = br.readLine()) != null; ) {
            if (line.startsWith(key)) {
                line = toAdd;
                found = true;
            }
            pw.println(line);
        }
        if (!found)
            pw.println(toAdd);
        br.close();
        pw.close();
        file.delete();
        tmpFile.renameTo(file);
    }

    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        try{
            FileDialog dialog = new FileDialog((Frame)null, "Select the config file");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String p = dialog.getDirectory() + dialog.getFile();
            System.out.println(p+" chosen");
            String pth = StringEscapeUtils.escapeJava(p);
            System.out.println(pth);
            System.out.println("Enter the desired sensitivity");
            String sensi = keyboard.nextLine();
            changeProperty(pth, "sensitivity", "\"" + sensi + "\"");

        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IOException");
        }
    }
}