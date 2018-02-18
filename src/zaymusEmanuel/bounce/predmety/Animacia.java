package zaymusEmanuel.bounce.predmety;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Animacia - jednoducha animacia cyklicky zobrazujuca obrazky.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Animacia {

    private final ArrayList<Grafika> obrazky;
    private int pocitadlo;
    private boolean zmenitObrazok;

    /**
     * Vytvori jednoduchu animaciu, nacita vsetky potrebne obrazky.
     *
     * @param nazovAnimacie nazov suboru s grafickou reprezentaciou
     * @param od poradie obrazku od ktoreho sa maju zobrazovat obrazky
     * @param po poradie obrazku po ktory sa maju zobrazovat obrazky
     */
    Animacia(String nazovAnimacie, int od, int po) {
        this.obrazky = new ArrayList<>();

        for (int i = od; i <= po; i++) {
            String nazovSuboru = String.format("%s%05d.png", nazovAnimacie, i);
            this.obrazky.add(new Grafika(nazovSuboru));
        }

        this.pocitadlo = 0;
        this.zmenitObrazok = false;
    }

    /**
     * Vykresli animaciu.
     *
     * @param g2d
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public void render(Graphics2D g2d, int x, int y) {
        if (this.zmenitObrazok) {
            if (++this.pocitadlo >= this.obrazky.size()) {
                this.pocitadlo = 0;
            }
        }
        this.zmenitObrazok = !this.zmenitObrazok;
        this.obrazky.get(this.pocitadlo).render(g2d, x, y);
    }

}
