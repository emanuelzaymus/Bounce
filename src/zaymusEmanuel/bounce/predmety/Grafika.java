package zaymusEmanuel.bounce.predmety;

import com.sun.javafx.iio.ImageLoader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import zaymusEmanuel.bounce.C;

/**
 * Grafika - jednoducha trieda predstavujuca graficku reprezentaciu predmetov.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Grafika {

    private BufferedImage obrazok;

    /**
     * Vytvori obrazok.
     *
     * @param nazovSuboru nazov suboru s grafickou reprezentaciou
     */
    public Grafika(String nazovSuboru) {
        this.nastavObrazok(nazovSuboru);
    }

    /**
     * Nastavi obrazok na zobrazovanie odovzdany cez parameter.
     *
     * @param nazovSuboru nazov suboru s grafickou reprezentaciou
     */
    public void nastavObrazok(String nazovSuboru) {
        try {
            this.obrazok = ImageIO.read(new File(C.DATA_PICS_cesta + nazovSuboru));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
//        try {
//            this.obrazok = ImageIO.read(ImageLoader.class.getResourceAsStream("/pics/" + nazovSuboru));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    /**
     * Vykresli obrazok.
     *
     * @param g2d
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public void render(Graphics2D g2d, int x, int y) {
        g2d.drawImage(this.obrazok, x, y, null);
    }

    /**
     * Vrati sirku obrazku.
     *
     * @return sirka obrazku
     */
    public int getSirkaObrazku() {
        return this.obrazok.getWidth();
    }

    /**
     * Vrati vysku obrazku.
     *
     * @return vyska obrazku
     */
    public int getVyskaObrazku() {
        return this.obrazok.getHeight();
    }

}
