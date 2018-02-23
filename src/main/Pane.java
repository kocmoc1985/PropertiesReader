/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import supplementary.HelpM;

/**
 *
 * @author Administrator
 */
public class Pane extends JPanel {

    private String property_path = "";
    private ArrayList<PaneEntry> pane_entry_list = new ArrayList<PaneEntry>();

    public Pane(String propertyPath) {
        super();
        property_path = propertyPath;
    }

    public void add_pane_entry(Component c) {
        pane_entry_list.add(pane_entry_list.size(), (PaneEntry) c);
        add(c);
    }

    public ArrayList<PaneEntry> get_pane_entries() {
        return pane_entry_list;
    }

    public String get_property_path() {
        return property_path;
    }

    public void save_properties() {
        //
        ArrayList<String> properties_list = new ArrayList<String>();
        //
        for (PaneEntry paneEntry : pane_entry_list) {
            String key = paneEntry.get_key();
            String value = paneEntry.get_value();
            properties_list.add(properties_list.size(), key + "#" + value);
        }
        //
        try {
            HelpM.properties_save_properties_manual(properties_list, property_path);
        } catch (IOException ex) {
            Logger.getLogger(Pane.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
    }
}
