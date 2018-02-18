/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy.okna;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author 3M0
 */
public class Label extends JLabel {

    public Label() {
    }

    public Label(String text, Font font) {
        super(text);
        super.setFont(font);
    }

    public Label(String text, Font font, Color farba) {
        super(text);
        super.setFont(font);
        super.setForeground(farba);
    }

    public Label(String text) {
        super(text);
        super.setFont(new Font("Arial Black", Font.PLAIN, 15));
    }

}
