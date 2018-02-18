package zaymusEmanuel.bounce.predmety.preskokovePrekazky;

import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.PredmetObrazkovy;
import java.awt.geom.Rectangle2D;
import zaymusEmanuel.bounce.C;

/**
 * Stena - predmet, ktory pre hraca predstavuje preskokovu prekazku.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Stena extends PredmetObrazkovy implements IPreskokovaPrekazka {

    /**
     * Vytvori stenu s umiestnenim a grafickou reprezentaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Stena(int x, int y) {
        super(C.STENA, x, y);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje vrch predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getVrch() {
        return new Rectangle2D.Double(super.getX() + 4, super.getY(), C.ROZLISENIE - 8, 2);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje spodok predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getSpodok() {
        return new Rectangle2D.Double(super.getX() + 4, super.getY() - 2 + C.ROZLISENIE, C.ROZLISENIE - 8, 2);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje pravu stranu predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getPravaStrana() {
        return new Rectangle2D.Double(super.getX() - 2 + C.ROZLISENIE, super.getY() + 8, 2, C.ROZLISENIE - 16);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje lavu stranu predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getLavaStrana() {
        return new Rectangle2D.Double(super.getX(), super.getY() + 8, 2, C.ROZLISENIE - 16);
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
}
