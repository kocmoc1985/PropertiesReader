/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supplementary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CopyAndDie {

    public static final String LOGGFILE = "copy.log";
    
    public static void main(String[] args) {
        String path = "";
        try {
            path = args[0];
        } catch (Exception ex) {
            String failure = "args[] = empty";
            System.out.println(failure);
            SimpleLogger.logg(LOGGFILE, failure);
            System.exit(0);
        }
        try {
            copy_file(path, "temp.properties");
        } catch (FileNotFoundException ex) {
            SimpleLogger.logg(LOGGFILE, ex.toString());
            Logger.getLogger(CopyAndDie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            SimpleLogger.logg(LOGGFILE, ex.toString());
            Logger.getLogger(CopyAndDie.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public static void copy_file(String file_to_copy, String name_of_duplicate) throws FileNotFoundException, IOException {
        File inputFile = new File(file_to_copy);
        File outputFile = new File(name_of_duplicate);

        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }
}
