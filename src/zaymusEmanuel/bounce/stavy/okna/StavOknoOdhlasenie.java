/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy.okna;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.stavy.IStav;
import zaymusEmanuel.bounce.stavy.StavHlavneMenu;
import zaymusEmanuel.bounce.stavy.StavMenuPrihlaseny;

/**
 *
 * @author 3M0
 */
public class StavOknoOdhlasenie implements IStav {

    private JFrame okno;
    private Button anoButton;
    private Button nieButton;

    private Grafika uvodnaObrazovka;
    private String menoHraca;
    private IStav dalsiStav = null;

    public StavOknoOdhlasenie(String meno) {
        this.menoHraca = meno;
        this.uvodnaObrazovka = new Grafika(C.UVODA_OBRAZOVKA);

        Label spravaLabel = new Label("Chcete opustiť tento profil?", new Font("Arial Black", Font.PLAIN, 17));

        this.anoButton = new Button("Áno", "Odhlásiť sa z profilu", (ActionEvent ae) -> {
            this.dalsiStav = new StavHlavneMenu();
            this.okno.setVisible(false);
        });

        this.nieButton = new Button("Nie", "Návrat do profilu", (ActionEvent ae) -> {
            this.dalsiStav = new StavMenuPrihlaseny(this.menoHraca);
            this.okno.setVisible(false);
        });

        JPanel panel = new JPanel(new BorderLayout());
        JPanel westPanel = new JPanel(new GridLayout());
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        JPanel southPanel = new JPanel(new GridLayout(1, 2));

        westPanel.add(new JLabel("              "));

        centerPanel.add(new JLabel(" "));
        centerPanel.add(spravaLabel);

        southPanel.add(this.anoButton);
        southPanel.add(this.nieButton);

        panel.add(westPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        panel.setOpaque(true);

        this.okno = new JFrame("Odhlásenie");
        this.okno.setContentPane(panel);
        this.okno.setSize(350, 140);
        this.okno.setResizable(false);
        this.okno.setFocusable(true);
        this.okno.setLocationRelativeTo(null);

        this.okno.setIconImage(new ImageIcon(C.DATA_PICS_cesta + C.LOPTA_KU_TLACITKU).getImage());
        this.okno.setVisible(true);
        this.anoButton.requestFocusInWindow();

        this.okno.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                StavOknoOdhlasenie.this.dalsiStav = new StavMenuPrihlaseny(StavOknoOdhlasenie.this.menoHraca);
            }
        });

    }

    @Override
    public void tik(Hra hra) {
        if (this.dalsiStav != null) {
            hra.zmenStavNa(this.dalsiStav);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        this.uvodnaObrazovka.render(g2d, 0, 0);
    }

}
