/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import supplementary.GP;
import supplementary.HelpM;
import supplementary.SpecsFile;
import udp.Client_UDP;

/**
 *
 * @author Administrator
 */
public class PReader extends JFrame implements ActionListener, ComponentListener {

    private final ArrayList<String> property_files_list = new ArrayList<String>();
    private final ArrayList<Pane> pane_list = new ArrayList<Pane>();
    private final HashMap<String, String> absolute_path_map = new HashMap<String, String>();
    //==========================
    private final JTabbedPane tabbed_pane = new JTabbedPane();
    private final JPanel tabbed_pane_container = new JPanel(new BorderLayout());
    private final JButton save_btn = new JButton("<html><span style='text-shadow: 2px 2px #000000;'>save</span></html>");
    private final Font save_btn_font = new Font("Arial", Font.PLAIN, 24);
    private Client_UDP client_UDP;

    public PReader() {
        go();
    }

    public static void main(String[] args) {
        //
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    //
////                    UIDefaults defaults = UIManager.getLookAndFeelDefaults();
////                    defaults.put("Table.gridColor", new Color(198, 195, 198));
////                    defaults.put("Table.disabled", false);
////                    defaults.put("Table.showGrid", true);
////                    defaults.put("Table.intercellSpacing", new Dimension(1, 1));
//                    //
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //
        PReader pReader = new PReader();
    }

    private void go() {
        startUdpClient();//[2020-04-07]
        find_properties(".");//find_properties(".");
        build_tabbed_pane();
        build_jframe();
    }
    
    private void startUdpClient(){
        client_UDP = new Client_UDP("localhost", 65534);
    }

    private void save() {
        for (Pane pane : pane_list) {
            pane.save_properties();
        }
    }

    //================================
    private void build_jframe() {
        calculate_jframe_size();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Properties Reader");
        this.setIconImage(new ImageIcon(GP.IMAGE_ICON_URL).getImage());
        this.addComponentListener(this);

        save_btn.addActionListener(this);
        save_btn.setFont(save_btn_font);

        calculate_components_size();

        this.add(tabbed_pane_container);
        this.add(save_btn);

        this.setLocation(HelpM.position_window_in_center_of_the_screen(this));
        this.setVisible(true);
    }

    private void calculate_jframe_size() {
        int min_width = 450;
        int width = (property_files_list.size() * 100);
        if (width < min_width) {
            width = min_width;
        }
        int height = 450;
        this.setSize(new Dimension(width, height));
    }

    private void calculate_components_size() {
        int width = (int) Math.round((getWidth() * 0.98));
        int pane_height = (int) Math.round((getHeight() * 0.78));
        int btn_height = (int) Math.round((getHeight() * 0.1));

        int diff = getHeight() - (pane_height + btn_height);

        while (diff > 53) {
            pane_height += 1;
            diff = getHeight() - (pane_height + btn_height);
        }

        while (diff < 53) {
            pane_height -= 1;
            diff = getHeight() - (pane_height + btn_height);
        }

        tabbed_pane_container.setPreferredSize(new Dimension(width, pane_height));
        save_btn.setPreferredSize(new Dimension(width, btn_height));

//        System.out.println("jframe: " + getHeight());
//        System.out.println("pane: " + pane_height);
//        System.out.println("btn: " + btn_height);
//        System.out.println("diff: " + diff);
//        System.out.println("=================================\n");
    }

    private void refresh_jframe() {
        SwingUtilities.updateComponentTreeUI(this.getContentPane());
    }

    private void build_tabbed_pane() {
        for (String property_path : property_files_list) {
            File f = new File(property_path);

            JScrollPane jsp = new JScrollPane(create_pane(property_path));
            jsp.setHorizontalScrollBar(null);
            tabbed_pane.addTab(f.getName(), jsp);//create_pane(property_path)
        }
        //
        tabbed_pane_container.add(tabbed_pane);
    }

    

    /**
     * Create "pane" for the "JTabbedPane"
     *
     * @param s
     * @return
     */
    private JPanel create_pane(String property_path) {
        //
        Pane pane = new Pane(property_path);
        //
        ArrayList<String> properties_list = HelpM.properties_load_properties_to_arraylist(property_path);
        //
        GridLayout gridLayout;
        if (properties_list.isEmpty() == false) {
            gridLayout = new GridLayout(properties_list.size(), 0);
        } else {
            gridLayout = new GridLayout(1, 0);
        }
        //
        pane.setLayout(gridLayout);
        //
        for (String property : properties_list) {
            String[] arr = property.split("#");
            String key;
            String value;
            //
            if (arr.length == 1) {
                key = arr[0];
                value = "";
            } else {
                key = arr[0];
                value = arr[1];
            }
            //
            PaneEntry one_entry_container = new PaneEntry(new GridLayout(1, 2));
            //
            JLabel key_label = new JLabel("   " + key);
            key_label.setBorder(BorderFactory.createLineBorder(Color.black));
            JTextField value_text_field = new JTextField(value);
            //
            //
            SpecsFile specsFile = check_if_specs_file_exist(property_path);
            //
            one_entry_container.setSpecsFile(specsFile);
            //
            one_entry_container.add_key_label_component(key_label);
            one_entry_container.add_value_textfield_component(value_text_field);
            //
            pane.add_pane_entry(one_entry_container);
            //
        }
        //
        pane_list.add(pane);
        return pane;
        //
    }
    
    private SpecsFile check_if_specs_file_exist(String path) {
        //
        File f = new File(path);
        //
        String fileNameWithExt = f.getAbsoluteFile().getName();
        //
        String[] arr = fileNameWithExt.split("\\.");
        //
        String specFile = "";
        //
        if(arr.length == 2){
            specFile = f.getParent() + "\\" + arr[0] + "$." + arr[1];
        }else if(arr.length == 3){
            specFile = f.getParent() + "\\" + arr[0] + "." + arr[1] + "$." + arr[2];
        }
        //
        if (HelpM.fileExist(specFile)) {
            return new SpecsFile(true, specFile);
        } else {
            return null;
        }
    }

    /**
     * This method uses RECURSION
     *
     * @param path write "." for current dir
     */
    private void find_properties(String path) {
        File[] f = new File(path).listFiles();
        for (File file : f) {
            if (file.isDirectory()) {
                find_properties(file.getPath());
            } else if (file.getName().contains(GP.PROPS_PATTERN) && file.getName().contains(GP.SPEC_PATTERN) == false) {
                if (skip(file.getName()) == false) {
                    property_files_list.add(file.getPath());
                    absolute_path_map.put(file.getPath(), file.getAbsolutePath());
                }
            }
        }
    }

    private boolean skip(String property_file_name) {
        for (String string : GP.PROPERTIES_TO_SKIP) {
            if (property_file_name.toLowerCase().contains(string.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save_btn) {
            client_UDP.prepareAndSendDatagram("#npms_restart#");
            save();
            tabbed_pane_container.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        calculate_components_size();
        refresh_jframe();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
