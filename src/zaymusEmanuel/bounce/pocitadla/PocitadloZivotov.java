package zaymusEmanuel.bounce.pocitadla;

import zaymusEmanuel.bounce.C;

/**
 * PocitadloZivotov - potomok predka Pocitadlo, zaznamenava pocet zivotov.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class PocitadloZivotov extends Pocitadlo {

    /**
     * Vytvori jednoduche pocitadlo zostavajucich zivotov.
     *
     * @param zaciatocnyPocet zaciatocny pocet zivotov
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public PocitadloZivotov(int zaciatocnyPocet, int x, int y) {
        super(C.STAV_ZIVOTOV, "" + zaciatocnyPocet, zaciatocnyPocet, 0, x, y);
    }

    /**
     * Inkrementacia poctu zivotov.
     */
    @Override
    public void pridaj() {
        super.pridaj();
        this.aktualizujPopis();
    }

    /**
     * Dekrementacia poctu zivotov.
     */
    @Override
    public void uber() {
        super.uber();
        this.aktualizujPopis();
    }

    /**
     * Vrati inforaciu ci uz hrac stratil vsetky zivoty.
     *
     * @return ci uz hrac dosiahol cielovy pocet (0)
     */
    public boolean uzUmrel() {
        return super.getAktualnyPocet() <= super.getCielovyPocet();
    }

    /**
     * Aktualizuje popis.
     */
    private void aktualizujPopis() {
        super.setPopis("" + super.getAktualnyPocet());
    }

}
