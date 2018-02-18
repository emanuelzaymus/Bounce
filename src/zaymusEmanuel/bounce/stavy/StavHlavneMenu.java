package zaymusEmanuel.bounce.stavy;

import java.awt.Font;
import zaymusEmanuel.bounce.stavy.okna.StavOknoPrihlasenie;
import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.vstupy.VstupKlavesnice;
import zaymusEmanuel.bounce.vstupy.VstupMysi;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.stavy.okna.StavOknoManual;

/**
 * StavMenu - zobrazi menu pre hraca s moznostami: Hraj, Vysledky, Koniec.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class StavHlavneMenu implements IStav {

    private final Grafika uvodnaObrazovka;
    private TlacitkoTextove[] tlacitka;
    private int vybrataMoznost;

    /**
     * Vytvori uvodnu obrazovku a tri tlacitka: Hraj, Vysledky, Koniec.
     */
    public StavHlavneMenu() {
        this.uvodnaObrazovka = new Grafika(C.UVODA_OBRAZOVKA);

        this.tlacitka = new TlacitkoTextove[]{
            new TlacitkoTextove("PRIHLÁSIŤ SA", new Font("Arial Black", Font.PLAIN, 42), C.SIRKA / 3 * 1 + 70, C.VYSKA / 4 * 3),
            new TlacitkoTextove("KONIEC", new Font("Arial Black", Font.PLAIN, 42), C.SIRKA / 3 * 2 + 70, C.VYSKA / 4 * 3)};

        this.vybrataMoznost = Integer.MAX_VALUE / 2 + 1;
    }

    /**
     * Kontroluje ci neboli stlacene klavesy pre vyber jednej z moznosti. Tiez
     * je kontrolovana pozicia mysi.
     *
     * @param hra aktualna hra
     */
    public void tik(Hra hra) {
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_LEFT)) {
            this.vybrataMoznost--;
        }
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_RIGHT)) {
            this.vybrataMoznost++;
        }

        boolean kliknute = false;

        for (int i = 0; i < this.tlacitka.length; i++) {
            if (this.tlacitka[i].getHranice().intersects(new Rectangle(VstupMysi.getX(), VstupMysi.getY(), 1, 1))) {
                this.vybrataMoznost = i + 1000000007 + 1;
                kliknute = VstupMysi.boloStlacene(MouseEvent.BUTTON1);
            }
        }
        if (kliknute || VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_ENTER)) {
            this.vyber(hra);
        }
    }

    /**
     * Vykresli uvodnu obrazovku a vsetky tlacitka.
     *
     * @param g2d
     */
    @Override
    public void render(Graphics2D g2d) {
        this.uvodnaObrazovka.render(g2d, 0, 0);
        for (int i = 0; i < this.tlacitka.length; i++) {
            this.tlacitka[i].setOznacene(i == this.vybrataMoznost % 2);
        }

        for (TlacitkoTextove tlacitko : this.tlacitka) {
            tlacitko.render(g2d);
        }
    }

    /**
     * Vykona vybranu moznost.
     *
     * @param hra aktualna hra
     */
    private void vyber(Hra hra) {
        switch (this.vybrataMoznost % 2) {
            case 0:
                hra.zmenStavNa(new StavOknoPrihlasenie());
                break;
            case 1:
                hra.stop();
                break;
        }
    }

}
