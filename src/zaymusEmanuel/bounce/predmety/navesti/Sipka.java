package zaymusEmanuel.bounce.predmety.navesti;

import zaymusEmanuel.bounce.predmety.PredmetAnimovany;

/**
 * Sipka - navadza hraca na spravny smer posunu.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Sipka extends PredmetAnimovany implements INavest {

    /**
     * Vytvori sipku s umiestnenim a grafickou reprezentaciou.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Sipka(TypSipky typ, int x, int y) {
        super(typ.getNazovAnimacie(), 0, 30, x, y);
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
