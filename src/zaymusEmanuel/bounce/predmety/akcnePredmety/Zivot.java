package zaymusEmanuel.bounce.predmety.akcnePredmety;

import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.PredmetObrazkovy;
import zaymusEmanuel.bounce.stavy.StavBounce;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import zaymusEmanuel.bounce.C;

/**
 * Zivot - predstavuje dalsi zivot, ktory moze hrac zobrat.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Zivot extends PredmetObrazkovy implements IAkcnyPredmet {

    /**
     * Vytvori zivot s umiestnenim a grafickou reprezentaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Zivot(int x, int y) {
        super(C.ZIVOT, x, y);
    }

    /**
     * Vrati kruh predstavujuci hranice predmetu.
     *
     * @return celkove hranice reprezentovane kruhom
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
     * Ak lopta zacne kolidovat so zivotom, lopta zoberie zivot - metoda nastavi
     * v lopte odobratie zivota z akcnych predmetov.
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        bounce.odoberPredmet(this);
    }
}
