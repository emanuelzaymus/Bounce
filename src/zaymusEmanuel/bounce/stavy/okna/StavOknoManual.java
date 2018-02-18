package zaymusEmanuel.bounce.stavy.okna;

import java.awt.BorderLayout;
import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Grafika;
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
import zaymusEmanuel.bounce.stavy.IStav;
import zaymusEmanuel.bounce.stavy.StavMenuPrihlaseny;

/**
 * StavOknoManual - zobrazi manual pre hraca.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class StavOknoManual implements IStav {

    private Button okButton;
    private JFrame okno;

    private final Grafika obrazovka;
    private IStav dalsiStav = null;

    /**
     * Vytvori vsetky potrebne atributy pre pobrazenie manualu.
     */
    public StavOknoManual(String menoHraca) {
        final String meno = menoHraca;
        this.obrazovka = new Grafika(C.UVODA_OBRAZOVKA);

        this.okButton = new Button("OK", (ActionEvent ae) -> {
            this.dalsiStav = new StavMenuPrihlaseny(meno);
            this.okno.setVisible(false);
        });

        JPanel panel = new JPanel(new BorderLayout());
        JPanel centralPanel = new JPanel(new GridLayout(9, 1));
        centralPanel.add(new Label(" "));
        centralPanel.add(new Label("POZBIERAJ VŠETKY MINCE A DOSTAŇ SA DO CIEĽA!"));
        centralPanel.add(new Label("POZOR! AK SA DOTKNEŠ PICHLIAČA, STRATÍŠ ŽIVOT."));
        centralPanel.add(new Label(" "));
        centralPanel.add(new Label("                    OVLÁDANIE:"));
        centralPanel.add(new Label("                    POHYB - ŠÍPKY"));
        centralPanel.add(new Label("                    PAUZA - MEDZERNÍK / ENTER"));
        centralPanel.add(new Label("                    ODÍSŤ - BACKSPACE"));

        panel.add(centralPanel, BorderLayout.CENTER);
        panel.add(new JLabel("         "), BorderLayout.WEST);
        panel.add(this.okButton, BorderLayout.SOUTH);

        panel.setOpaque(true);

        this.okno = new JFrame("Manuál");
        this.okno.setContentPane(panel);
        this.okno.setSize(520, 250);
        this.okno.setResizable(false);
        this.okno.setFocusable(true);
        this.okno.setLocationRelativeTo(null);

        this.okno.setIconImage(new ImageIcon(C.DATA_PICS_cesta + C.LOPTA_KU_TLACITKU).getImage()
        );
        this.okno.setVisible(true);
        this.okButton.requestFocusInWindow();

        this.okno.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                StavOknoManual.this.dalsiStav = new StavMenuPrihlaseny(meno);
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
        this.obrazovka.render(g2d, 0, 0);
    }

}
