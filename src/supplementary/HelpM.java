/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supplementary;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Administrator
 */
public class HelpM {

    public static ArrayList<String> properties_load_properties_to_arraylist(String path) {
        ArrayList<String> properties_list = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader);
            String[] parts;

            String rs = br.readLine();
            while (rs != null) {
                parts = rs.split("=");
                if (parts.length == 1 && lastCharEquals(rs, "=") == true) {
                    properties_list.add(properties_list.size(), parts[0] + "#" + "");
                    rs = br.readLine();
                    continue;
                }

                if (parts.length == 1 && lastCharEquals(rs, "=") == false) {
                    rs = br.readLine();
                    continue;
                }

                if (parts.length == 0) {
                    break;
                }
                if (parts[0].isEmpty() == false && parts[1].isEmpty() == false) {
                    properties_list.add(properties_list.size(), parts[0] + "#" + parts[1]);
                }

                rs = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("" + e);
        }

        return properties_list;
    }

    private static boolean lastCharEquals(String str_to_chek, String equals) {
        int a = str_to_chek.length() - 1;
        int b = str_to_chek.length();
        String last_char;
        try {
            last_char = str_to_chek.substring(a, b);
        } catch (Exception ex) {
            return false;
        }

        if (last_char.equals(equals)) {
            return true;
        } else {
            return false;
        }
    }

    public static void properties_save_properties_manual(ArrayList<String> properties_list, String fileName) throws IOException {
        FileWriter fstream = new FileWriter(fileName, false);
        BufferedWriter out = new BufferedWriter(fstream);

        String key;
        String value;

        for (String property : properties_list) {
            String arr[] = property.split("#");

            if (arr.length == 1) {
                key = arr[0];
                value = "";
            } else {
                key = arr[0];
                value = arr[1];
            }

            out.write(key + "=" + value);
            out.newLine();
            out.flush();
        }
        out.close();

    }

    public static Properties properties_load_properties(String path_andOr_fileName, boolean list_properties) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(path_andOr_fileName));
            if (list_properties == true) {
                p.list(System.out);
            }
        } catch (IOException ex) {
            System.out.println("" + ex);
        }
        return p;
    }

    public static ArrayList<String> read_Txt_To_ArrayList(String filename, String regex) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] parts;
            String rs = br.readLine();
            while (rs != null) {
                parts = rs.split(regex);
                for (int i = 0; i < parts.length; i++) {
                    list.add(parts[i]);
                }
                rs = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("" + e);
        }
        return list;
    }

    public static Point position_window_in_center_of_the_screen(JFrame window) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point((d.width - window.getSize().width) / 2, (d.height - window.getSize().height) / 2);
    }

    private static void wait_(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(HelpM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
