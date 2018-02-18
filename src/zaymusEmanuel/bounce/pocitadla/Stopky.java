package zaymusEmanuel.bounce.pocitadla;

import zaymusEmanuel.bounce.C;

/**
 * Stopky - potomok predka Pocitadlo, zaznamenava cas.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Stopky extends Pocitadlo {

    private int pocitadlo;

    /**
     * Vytvori jednoduche pocitadlo sekund a minut.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Stopky(int x, int y) {
        super(C.STOPKY, "00:00", 0, 0, x, y);
        this.pocitadlo = 0;
    }

    /**
     * Inkrementuje pocet sekund kazdu sekundu.
     */
    public void tik() {
        if (++this.pocitadlo >= 60) {
            super.pridaj();
            this.setPopis();
            this.pocitadlo = 0;
        }
    }

    /**
     * Vynuluje pocitadlo.
     */
    public void vynuluj() {
        super.setZaciatocnyACielovyPocet(true, 0, 0);
        this.setPopis();
    }

    /**
     * Nastavi spravny format minut a sekund.
     */
    private void setPopis() {
        super.setPopis(String.format("%02d:%02d", super.getAktualnyPocet() / 60, super.getAktualnyPocet() % 60));
    }

}
