/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy.okna;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author 3M0
 */
public class Button extends JButton {

    public Button(String text, String toolTipText, ActionListener al) {
        super(text);
        super.setFont(new Font("Arial Black", Font.PLAIN, 15));
        super.setToolTipText(toolTipText);
        super.addActionListener(al);
    }

    public Button(String text, ActionListener al) {
        super(text);
        super.setFont(new Font("Arial Black", Font.PLAIN, 15));
        super.addActionListener(al);
    }

}
