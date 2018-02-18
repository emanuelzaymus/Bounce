/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy.okna;

import javax.swing.JPasswordField;

/**
 *
 * @author 3M0
 */
public class PasswordField extends JPasswordField {

    public PasswordField(String text, String toolTipTest) {
        super(text);
        super.setToolTipText(toolTipTest);
    }

}
