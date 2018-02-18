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
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
public class StavOknoVytvorenieProfilu implements IStav {

    private TextField menoField;
    private PasswordField hesloField;
    private PasswordField heslo2Field;

    private JFrame okno;
    private Button vytvorButton;
    private Button odistButton;

    private Grafika uvodnaObrazovka;

    private IStav dalsiStav = null;

    public StavOknoVytvorenieProfilu() {
        this.vytvorenieOkna(false, null, null, null, false);
    }

    public StavOknoVytvorenieProfilu(String meno, String heslo, String heslo2, boolean opakujeSaMeno) {
        this.vytvorenieOkna(true, meno, heslo, heslo2, opakujeSaMeno);
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

    private void vytvorenieOkna(boolean sChybou, String meno, String heslo, String heslo2, boolean opakujeSaMeno)
            throws HeadlessException {
        this.uvodnaObrazovka = new Grafika(C.UVODA_OBRAZOVKA);

        Label popisLabel = new Label("Účet bude uložený iba na tomto počítači.", new Font("Arial Black", Font.PLAIN, 11));
        Label popis2Label = new Label("Zadaj registračné údaje", new Font("Arial Black", Font.PLAIN, 15));
        Label menoLabel = new Label(" Meno: ", new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        this.menoField = new TextField(meno, "Zadaj prihlasovacie meno");

        Label hesloLabel = new Label(" Heslo: ", new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        this.hesloField = new PasswordField(heslo, "Zadaj prihlasovacie heslo");
        Label heslo2Label = new Label(" Zopakuj heslo: ", new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        this.heslo2Field = new PasswordField(heslo2, "Zadaj opakovane prihlasovacie heslo");

        Label poznamkaLabel = new Label(null, new Font("Arial Black", Font.PLAIN, 12), Color.red);
        if (meno != null) {
            if (meno.length() < 3) {
                poznamkaLabel.setText("Meno musí mať minimálne 3 znaky!");
            } else if (meno.length() > 13) {
                poznamkaLabel.setText("Meno môže mať max 13 znakov!");
            } else if (opakujeSaMeno) {
                poznamkaLabel.setText("Zadané meno už existuje!");
            } else if (heslo.length() < 6) {
                poznamkaLabel.setText("Heslo musí mať minimálne 6 znakov!");
            } else if (sChybou) {
                poznamkaLabel.setText("Heslá sa nezhodujú!");
            }
        }

        this.vytvorButton = new Button("Vytvoriť profil", "Vytvoriť nový profil", (ActionEvent ae) -> {
            boolean opakujeSa = this.opakujeSa();
            if (this.menoField.getText().length() >= 3 && this.menoField.getText().length() <= 13 && !opakujeSa && this.hesloField.getText().length() >= 6
                    && this.hesloField.getText().equals(this.heslo2Field.getText())) {
                this.pridajProfil();
                this.vytvorSuborZaznamov();
                this.dalsiStav = new StavMenuPrihlaseny(this.menoField.getText());
            } else {
                this.dalsiStav = new StavOknoVytvorenieProfilu(this.menoField.getText(),
                        this.hesloField.getText(), this.heslo2Field.getText(), opakujeSa);
            }
            this.okno.setVisible(false);
        });

        this.odistButton = new Button("Odísť", "Vrátiť sa do menu", (ActionEvent ae) -> {
            this.dalsiStav = new StavHlavneMenu();
            this.okno.setVisible(false);
        });

        JPanel panel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(6, 1));
        JPanel westPanel = new JPanel(new GridLayout(6, 1));
        JPanel southPanel = new JPanel(new GridLayout(1, 3));

        westPanel.add(new JLabel(" "));
        westPanel.add(new JLabel(" "));
        westPanel.add(menoLabel);
        westPanel.add(hesloLabel);
        westPanel.add(heslo2Label);

        centerPanel.add(popisLabel);
        centerPanel.add(popis2Label);
        centerPanel.add(this.menoField);
        centerPanel.add(this.hesloField);
        centerPanel.add(this.heslo2Field);
        centerPanel.add(poznamkaLabel);

        southPanel.add(this.vytvorButton);
        southPanel.add(this.odistButton);

        panel.add(new JLabel("                     "), BorderLayout.EAST);
        panel.add(new JLabel(" "), BorderLayout.NORTH);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(westPanel, BorderLayout.WEST);
        panel.add(southPanel, BorderLayout.SOUTH);

        panel.setOpaque(true);

        this.okno = new JFrame("Vytvorenie profilu");
        this.okno.setContentPane(panel);
        this.okno.setSize(460, 220);
        this.okno.setResizable(false);
        this.okno.setFocusable(true);
        this.okno.setLocationRelativeTo(null);

        this.okno.setIconImage(new ImageIcon(C.DATA_PICS_cesta + C.LOPTA_KU_TLACITKU).getImage()
        );
        this.okno.setVisible(true);
        this.menoField.requestFocusInWindow();

        this.okno.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                StavOknoVytvorenieProfilu.this.dalsiStav = new StavHlavneMenu();
            }
        });
    }

    private void pridajProfil() {
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

        String menoZ = null;
        String hesloZ = null;
        try {
            Sifrovac s = Sifrovac.getInstancia();

            menoZ = s.encrypt(this.menoField.getText(), "heslo");
            hesloZ = s.encrypt(this.hesloField.getText(), "heslo");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        prihlasovacieUdaje.put(menoZ, hesloZ);

        try (DataOutputStream zapisovac = new DataOutputStream(new FileOutputStream(new File(C.DATA_UCTY_UCTY_cesta)))) {
            for (String meno : prihlasovacieUdaje.keySet()) {
                zapisovac.writeUTF(meno);
                zapisovac.writeUTF(prihlasovacieUdaje.get(meno));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void vytvorSuborZaznamov() {
        try (PrintWriter pw = new PrintWriter(new File(C.DATA_VYSLEDKY_cesta + this.menoField.getText() + ".bin"))) {
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private boolean opakujeSa() {
        try (DataInputStream citac = new DataInputStream(new FileInputStream(new File(C.DATA_UCTY_UCTY_cesta)))) {
            String meno = this.menoField.getText();
            while (citac.available() > 0) {
                if (meno.equals(citac.readUTF())) {
                    return true;
                }
                citac.readUTF();
            }
        } catch (EOFException ex) {
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return false;
    }

}
