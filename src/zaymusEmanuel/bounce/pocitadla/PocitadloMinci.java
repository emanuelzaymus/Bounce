package zaymusEmanuel.bounce.pocitadla;

import zaymusEmanuel.bounce.C;

/**
 * PocitadloMinci - potomok predka Pocitadlo, zaznamenava pocet ziskanych minci.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class PocitadloMinci extends Pocitadlo {

    /**
     * Vytvori jednoduche pocitadlo minci.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public PocitadloMinci(int x, int y) {
        super(C.PENIAZE, String.format("0/%d", 0), 0, 0, x, y);
    }

    /**
     * Inkrementacia poctu ziskanych minci.
     */
    @Override
    public void pridaj() {
        super.pridaj();
        this.setPopis();
    }

    /**
     * Vrati informaciu, ci je aktulany pocet vacsi-rovny cielovemu poctu.
     *
     * @return ci uz nazberal cielovy/pozadovany pocet minci
     */
    public boolean nazberalDost() {
        if (super.getAktualnyPocet() >= super.getCielovyPocet()) {
            super.zmenObrazok(C.PENIAZE_CIELOVE);
            return true;
        }
        return false;
    }

    /**
     * Vynuluje pocitadlo, ak je to potrebne, tak zapocita aktualny pocet do
     * celkoveho poctu
     *
     * @param zaratatNapocitane informacia, ci sa ma aktualny pocet zapocitat do
     * celkoveho poctu
     * @param cielovyPocet novy cielovy pocet minci
     */
    public void vynuluj(boolean zaratatNapocitane, int cielovyPocet) {
        super.setZaciatocnyACielovyPocet(zaratatNapocitane, 0, cielovyPocet);
        super.zmenObrazok(C.PENIAZE);
        this.setPopis();
    }

    /**
     * Nastavy popis na zobrazenie.
     */
    private void setPopis() {
        super.setPopis(String.format("%d/%d", super.getAktualnyPocet(), super.getCielovyPocet()));
    }

}
