package zaymusEmanuel.bounce.predmety.akcnePredmety;

import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.PredmetObrazkovy;
import zaymusEmanuel.bounce.stavy.StavBounce;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import zaymusEmanuel.bounce.C;

/**
 * Ciel - predmet, kde sa musi hrac dostat, aby mohol pokracovat v dalsom
 * levely.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Ciel extends PredmetObrazkovy implements IAkcnyPredmet {

    private boolean otvoreny;

    /**
     * Vytvori ciel s umiestnenim a grafickou reprezentaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Ciel(int x, int y) {
        super(C.CIEL_ZATVORENY, x, y);
        this.otvoreny = false;
    }

    /**
     * Vrati obdlznik predstavujuci hranice predmetu.
     *
     * @return celkove hranice reprezentovane obdlznikom
     */
    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX() + 30, super.getY() + 45, 30, C.ROZLISENIE);
    }

    /**
     * Vykona jeden tik predmetu, posunie o pozadovane posuny X Y.
     *
     * @param posunX posun po X-ovej osi
     * @param posunY posun po Y-ovej osi
     */
    @Override
    public void tik(int posunX, int posunY) {
        super.pohyb(posunX, posunY);
    }

    /**
     * Vyrati informaciu, ci je ciel otvoreny a hrac moze pokracovat do dalsieho
     * levelu.
     *
     * @return otvoreny ci je ciel otvoreny
     */
    public boolean isOtvoreny() {
        return this.otvoreny;
    }

    /**
     * Otvori ciel, pre prechod do dalsieho levelu.
     */
    public void otvor() {
        super.zmenObrazok(C.CIEL_OTVORENY);
        this.otvoreny = true;
    }

    /**
     * Nastavi v aktualnom stave vyhru hraca pre prechod do dalsieho levelu.
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        if (this.otvoreny) {
            bounce.vyhra();
        }
    }

}
