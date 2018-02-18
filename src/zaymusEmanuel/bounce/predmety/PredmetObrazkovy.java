package zaymusEmanuel.bounce.predmety;

import java.awt.Graphics2D;

/**
 * PredmetObrazkovy - predmet s obrazovou reprezentaciou.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public abstract class PredmetObrazkovy {

    private Grafika grafika;
    private PoziciaPredmetu pozicia;

    /**
     * Vytvori jednoduchi obrazkovy predmet.
     *
     * @param nazovSuboru nazov suboru s grafickou reprezentaciou
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public PredmetObrazkovy(String nazovSuboru, int x, int y) {
        this.pozicia = new PoziciaPredmetu(x, y);
        this.grafika = new Grafika(nazovSuboru);
    }

    /**
     * Vykresli graficku reprezentaciu.
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        this.grafika.render(g2d, this.getX(), this.getY());
    }

    /**
     * Nastavi obrazok na zobrazovanie odovzdany cez parameter.
     *
     * @param nazovSuboru nazov suboru s grafickou reprezentaciou
     */
    protected void zmenObrazok(String nazovSuboru) {
        this.grafika.nastavObrazok(nazovSuboru);
    }

    /**
     * Posun objektu.
     *
     * @param posunX posun po X-ovej osi
     * @param posunY posun po Y-ovej osi
     */
    protected void pohyb(int posunX, int posunY) {
        this.pozicia.pohyb(posunX, posunY);
    }

    /**
     * Vrati X-ovu poziciu predmetu.
     *
     * @return x
     */
    protected int getX() {
        return this.pozicia.getX();
    }

    /**
     * Vrati Y-ovu poziciu predmetu.
     *
     * @return y
     */
    protected int getY() {
        return this.pozicia.getY();
    }

}
