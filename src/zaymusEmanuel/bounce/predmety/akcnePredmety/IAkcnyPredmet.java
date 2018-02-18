package zaymusEmanuel.bounce.predmety.akcnePredmety;

import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.stavy.StavBounce;
import java.awt.Graphics2D;
import java.awt.Shape;

/**
 * IAkcnyPredmet - zoskupuje rozne predmety v hre, ktore mozu vykonavat osobitu
 * akciu a nie su prekazkou pre hraca (moze cez ne volne prechadzat).
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public interface IAkcnyPredmet {

    /**
     * Vrati jednoduchy tvar, ktory reprezentuje hranice predmetu.
     *
     * @return shape
     */
    Shape getCelkoveHranice();

    /**
     * Vykona jeden tik predmetu, posunie o pozadovane posuny X Y.
     *
     * @param posunX posun po X-ovej osi
     * @param posunY posun po Y-ovej osi
     */
    void tik(int posunX, int posunY);

    /**
     * Vykresli graficku reprezentaciu objektu.
     *
     * @param g2d
     */
    void render(Graphics2D g2d);

    /**
     * Spusti osobitu akciu akcneho predmetu.
     *
     * @param bounce aktualny stav hry
     * @param lopta aktualna lopta
     */
    void spustiAkciu(StavBounce bounce, Lopta lopta);

}
