package zaymusEmanuel.bounce.predmety.navesti;

import java.awt.Graphics2D;

/**
 * INavest - predstavuje predmety, ktore nemaju v aplikacii ziadnu specialnu
 * funkciu. Navadzaju hraca na spravny smer posunu. Nie su prekazkou pre hraca.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public interface INavest {

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
