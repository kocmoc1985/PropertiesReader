/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

/**
 *
 * @author Administrator
 */
public class PaneEntry extends JPanel implements ActionListener, MouseListener {

    private JLabel key_label;
    private JTextField value_textfield;
    private JToggleButton toggle_btn;
    private final Border initial_border;
    private final Color initial_background_color;

    public PaneEntry(LayoutManager layoutManager) {
        super(layoutManager);
        initial_border = this.getBorder();
        initial_background_color = this.getBackground();
    }

    public String get_key() {
        return key_label.getText().trim();
    }

    public String get_value() {
        if (toggle_btn != null) {
            return toggle_btn.getText();
        } else {
            return value_textfield.getText().trim();
        }

    }

    public void add_key_label_component(Component c) {
        key_label = (JLabel) c;
        add(c);

    }

    public void add_value_textfield_component(Component c) {
        value_textfield = (JTextField) c;
        if (get_value().equals("true") || get_value().equals("false")) {
            toggle_btn = new JToggleButton(get_value());
            toggle_btn.setBorder(BorderFactory.createLineBorder(Color.gray));
            toggle_btn.addActionListener(this);
            toggle_btn.addMouseListener(this);
            add(toggle_btn);
        } else {
            value_textfield.addMouseListener(this);
            add(c);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == toggle_btn) {
            if (toggle_btn.getText().equals("true")) {
                toggle_btn.setText("false");
            } else if (toggle_btn.getText().equals("false")) {
                toggle_btn.setText("true");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
//       this.setBorder(BorderFactory.createEtchedBorder());//BorderFactory.createLoweredBevelBorder()
        this.setBackground(Color.lightGray);
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        this.setBorder(initial_border);
        this.setBackground(initial_background_color);
    }
}
