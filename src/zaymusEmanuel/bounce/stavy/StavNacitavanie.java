package zaymusEmanuel.bounce.stavy;

import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Grafika;
import java.awt.Graphics2D;
import java.util.ArrayList;
import zaymusEmanuel.bounce.C;

/**
 * StavNacitavanie - zobrazi jednoduchu animaciu nacitavania.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class StavNacitavanie implements IStav {

    private final int pocetObrazkov = 90;

    private final Grafika uvodnaObrazovka;
    private final ArrayList<Grafika> obrazkyAnimacie;
    private int pocitadlo;
    private Grafika aktualnyObrazok;

    /**
     * Vytvori uvodnu obrazovku a animaciu nacitavania.
     */
    public StavNacitavanie() {
        this.uvodnaObrazovka = new Grafika(C.UVODA_OBRAZOVKA);
        this.obrazkyAnimacie = new ArrayList<>();
        for (int i = 0; i <= this.pocetObrazkov; i++) {
            String nazovSuboru = String.format(C.UVODNA_ANIMACIA_cesta, i);
            this.obrazkyAnimacie.add(new Grafika(nazovSuboru));
        }
        this.pocitadlo = 0;
    }

    /**
     * Meni obrazky animacie kazdu 60-tinu sekundy. Po skonceni animacie posle
     * spravu objektu hra na zmenu stavu na StavMenu.
     *
     * @param hra aktualna hra
     */
    @Override
    public void tik(Hra hra) {
        if (this.pocitadlo < this.obrazkyAnimacie.size()) {
            this.aktualnyObrazok = this.obrazkyAnimacie.get(this.pocitadlo);
        } else {
            hra.zmenStavNa(new StavHlavneMenu());
        }
        this.pocitadlo++;
    }

    /**
     * Vykresli vsetky objekty v stave s grafickou reprezentaciou.
     *
     * @param g2d
     */
    @Override
    public void render(Graphics2D g2d) {
        this.uvodnaObrazovka.render(g2d, 0, 0);
        this.aktualnyObrazok.render(g2d, (C.SIRKA / 2) - (this.aktualnyObrazok.getSirkaObrazku() / 2), (int) (C.VYSKA * 0.6));
    }

}
