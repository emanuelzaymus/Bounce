package zaymusEmanuel.bounce.predmety.preskokovePrekazky;

import zaymusEmanuel.bounce.predmety.akcnePredmety.IAkcnyPredmet;
import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.PredmetObrazkovy;
import zaymusEmanuel.bounce.stavy.StavBounce;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import zaymusEmanuel.bounce.C;

/**
 * Pumpa - predmet, ktory moze nafuknut loptu (zvacsit jej rozmery). Pumpa je
 * akcny predmet, ktory pre hraca predstavuje aj preskokovu prekazku.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Pumpa extends PredmetObrazkovy implements IAkcnyPredmet, IPreskokovaPrekazka {

    /**
     * Vytvori pumpu s umiestnenim a grafickou reprezentaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Pumpa(int x, int y) {
        super(C.PUMPA, x, y);
    }

    /**
     * Vrati obdlznik predstavujuci hranice predmetu.
     *
     * @return celkove hranice reprezentovane obdlznikom
     */
    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX(), super.getY(), C.ROZLISENIE, C.ROZLISENIE * 2);
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
     * Ak lopta zacne kolidovat s pumpou, metoda nastavi v lopte onafuknutie
     * (zvacsenie rozmerov).
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        lopta.nafukniSa();
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje vrch predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getVrch() {
        return new Rectangle2D.Double(super.getX() + 17, super.getY(), C.ROZLISENIE - 34, 2);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje spodok predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getSpodok() {
        return new Rectangle2D.Double(super.getX() + 17, super.getY() - 2 + C.ROZLISENIE * 2, C.ROZLISENIE - 34, 2);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje pravu stranu predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getPravaStrana() {
        return new Rectangle2D.Double(super.getX() - 2 + C.ROZLISENIE, super.getY() + 8, 2, C.ROZLISENIE * 2 - 16);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje lavu stranu predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getLavaStrana() {
        return new Rectangle2D.Double(super.getX(), super.getY() + 8, 2, C.ROZLISENIE * 2 - 16);
    }

}
