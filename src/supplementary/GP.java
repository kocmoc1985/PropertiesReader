/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supplementary;

import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class GP {
    public static final URL IMAGE_ICON_URL = GP.class.getResource("images/icon.jpg");
    public static final String REGEX_PROPERTY = ".properties";
    public static final ArrayList<String> PROPERTIES_TO_SKIP = HelpM.read_Txt_To_ArrayList("pr_skip.list", "#");
    
}
