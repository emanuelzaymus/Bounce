package zaymusEmanuel.bounce.stavy;

import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.vstupy.VstupKlavesnice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import zaymusEmanuel.bounce.C;

/**
 * StavVyhodnotenie - zobrazi vyhodnotenie po prehre alebo vyhre. Zobrazuje
 * dosiahnuty pocet bodov, celkovy cas, poradie posledneho dosiahnuteho levelu.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class StavVyhodnotenie implements IStav {

    private final Grafika obrazok;
    private final long pocetBodov;
    private final long cas;
    private final int level;
    private final String menoHraca;

    private final Text popis;

    /**
     * Vytvori uvodnu obrazovku podla vyhry/prehry a test na vypis vysledku.
     *
     * @param vyhral ci vyhral/prehral
     * @param pocetBodov pocet dosiahnutych bodov
     * @param cas celkovy cas hry
     * @param level poradie dosihnuteho levelu
     * @param menoHraca meno hraca
     */
    public StavVyhodnotenie(boolean vyhral, long pocetBodov, long cas, int level, String menoHraca) {
        if (vyhral) {
            this.obrazok = new Grafika(C.VYHODNOTENIE_VYHRA);
        } else {
            this.obrazok = new Grafika(C.VYHODNOTENIE_PREHRA);
        }
        this.pocetBodov = pocetBodov;
        this.cas = cas;
        this.level = level;
        this.menoHraca = menoHraca;

        this.popis = new Text(String.format("Počet bodov: %d    Celkový čas: %02d:%02d    Po level: %d", pocetBodov, (cas / 60), (cas % 60), level),
                new Font("Arial Black", Font.PLAIN, 25), Color.BLACK, C.SIRKA / 4 - 30, C.VYSKA / 4 * 3 + 20);
    }

    /**
     * Kontroluje ci neboli stlacene klavesy pre pokracovanie
     * (Space/Enter/Escape).
     *
     * @param hra aktualna hra
     */
    @Override
    public void tik(Hra hra) {
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_ENTER)) {
            hra.zmenStavNa(new StavVysledky(this.pocetBodov, this.cas, this.level, this.menoHraca));
        }
    }

    /**
     * Vykresli vsetky objekty v stave s grafickou reprezentaciou a text
     * vysledku odohratej hry.
     *
     * @param g2d
     */
    @Override
    public void render(Graphics2D g2d) {
        this.obrazok.render(g2d, 0, 0);
        this.popis.render(g2d);
    }

}
