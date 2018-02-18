package zaymusEmanuel.bounce.vstupy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;

/**
 * VstupMysi - zaznamenava stlacene a pustene tlacitka mysi.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class VstupMysi extends MouseAdapter {

    private static HashSet<Integer> stlaceneTlacitka;
    private static HashSet<Integer> posledneStlacene;
    private static int x;
    private static int y;
    private static int posledneX;
    private static int posledneY;

    /**
     * Vytvori atrinuty pre stlacene tlacitka mysi a poslede stlacene tlacitka
     * mysi.
     */
    public VstupMysi() {
        this.stlaceneTlacitka = new HashSet<>();
        this.posledneStlacene = new HashSet<>();
        this.x = 0;
        this.y = 0;
        this.posledneX = 0;
        this.posledneY = 0;
    }

    /**
     * Zaznamena stlacene tlacitko.
     *
     * @param me stlacene tlacidlo
     */
    @Override
    public void mousePressed(MouseEvent me) {
        stlaceneTlacitka.add(me.getButton());
    }

    /**
     * Zaznamena pustene tlacitko.
     *
     * @param me pustene tlacidlo
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        stlaceneTlacitka.remove(me.getButton());
    }

    /**
     * Zaznamena pohyb mysou.
     *
     * @param me pohyb mysi
     */
    @Override
    public void mouseMoved(MouseEvent me) {
        this.x = me.getX();
        this.y = me.getY();
    }

    /**
     * Aktualizuje posledne stlacene tlacidlo mysi.
     */
    public static void aktualizuj() {
        posledneStlacene.clear();
        posledneStlacene.addAll(stlaceneTlacitka);

        posledneX = x;
        posledneY = y;
    }

    /**
     * Vrati informaciu, ci je tlacidlo stlacene.
     *
     * @param tlacidlo
     * @return ci je dane tlacidlo stlacene
     */
    public static boolean jeStlacene(int tlacidlo) {
        return stlaceneTlacitka.contains(tlacidlo);
    }

    /**
     * Vrati informaciu, ci bolo dane tlacidlo naposledy stlacene.
     *
     * @param tlacidlo
     * @return ci bolo dane tlacidlo naposledy stlacene
     */
    public static boolean boloNaposledyStlacene(int tlacidlo) {
        return posledneStlacene.contains(tlacidlo);
    }

    /**
     * Vrati informaciu, ci bolo dane tlacidlo stlacene.
     *
     * @param t
     * @return ci bolo dane tlacidlo stlacene
     */
    public static boolean boloStlacene(int t) {
        return jeStlacene(t) && !boloNaposledyStlacene(t);
    }

    /**
     * Vrati informaciu, ci bolo dane tlacidlo pustene.
     *
     * @param t
     * @return ci bolo dane tlacidlo pustene
     */
    public static boolean boloPustene(int t) {
        return !jeStlacene(t) && boloNaposledyStlacene(t);
    }

    /**
     * Vrati X-ovu poziciu mysi.
     *
     * @return X-ova pozicia mysi
     */
    public static int getX() {
        return x;
    }

    /**
     * Vrati Y-ovu poziciu mysi.
     *
     * @return Y-ova pozicia mysi
     */
    public static int getY() {
        return y;
    }

}
