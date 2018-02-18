package zaymusEmanuel.bounce.stavy;

import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.NacitavacMap;
import zaymusEmanuel.bounce.pocitadla.PocitadloMinci;
import zaymusEmanuel.bounce.pocitadla.PocitadloZivotov;
import zaymusEmanuel.bounce.pocitadla.Stopky;
import zaymusEmanuel.bounce.predmety.akcnePredmety.Ciel;
import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.predmety.akcnePredmety.IAkcnyPredmet;
import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Stena;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.IPreskokovaPrekazka;
import zaymusEmanuel.bounce.predmety.akcnePredmety.Minca;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Voda;
import zaymusEmanuel.bounce.predmety.akcnePredmety.Zivot;
import zaymusEmanuel.bounce.predmety.navesti.INavest;
import zaymusEmanuel.bounce.vstupy.VstupKlavesnice;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.predmety.FarbaLopty;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Krabica;
import zaymusEmanuel.bounce.stavy.okna.StavOknoOdhlasenie;
import zaymusEmanuel.bounce.vstupy.VstupMysi;

/**
 * StavBounce - hlavny stav aplikacie, predstavuje samotnu hru, nesie informacie
 * o vsetkych objektoch v hre.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class StavBounce implements IStav {

    private Grafika pozadie;

    private String nazovLevelu;
    private ArrayList<IPreskokovaPrekazka> prekazky;
    private ArrayList<IAkcnyPredmet> akcnePredmety;
    private ArrayList<INavest> ostatnePredmety;

    private Lopta lopta;

    private int posunProstrediaX;
    private int posunProstrediaY;
    private int poradieLevelu;
    private int poradiePoslednehoLevelu;

    private PocitadloMinci pocitadloMinci;
    private PocitadloZivotov pocitadloZivotov;
    private Stopky stopky;
    private boolean spustene;
    private Grafika pauza;
    private boolean koniec;
    private boolean vyhral;

    private String menoHraca;
    private Text popis;
    private int cielovyPocetMinci;
    private final FarbaLopty vybrataFarba;
    private TlacitkoObrazkove tlacitkoSpat;
    private int hranicaY = 0;

    /**
     * Vytvori vsetky potrebne atributy pre hru, ak je StavBounce vytvareny prvy
     * krat, poziada hraca o zadanie svojho mena. Nasledne nacita prvy level.
     */
    public StavBounce(String meno, FarbaLopty vybrataFarba) {
        this.pozadie = new Grafika(C.POZADIE);
        this.nazovLevelu = null;
        this.prekazky = null;
        this.akcnePredmety = null;
        this.ostatnePredmety = null;

        this.lopta = null;

        this.posunProstrediaX = 0;
        this.posunProstrediaY = 0;
        this.poradieLevelu = C.ZACIATOCNY_LEVEL;
        this.poradiePoslednehoLevelu = C.ZACIATOCNY_LEVEL - 1;

        this.spustene = true;

        this.menoHraca = meno;

        this.popis = null;

        this.pocitadloZivotov = new PocitadloZivotov(3, 200, 15);
        this.pocitadloMinci = new PocitadloMinci(15, 15);
        this.stopky = new Stopky(350, 15);

        this.vybrataFarba = vybrataFarba;
        this.nacitajLevel(this.poradieLevelu);

        this.pauza = new Grafika(C.PAUZA);
        this.koniec = false;
        this.vyhral = false;

        this.tlacitkoSpat = new TlacitkoObrazkove(50, 50, new Ellipse2D.Double(50, 50, 112, 112), new Grafika(C.TLACITKO_SPAT), new Grafika(C.TLACITKO_SPAT_OZNACENE), new Grafika(C.TLACITKO_SPAT_STLACENE));
    }

    /**
     * Vykona jeden tik v stave aplikacie. Skontroluje vstupy hraca cez
     * klavesnicu a mys. Posiela spravy tik vsetkym potrebym atributom.
     *
     * @param hra aktualna hra
     */
    @Override
    public void tik(Hra hra) {

        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_ENTER) || VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_SPACE)) {
            this.spustene = !this.spustene;
        }
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_BACK_SPACE)) {
            hra.zmenStavNa(new StavProfil(this.menoHraca));
        }

        if (this.spustene) {
            this.lopta.tik(this);

            for (IPreskokovaPrekazka prekazka : this.prekazky) {
                if (prekazka instanceof Stena || (prekazka instanceof Voda)) {
                    prekazka.tik(this.posunProstrediaX, this.posunProstrediaY);
                }
            }
            for (IAkcnyPredmet predmet : this.akcnePredmety) {
                predmet.tik(this.posunProstrediaX, this.posunProstrediaY);
            }
            for (INavest navest : this.ostatnePredmety) {
                navest.tik(this.posunProstrediaX, this.posunProstrediaY);
            }

            this.stopky.tik();

            if (this.koniec) {
                hra.zmenStavNa(new StavVyhodnotenie(this.vyhral, this.pocitadloMinci.getCelkovyPocet(),
                        this.stopky.getCelkovyPocet(), this.poradieLevelu, this.menoHraca));
            }
        } else {
            this.tlacitkoSpat.setOznacene(this.tlacitkoSpat.getHranice().intersects(new Rectangle(VstupMysi.getX(), VstupMysi.getY(), 1, 1)));
            if (this.tlacitkoSpat.isOznacene()) {
                this.tlacitkoSpat.setStlacene(VstupMysi.jeStlacene(MouseEvent.BUTTON1));
                if (VstupMysi.boloPustene(MouseEvent.BUTTON1)) {
                    hra.zmenStavNa(new StavProfil(this.menoHraca));
                }
            }
        }

    }

    /**
     * Vykresli vsetky objekty v stave s grafickou reprezentaciou.
     *
     * @param g2d
     */
    @Override
    public void render(Graphics2D g2d) {
        this.pozadie.render(g2d, 0, 0);
        for (IPreskokovaPrekazka prekazka : this.prekazky) {
            prekazka.render(g2d);
        }
        for (INavest navest : this.ostatnePredmety) {
            navest.render(g2d);
        }
        for (IAkcnyPredmet predmet : this.akcnePredmety) {
            predmet.render(g2d);
        }
        this.lopta.render(g2d);

        this.pocitadloMinci.render(g2d);
        this.pocitadloZivotov.render(g2d);
        this.stopky.render(g2d);
        this.popis.render(g2d);

        if (!this.spustene) {
            this.pauza.render(g2d, 0, 0);
            this.tlacitkoSpat.render(g2d);
        }
    }

    /**
     * Vrati klon preskovovych prekazok.
     *
     * @return prekazky
     */
    public ArrayList<IPreskokovaPrekazka> getPrekazky() {
        return (ArrayList<IPreskokovaPrekazka>) this.prekazky.clone();
    }

    /**
     * Vrati klon akcnych predmetov.
     *
     * @return akcnePredmety
     */
    public ArrayList<IAkcnyPredmet> getAkcnePredmety() {
        return (ArrayList<IAkcnyPredmet>) this.akcnePredmety.clone();
    }

    /**
     * Nastavy posun po X-ovej osi pre cele prostredie (prekazky a akcne
     * predmety).
     *
     * @param posunX posun po X-ovej osi
     */
    public void setPosunProstrediaX(int posunX) {
        this.posunProstrediaX = posunX;
    }

    /**
     * Nastavy posun po Y-ovej osi pre cele prostredie (prekazky a akcne
     * predmety).
     *
     * @param posunY posun po Y-ovej osi
     */
    public void setPosunProstrediaY(int posunY) {
        this.posunProstrediaY = posunY;
    }

    //nepouzite (iba v Krabica)
    public int getPosunProstrediaX() {
        return this.posunProstrediaX;
    }

    /**
     * Ubere hacovy jeden zivot a skontroluje, ci to bol uz posledny zivot.
     * Posle spravu na nacitanie levelu.
     */
    public void prehra() {
        this.pocitadloZivotov.uber();
        if (this.pocitadloZivotov.uzUmrel()) {
            this.vyhral = false;
            this.koniec = true;
        }
        this.nacitajLevel(this.poradieLevelu);
    }

    /**
     * Inkrementuje poradie leveu a skontroluje, ci uz hrac nevyhral vsetky
     * levely. Posle spravu na nacitanie levelu.
     */
    public void vyhra() {
        this.poradieLevelu++;
        if (this.poradieLevelu > C.POCET_LEVELOV) {
            this.vyhral = true;
            this.koniec = true;
            this.poradieLevelu = C.POCET_LEVELOV;
        }
        this.nacitajLevel(this.poradieLevelu);
    }

    /**
     * Odoberie akcny predet odovzdany parametrom.
     *
     * @param predmet
     */
    public void odoberPredmet(IAkcnyPredmet predmet) {
        this.akcnePredmety.remove(predmet);
        if (predmet instanceof Minca) {
            this.pridajBod();
        } else if (predmet instanceof Zivot) {
            this.pocitadloZivotov.pridaj();
        }
    }

    /**
     * Posle spravu objektu NacitavacMap na nacitanie daneho levelu. Nasledne
     * posiela spravy na ziskanie vsetkych potrebnych nacitanych objektov daneho
     * levelu. Vynuluje pocitadla.
     *
     * @param poradieLevelu
     */
    private void nacitajLevel(int poradieLevelu) {
        if (this.koniec) {
            this.pocitadloMinci.vynuluj(true, 0);
            return;
        }

        NacitavacMap nacitavac = new NacitavacMap();

        nacitavac.nacitajLevel(poradieLevelu, this.vybrataFarba);

        this.popis = new Text(this.menoHraca + "     " + nacitavac.getNazovLevelu(), new Font("Arial Black", Font.BOLD, 27), Color.BLACK, 750, 55);
        this.prekazky = nacitavac.getPrekazky();
        this.akcnePredmety = nacitavac.getAkcnePredmety();
        this.ostatnePredmety = nacitavac.getNavesti();

        this.lopta = nacitavac.getLopta();

        this.pocitadloMinci.vynuluj((this.poradieLevelu != this.poradiePoslednehoLevelu), nacitavac.getPocetMinci());
        this.stopky.vynuluj();

        this.posunProstrediaX = 0;
        this.posunProstrediaY = 0;

        this.poradiePoslednehoLevelu = this.poradieLevelu;

        this.hranicaY = (nacitavac.getHranicaY() + 4) * C.ROZLISENIE;
    }

    /**
     * Prida bod na pocitadle minci a skontroluje, ci uz hrac nazbieral
     * dostatocny pocet minci na otvorenie ciela.
     */
    private void pridajBod() {
        this.pocitadloMinci.pridaj();

        if (this.pocitadloMinci.nazberalDost()) {
            for (IAkcnyPredmet predmet : this.akcnePredmety) {
                if (predmet instanceof Ciel) {
                    ((Ciel) predmet).otvor();
                }
            }
        }
    }

    public int getHranicaY() {
        return this.hranicaY;
    }

}
