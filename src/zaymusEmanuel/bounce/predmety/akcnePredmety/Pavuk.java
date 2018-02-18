package zaymusEmanuel.bounce.predmety.akcnePredmety;

import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.PredmetObrazkovy;
import zaymusEmanuel.bounce.stavy.StavBounce;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import zaymusEmanuel.bounce.C;

/**
 * Pavuk - predstavuje nastrahu pre loptu, ktoru moze prepichnut.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Pavuk extends PredmetObrazkovy implements IAkcnyPredmet {

    private final boolean maIstVertikalne;
    private final int delta;
    private final int maxPosun;

    private int doterajsiPosun;
    private int znamienko;

    /**
     * Vytvori pavuka s umiestnenim a grafickou reprezentaciou.
     *
     * @param maIstVertikalne ci sa ma pavuk pohybovat vertikalne alebo
     * horizontalne
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Pavuk(boolean maIstVertikalne, int x, int y) {
        super(C.PAVUK, x, y);
        this.maIstVertikalne = maIstVertikalne;
        this.delta = 3;
        this.maxPosun = C.ROZLISENIE * 2;

        this.doterajsiPosun = 0;
        this.znamienko = 1;
    }

    /**
     * Vrati stvorec predstavujuci hranice predmetu.
     *
     * @return celkove hranice reprezentovane stvorcom
     */
    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX(), super.getY(), C.ROZLISENIE * 2, C.ROZLISENIE * 2);
    }

    /**
     * Vykona jeden tik predmetu, posunie o pozadovane posuny X Y, pri com sa
     * pavuk este osobitne pohybuje horizontalne alebo vertikalne.
     *
     * @param posunX posun po X-ovej osi
     * @param posunY posun po Y-ovej osi
     */
    @Override
    public void tik(int posunX, int posunY) {
        if (this.doterajsiPosun <= this.maxPosun) {
            this.doterajsiPosun += this.delta;
        } else {
            this.znamienko *= -1;
            this.doterajsiPosun = 0;
        }
        if (this.maIstVertikalne) {
            super.pohyb(posunX, posunY + this.delta * this.znamienko);
        } else {
            super.pohyb(posunX + this.delta * this.znamienko, posunY);
        }
    }

    /**
     * Ak lopta zacne kolidovat s pavukom, nastavi v lopte prepichnutie.
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        lopta.prepichlaSiSa();
    }

}
