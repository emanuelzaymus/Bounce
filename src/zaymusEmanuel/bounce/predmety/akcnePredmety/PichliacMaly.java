package zaymusEmanuel.bounce.predmety.akcnePredmety;

import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.PredmetObrazkovy;
import zaymusEmanuel.bounce.stavy.StavBounce;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import zaymusEmanuel.bounce.C;

/**
 * PichliacMaly - predstavuje malu nastrahu pre loptu, ktoru moze prepichnut.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class PichliacMaly extends PredmetObrazkovy implements IAkcnyPredmet {

    /**
     * Vytvori maly pichliac s umiestnenim a grafickou reprezentaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public PichliacMaly(int x, int y) {
        super(C.PICHLIAC_MALY, x, y);
    }

    /**
     * Vrati stvorec predstavujuci hranice predmetu.
     *
     * @return celkove hranice reprezentovane stvorcom
     */
    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX() + 22, super.getY() + 45, 45, 45);
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
     * Ak lopta zacne kolidovat s pichliacom, nastavi v lopte prepichnutie.
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        lopta.prepichlaSiSa();
    }

}
