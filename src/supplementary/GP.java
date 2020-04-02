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
    public static final URL IMAGE_ICON_URL = GP.class.getResource("images/icon.png");
    public static final String PROPS_PATTERN = ".properties";
    public static final ArrayList<String> PROPERTIES_TO_SKIP = HelpM.read_Txt_To_ArrayList("pr_skip.list", "#");
    public static final String SPEC_PATTERN = "$";
    public static final String REGEX_PATTERN = "$";
    public static final String FIXED_VALUE_PATTERN = "%";
}
