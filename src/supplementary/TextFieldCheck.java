/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supplementary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author KOCMOC
 */
public class TextFieldCheck extends JTextField implements KeyListener {

    private String regex;
//    private final Font FONT_1 = new Font("Arial", Font.BOLD, 16);
    public static int OK_RESULT = 0;
    public static int WRONG_FORMAT_RESULT = 1;
    public static int ALREADY_EXIST_RESULT = 2;
    public int RESULT;

    public TextFieldCheck(String regex, int columns) {
        this.regex = regex;
        if (columns > 0) {
            setColumns(columns);
        }
        initOther();
    }
    public TextFieldCheck(String text,String regex, int columns) {
        //
        setText(text);
        //
        this.regex = regex;
        if (columns > 0) {
            setColumns(columns);
        }
        initOther();
        //
        check();
    }
    

    public int getRESULT() {
        return RESULT;
    }

    private void initOther() {
        this.addKeyListener(this);
//        this.setFont(FONT_1);
    }

    @Override
    public String getText() {
        if (RESULT == OK_RESULT) {
            return super.getText();
        } else if (RESULT == ALREADY_EXIST_RESULT) {
            JOptionPane.showMessageDialog(null, "Exist already");
            return null;
        } else if (RESULT == WRONG_FORMAT_RESULT) {
            JOptionPane.showMessageDialog(null, "Wrong format");
            return null;
        }
        //
        return null;

    }

    public String getText_() {
        return super.getText();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       check();
    }
    
    private void check(){
         //
        String str = getText_();
        //
        if (str.matches(regex)) {
            //
            setForeground(new Color(34,139,34));
            RESULT = OK_RESULT;
            //
        } else {
            setForeground(Color.RED);
            RESULT = WRONG_FORMAT_RESULT;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }
}
