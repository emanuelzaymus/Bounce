package zaymusEmanuel.bounce.stavy;

import java.awt.Font;
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
import zaymusEmanuel.bounce.stavy.okna.StavOknoOdhlasenie;

/**
 * StavMenuPrihlaseny - zobrazi menu pre hraca s moznostami: Hraj, Vysledky,
 * Koniec.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class StavMenuPrihlaseny implements IStav {

    private final Grafika uvodnaObrazovka;
    private TlacitkoTextove[] tlacitka;
    private int vybrataMoznost;
    private final String menoHraca;

    /**
     * Vytvori uvodnu obrazovku a tri tlacitka: Hraj, Vysledky, Koniec.
     */
    public StavMenuPrihlaseny(String menoHraca) {
        this.uvodnaObrazovka = new Grafika(C.UVODA_OBRAZOVKA);

        this.tlacitka = new TlacitkoTextove[]{
            new TlacitkoTextove("HRAJ", new Font("Arial Black", Font.PLAIN, 38), C.SIRKA / 3 * 1, C.VYSKA / 9 * 6),
            new TlacitkoTextove("VÝSLEDKY", new Font("Arial Black", Font.PLAIN, 38), C.SIRKA / 3 * 2, C.VYSKA / 9 * 6),
            new TlacitkoTextove("MANUÁL", new Font("Arial Black", Font.PLAIN, 38), C.SIRKA / 3 * 1, C.VYSKA / 9 * 7),
            new TlacitkoTextove("ODHLÁSIŤ SA", new Font("Arial Black", Font.PLAIN, 38), C.SIRKA / 3 * 2, C.VYSKA / 9 * 7)};

        this.vybrataMoznost = Integer.MAX_VALUE / 2 + 1;
        this.menoHraca = menoHraca;
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
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_UP)) {
            this.vybrataMoznost -= 2;
        }
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_DOWN)) {
            this.vybrataMoznost += 2;
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
            this.tlacitka[i].setOznacene(i == this.vybrataMoznost % 4);
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
        switch (this.vybrataMoznost % 4) {
            case 0:
                hra.zmenStavNa(new StavProfil(this.menoHraca));
                break;
            case 1:
                hra.zmenStavNa(new StavVysledky(this.menoHraca));
                break;
            case 2:
                hra.zmenStavNa(new StavOknoManual(this.menoHraca));
                break;
            case 3:
                hra.zmenStavNa(new StavOknoOdhlasenie(this.menoHraca));
                break;
        }
    }

}
