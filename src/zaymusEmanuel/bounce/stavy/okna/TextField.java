/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy.okna;

import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author 3M0
 */
public class TextField extends JTextField {

    public TextField(String text, String toolTipText) {
        super(text);
        super.setFont(new Font("Arial Black", Font.PLAIN, 15));
        super.setToolTipText(toolTipText);
    }

}
