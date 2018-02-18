package zaymusEmanuel.bounce.predmety;

import zaymusEmanuel.bounce.C;

/**
 * PoziciaPredmetu - jednoducha pozicia X Y predmetu.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class PoziciaPredmetu {

    private int x;
    private int y;

    /**
     * Vytvori jednoduchu poziciu predmetu.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public PoziciaPredmetu(int x, int y) {
        this.x = x * C.ROZLISENIE + (C.ROZLISENIE / 2);
        this.y = y * C.ROZLISENIE;
    }

    /**
     * Posun objektu.
     *
     * @param posunX posun po X-ovej osi
     * @param posunY posun po Y-ovej osi
     */
    public void pohyb(int posunX, int posunY) {
        this.x += posunX;
        this.y += posunY;
    }

    /**
     * Vrati X-ovu poziciu predmetu.
     *
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati Y-ovu poziciu predmetu.
     *
     * @return y
     */
    public int getY() {
        return this.y;
    }

}
