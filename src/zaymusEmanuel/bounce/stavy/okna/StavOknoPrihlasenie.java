/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy.okna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.stavy.IStav;
import zaymusEmanuel.bounce.stavy.Sifrovac;
import zaymusEmanuel.bounce.stavy.StavHlavneMenu;
import zaymusEmanuel.bounce.stavy.StavMenuPrihlaseny;

/**
 *
 * @author 3M0
 */
public class StavOknoPrihlasenie implements IStav {

    private TextField menoField;
    private PasswordField hesloField;

    private JFrame okno;
    private Button prihlasitButton;
    private Button vytvorButton;
    private Button odistButton;

    private Grafika uvodnaObrazovka;

    private IStav dalsiStav = null;

    public StavOknoPrihlasenie() {
        this.vytvorenieOkna(false, null, null);
    }

    public StavOknoPrihlasenie(String meno, String heslo) {
        this.vytvorenieOkna(true, meno, heslo);
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

    private void vytvorenieOkna(boolean sChybou, String meno, String heslo) throws HeadlessException {
        this.uvodnaObrazovka = new Grafika(C.UVODA_OBRAZOVKA);

        Label popisLabel = new Label("Zadaj prihlasovacie údaje", new Font("Berlin Sans FB Demi", Font.PLAIN, 17));
        Label menoLabel = new Label(" Meno: ", new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        this.menoField = new TextField(meno, "Zadaj prihlasovacie meno");

        Label hesloLabel = new Label(" Heslo: ", new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        this.hesloField = new PasswordField(heslo, "Zadaj prihlasovacie heslo");

        Label poznamkaLabel = (sChybou) ? new Label("Chybné prihlasovacie údaje!", new Font("Berlin Sans FB Demi", Font.PLAIN, 15), Color.red) : new Label();

        this.prihlasitButton = new Button("Prihlásiť sa", "Prihlásiť sa do existujúceho účtu", (ActionEvent ae) -> {
            if (this.spravneUdaje()) {
                this.dalsiStav = new StavMenuPrihlaseny(this.menoField.getText());
            } else {
                this.dalsiStav = new StavOknoPrihlasenie(this.menoField.getText(), this.hesloField.getText());
            }
            this.okno.setVisible(false);
        });

        this.vytvorButton = new Button("Vytvoriť profil", "Vytvoriť nový profil", (ActionEvent ae) -> {
            this.dalsiStav = new StavOknoVytvorenieProfilu();
            this.okno.setVisible(false);
        });

        this.odistButton = new Button("Odísť", "Vrátiť sa do menu", (ActionEvent ae) -> {
            this.dalsiStav = new StavHlavneMenu();
            this.okno.setVisible(false);
        });

        JPanel panel = new JPanel(new BorderLayout());
        JPanel westPanel = new JPanel(new GridLayout(4, 1));
        JPanel centerPanel = new JPanel(new GridLayout(4, 2));
        JPanel southPanel = new JPanel(new GridLayout(1, 3));

        westPanel.add(new JLabel(" "));
        westPanel.add(menoLabel);
        westPanel.add(hesloLabel);

        centerPanel.add(popisLabel);
        centerPanel.add(this.menoField);
        centerPanel.add(hesloField);
        centerPanel.add(poznamkaLabel);

        southPanel.add(this.prihlasitButton);
        southPanel.add(this.vytvorButton);
        southPanel.add(this.odistButton);

        panel.add(new JLabel("                     "), BorderLayout.EAST);
        panel.add(new JLabel(" "), BorderLayout.NORTH);

        panel.add(westPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        panel.setOpaque(true);

        this.okno = new JFrame("Prihlásenie");
        this.okno.setContentPane(panel);
        this.okno.setSize(460, 180);
        this.okno.setResizable(false);
        this.okno.setFocusable(true);
        this.okno.setLocationRelativeTo(null);

        this.okno.setIconImage(new ImageIcon(C.DATA_PICS_cesta + C.LOPTA_KU_TLACITKU).getImage());
        this.okno.setVisible(true);
        this.menoField.requestFocusInWindow();

        this.okno.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                StavOknoPrihlasenie.this.dalsiStav = new StavHlavneMenu();
            }
        });
    }

    private boolean spravneUdaje() {
        HashMap<String, String> prihlasovacieUdaje = new HashMap<>();

        try (DataInputStream citac = new DataInputStream(new FileInputStream(new File(C.DATA_UCTY_UCTY_cesta)))) {
            while (citac.available() > 0) {
                prihlasovacieUdaje.put(citac.readUTF(), citac.readUTF());
            }
        } catch (EOFException ex) {
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        String meno = null;
        String heslo = null;
        try {
            Sifrovac s = Sifrovac.getInstancia();

            meno = s.encrypt(this.menoField.getText(), "heslo");
            heslo = s.encrypt(this.hesloField.getText(), "heslo");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (prihlasovacieUdaje.containsKey(meno)) ? prihlasovacieUdaje.get(meno).equals(heslo) : false;
    }

}
