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
 * Sfukovac - predmet, ktory moze sfuknut nafuknutu loptu (zmensit jej rozmery
 * do povodneho stavu). Sfukovac je akcny predmet, ktory pre hraca predstavuje
 * aj preskokovu prekazku.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Sfukovac extends PredmetObrazkovy implements IAkcnyPredmet, IPreskokovaPrekazka {

    /**
     * Vytvori sfukovac s umiestnenim a grafickou reprezentaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Sfukovac(int x, int y) {
        super(C.SFUKOVAC, x, y);
    }

    /**
     * Vrati obdlznik predstavujuci hranice predmetu.
     *
     * @return celkove hranice reprezentovane obdlznikom
     */
    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX() + 10, super.getY() + 17, 70, 73);
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
     * Ak lopta zacne kolidovat so sfukovacom, metoda nastavi v lopte sfuknutie
     * lopty (zmensenie rozmerov do povodneho stavu).
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        lopta.zmensiSa();
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje vrch predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getVrch() {
        return new Rectangle2D.Double(super.getX() + 10 + 4, super.getY() + 17, 70 - 8, 2);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje spodok predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getSpodok() {
        return new Rectangle2D.Double(super.getX() + 10 + 4, super.getY() - 2 + C.ROZLISENIE, 70 - 8, 2);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje pravu stranu predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getPravaStrana() {
        return new Rectangle2D.Double(super.getX() - 2 + 80, super.getY() + 17, 2, 73);
    }

    /**
     * Vrati jednoduchy obdlznik, ktory reprezentuje lavu stranu predmetu.
     *
     * @return shape
     */
    @Override
    public Rectangle2D getLavaStrana() {
        return new Rectangle2D.Double(super.getX() + 10, super.getY() + 17, 2, 73);
    }
}
