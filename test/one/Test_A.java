/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author KOCMOC
 */
public class Test_A {

    public static void main(String[] args) {
        Properties p = properties_load_properties("A1/emails.properties", false);
        System.out.println("" + getEmailsStr(p));

    }

    public static String getEmailsStr(Properties p) {
        String str = "";
        Set set = p.keySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = p.getProperty(key);
            //
            if (value.isEmpty() == false) {
                if (str.isEmpty() == false) {
                    str += ";" + value;
                } else {
                    str += value;
                }
            }
            //
        }
        return str;
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
}
