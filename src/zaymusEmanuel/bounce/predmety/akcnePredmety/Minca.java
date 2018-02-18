package zaymusEmanuel.bounce.predmety.akcnePredmety;

import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.PredmetAnimovany;
import zaymusEmanuel.bounce.stavy.StavBounce;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import zaymusEmanuel.bounce.C;

/**
 * Minca - predstavuje bod, ktory hrac zbiera, aby mohol prejist do dalsieho
 * levelu.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Minca extends PredmetAnimovany implements IAkcnyPredmet {

    /**
     * Vytvori mincu s jednoduchou animaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Minca(int x, int y) {
        super(C.ANIMACIA_MINCA_cesta, 0, 45, x, y);
    }

    /**
     * Vrati jednoduchy tvar, ktory reprezentuje hranice predmetu.
     *
     * @return shape celkove hranice reprezentovane kruhom.
     */
    @Override
    public Shape getCelkoveHranice() {
        return new Ellipse2D.Double(super.getX() + 14, super.getY() + 14, 61, 61);
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
     * Vykonava sa ked lopta zacne kolidovat s hranicami mince. V stave hry
     * odoberie mincu z akcnych predmetov
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        bounce.odoberPredmet(this);
    }

}
