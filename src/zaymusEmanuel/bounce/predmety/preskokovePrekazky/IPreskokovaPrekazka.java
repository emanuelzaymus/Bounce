package zaymusEmanuel.bounce.predmety.preskokovePrekazky;

import java.awt.Graphics2D;
import java.awt.Shape;

/**
 * IPreskokovaPrekazka - zoskupuje rozne predmety v hre, ktore su prekazkou pre
 * hraca (nemoze cez ne volne prechadzat - musi ich preskakovat). Nemaju ziadnu
 * specialnu akciu na vykonavanie.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public interface IPreskokovaPrekazka {

    /**
     * Vrati jednoduchy tvar (zvycajne obdlznik), ktory reprezentuje vrch
     * predmetu.
     *
     * @return shape
     */
    Shape getVrch();

    /**
     * Vrati jednoduchy tvar (zvycajne obdlznik), ktory reprezentuje spodok
     * predmetu.
     *
     * @return shape
     */
    Shape getSpodok();

    /**
     * Vrati jednoduchy tvar (zvycajne obdlznik), ktory reprezentuje pravu
     * stranu predmetu.
     *
     * @return shape
     */
    Shape getPravaStrana();

    /**
     * Vrati jednoduchy tvar (zvycajne obdlznik), ktory reprezentuje lavy stranu
     * predmetu.
     *
     * @return shape
     */
    Shape getLavaStrana();

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

}
