package zaymusEmanuel.bounce.stavy;

import java.awt.Color;
import java.awt.Font;
import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.vstupy.VstupKlavesnice;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.vstupy.VstupMysi;

/**
 * StavVysledky - zobrazi vysledky zo vsetkych doteraz odohranych hier.
 * Zobrazuje dosiahnuty pocet bodov, celkovy cas, poradie posledneho
 * dosiahnuteho levelu, meno hraca.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class StavVysledky implements IStav {

    private final Grafika pozadie = new Grafika(C.POZADIE_OKRAJ);
    private Zaznamy zaznamy;
    private Text nadpis = new Text("TABUĽKA NAJLEPŠÍCH VÝSLEDKOV", new Font("Arial Black", Font.PLAIN, 30), Color.BLACK, 220, 150);

    private long pocetBodov;
    private long cas;
    private int level;
    private String menoHraca;

    private boolean istDoProfilu;
    private TlacitkoObrazkove tlacitkoSpat;

    /**
     * Konstruktor s pridanim noveho zaznamu o hre. Posle spravu pre nacitanie a
     * usporiadanie zaznmaov o hrach.
     *
     * @param pocetBodov pocet dosiahnutych bodov
     * @param cas celkovy cas hry
     * @param level poradie dosihnuteho levelu
     * @param menoHraca meno hraca
     */
    public StavVysledky(long pocetBodov, long cas, int level, String menoHraca) {
        this.pocetBodov = pocetBodov;
        this.cas = cas;
        this.level = level;
        this.menoHraca = menoHraca;

        this.istDoProfilu = true;
        this.tlacitkoSpat = new TlacitkoObrazkove(50, 50, new Ellipse2D.Double(50, 50, 112, 112), new Grafika(C.TLACITKO_SPAT), new Grafika(C.TLACITKO_SPAT_OZNACENE));

        this.zaznamy = new Zaznamy(C.VYSLEDKY_BOUNCE, 220, 200);
        try {
            this.zaznamy.prepisAkJeLepsi(new ZaznamOHre(pocetBodov, cas, level, menoHraca));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        this.zaznamy.usporiadajZostupnePodlaBodov();
    }

    /**
     * Konstruktor posle spravu pre nacitanie a usporiadanie zaznamov o hrach.
     */
    public StavVysledky(String menoHraca) {
        this.menoHraca = menoHraca;
        this.istDoProfilu = false;
        this.tlacitkoSpat = new TlacitkoObrazkove(50, 50, new Ellipse2D.Double(50, 50, 112, 112), new Grafika(C.TLACITKO_SPAT), new Grafika(C.TLACITKO_SPAT_OZNACENE), new Grafika(C.TLACITKO_SPAT_STLACENE));

        this.zaznamy = new Zaznamy(C.VYSLEDKY_BOUNCE, 220, 200);
        try {
            this.zaznamy.nacitajZoSuboru();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        this.zaznamy.usporiadajZostupnePodlaBodov();
    }

    /**
     * Kontroluje ci neboli stlacene klavesy pre pokracovanie
     * (Space/Enter/Escape).
     *
     * @param hra aktualna hra
     */
    @Override
    public void tik(Hra hra) {
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_BACK_SPACE) || VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_ENTER)) {
            if (this.istDoProfilu) {
                hra.zmenStavNa(new StavProfil(this.pocetBodov, this.cas, this.level, this.menoHraca));
            } else {
                hra.zmenStavNa(new StavMenuPrihlaseny(this.menoHraca));
            }
        }

        this.tlacitkoSpat.setOznacene(this.tlacitkoSpat.getHranice().intersects(new Rectangle(VstupMysi.getX(), VstupMysi.getY(), 1, 1)));
        if (this.tlacitkoSpat.isOznacene()) {
            this.tlacitkoSpat.setStlacene(VstupMysi.jeStlacene(MouseEvent.BUTTON1));
            if (VstupMysi.boloPustene(MouseEvent.BUTTON1)) {
                hra.zmenStavNa(new StavMenuPrihlaseny(this.menoHraca));
            }
        }
    }

    /**
     * Vykresli vsetky objekty v stave s grafickou reprezentaciou a vypisy o
     * hrach.
     *
     * @param g2d
     */
    @Override
    public void render(Graphics2D g2d) {
        this.pozadie.render(g2d, 0, 0);
        this.nadpis.render(g2d);
        this.zaznamy.render(g2d, true);
        this.tlacitkoSpat.render(g2d);
    }

}
