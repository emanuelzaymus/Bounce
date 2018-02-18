package zaymusEmanuel.bounce.predmety;

import java.awt.Graphics2D;

/**
 * PredmetAnimovany - predmet s animovanou reprezentaciou.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public abstract class PredmetAnimovany {

    private Animacia animacia;
    private Animacia animacia2;
    private PoziciaPredmetu pozicia;

    private boolean vymena = false;

    /**
     * Vytvori jednoduchi animovany predmet.
     *
     * @param nazovAnimacie nazov suboru s grafickou reprezentaciou
     * @param od poradie obrazku od ktoreho sa maju zobrazovat obrazky
     * @param po poradie obrazku po ktory sa maju zobrazovat obrazky
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public PredmetAnimovany(String nazovAnimacie, int od, int po, int x, int y) {
        this.pozicia = new PoziciaPredmetu(x, y);
        this.animacia = new Animacia(nazovAnimacie, od, po);
        this.animacia2 = null;
    }

    public PredmetAnimovany(String nazovAnimacie, String nazovAnimacie2, int od, int po, int x, int y) {
        this.pozicia = new PoziciaPredmetu(x, y);
        this.animacia = new Animacia(nazovAnimacie, od, po);
        this.animacia2 = new Animacia(nazovAnimacie2, od, po);
    }

    public void zmenAnimaciu() {
        this.vymena = !this.vymena;
    }

    /**
     * Vykresli graficku reprezentaciu.
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        if (this.vymena) {
            this.animacia2.render(g2d, this.getX(), this.getY());
        } else {
            this.animacia.render(g2d, this.getX(), this.getY());
        }
    }

    /**
     * Posun predmetu.
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
