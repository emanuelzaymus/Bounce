package zaymusEmanuel.bounce;

import java.awt.Canvas;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Platno - singleton - jednoducha obrazovka s platnom.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
class Platno extends Canvas {

    private static Platno instancia = null;
    private JFrame frame;

    /**
     * Vytvori jedine platno.
     */
    private Platno() {
        this.frame = new JFrame(C.NAZOV);

        this.frame.setSize(C.SIRKA, C.VYSKA);
        this.frame.setResizable(false);
        this.frame.setFocusable(true);

        this.frame.setLocationRelativeTo(null);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.setIconImage(new ImageIcon(C.DATA_PICS_cesta + C.LOPTA_KU_TLACITKU).getImage());
    }

    /**
     * Vrati jedinu instanciu platna.
     *
     * @return instancia
     */
    public static Platno getInstancia() {
        if (Platno.instancia == null) {
            Platno.instancia = new Platno();
        }
        return Platno.instancia;
    }

    /**
     * Prida aplikaciu na zobrazovanie na platne.
     */
    void pridaj(Hra hra) {
        this.frame.add(hra);
    }

    /**
     * Nastavy platno na viditelne.
     */
    public void zobraz() {
        this.frame.setVisible(true);
    }

}
