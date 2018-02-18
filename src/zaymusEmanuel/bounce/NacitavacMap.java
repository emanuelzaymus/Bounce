package zaymusEmanuel.bounce;

import zaymusEmanuel.bounce.predmety.akcnePredmety.Ciel;
import zaymusEmanuel.bounce.predmety.akcnePredmety.IAkcnyPredmet;
import zaymusEmanuel.bounce.vynimky.NasliSaDveLoptyException;
import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.navesti.Sipka;
import zaymusEmanuel.bounce.predmety.navesti.TypSipky;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Stena;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.IPreskokovaPrekazka;
import zaymusEmanuel.bounce.predmety.akcnePredmety.Minca;
import zaymusEmanuel.bounce.predmety.akcnePredmety.Pavuk;
import zaymusEmanuel.bounce.predmety.akcnePredmety.Pichliac;
import zaymusEmanuel.bounce.predmety.akcnePredmety.PichliacMaly;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Pumpa;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Sfukovac;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Voda;
import zaymusEmanuel.bounce.predmety.akcnePredmety.Zivot;
import zaymusEmanuel.bounce.predmety.navesti.INavest;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import zaymusEmanuel.bounce.predmety.FarbaLopty;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Elektrina;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Krabica;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Vytah;

/**
 * NacitavacMap - nacitava levely.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class NacitavacMap {

    private String nazovLevelu;

    private ArrayList<IPreskokovaPrekazka> prekazky;
    private ArrayList<IAkcnyPredmet> akcnePredmety;
    private ArrayList<INavest> navesti;
    private int pocetMinci;

    private Lopta lopta;

    private int hranicaY;

    /**
     * Vytvori NacitavacMap.
     */
    public NacitavacMap() {
        this.nazovLevelu = null;

        this.prekazky = new ArrayList<>();
        this.pocetMinci = 0;
        this.lopta = null;

        this.akcnePredmety = new ArrayList<>();
        this.navesti = new ArrayList<>();
    }

    /**
     * Vytvori kompletny level. Naplni vsetky kontajnere prislusnymi objektami.
     *
     * @param poradieSuboru poradie levelu
     */
    public void nacitajLevel(int poradieSuboru, FarbaLopty vybrataFarba) {
        File subor = new File("data/levely/level" + poradieSuboru + ".dat");

        int y = 0;
        try (Scanner sc = new Scanner(subor)) {
            if (sc.hasNextLine()) {
                this.nazovLevelu = sc.nextLine();
            }
            String riadok;
            char[] znaky;

            riadok = sc.nextLine();
            Scanner scRiadku = new Scanner(riadok);
            this.pocetMinci = scRiadku.nextInt();

            while (sc.hasNextLine()) {
                riadok = sc.nextLine();
                znaky = riadok.toCharArray();
                for (int x = 0; x < znaky.length; x++) {
                    switch (znaky[x]) {
                        case '#':
                            this.prekazky.add(new Stena(x, y));
                            break;

                        case 'V':
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'X':
                            this.akcnePredmety.add(new Pichliac(x, y));
                            break;

                        case 'x':
                            this.akcnePredmety.add(new Pichliac(x, y));
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'I':
                            this.akcnePredmety.add(new PichliacMaly(x, y));
                            break;

                        case 'i':
                            this.akcnePredmety.add(new PichliacMaly(x, y));
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'K':
                            this.akcnePredmety.add(new Pavuk(true, x, y));
                            break;

                        case 'k':
                            this.akcnePredmety.add(new Pavuk(false, x, y));
                            break;

                        case 'M':
                            this.akcnePredmety.add(new Minca(x, y));
                            break;

                        case 'm':
                            this.akcnePredmety.add(new Minca(x, y));
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'Z':
                            this.akcnePredmety.add(new Zivot(x, y));
                            break;

                        case 'S':
                            Sfukovac s = new Sfukovac(x, y);
                            this.akcnePredmety.add(s);
                            this.prekazky.add(s);
                            break;

                        case 'N':
                            Pumpa p = new Pumpa(x, y);
                            this.akcnePredmety.add(p);
                            this.prekazky.add(p);
                            break;

                        case 'T':
                            Vytah t = new Vytah(true, x, y);
                            this.akcnePredmety.add(t);
                            this.prekazky.add(t);
                            break;

                        case 't':
                            Vytah t2 = new Vytah(false, x, y);
                            this.akcnePredmety.add(t2);
                            this.prekazky.add(t2);
                            break;

//                        case 'E':
//                            Elektrina e = new Elektrina(x, y);
//                            this.akcnePredmety.add(e);
//                            this.prekazky.add(e);
//                            break;
//
//                        case 'e':
//                            Elektrina e2 = new Elektrina(x, y);
//                            this.akcnePredmety.add(e2);
//                            this.prekazky.add(e2);
//                            this.prekazky.add(new Voda(x, y));
//                            break;
//
//                        case 'B':
//                            Krabica b = new Krabica(x, y);
//                            this.akcnePredmety.add(b);
//                            this.prekazky.add(b);
//                            break;
//
//                        case 'b':
//                            Krabica b2 = new Krabica(x, y);
//                            this.akcnePredmety.add(b2);
//                            this.prekazky.add(b2);
//                            this.prekazky.add(new Voda(x, y));
//                            break;
//                            
                        case 'C':
                            this.akcnePredmety.add(new Ciel(x, y));
                            break;

                        case 'H':
                            this.navesti.add(new Sipka(TypSipky.HORE, x, y));
                            break;
                        case 'h':
                            this.navesti.add(new Sipka(TypSipky.HORE, x, y));
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'D':
                            this.navesti.add(new Sipka(TypSipky.DOLE, x, y));
                            break;
                        case 'd':
                            this.navesti.add(new Sipka(TypSipky.DOLE, x, y));
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'P':
                            this.navesti.add(new Sipka(TypSipky.VPRAVO, x, y));
                            break;
                        case 'p':
                            this.navesti.add(new Sipka(TypSipky.VPRAVO, x, y));
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'L':
                            this.navesti.add(new Sipka(TypSipky.VLAVO, x, y));
                            break;
                        case 'l':
                            this.navesti.add(new Sipka(TypSipky.VLAVO, x, y));
                            this.prekazky.add(new Voda(x, y));
                            break;

                        case 'O':
                            if (this.lopta == null) {
                                this.lopta = new Lopta(x, y, vybrataFarba);
                            } else {
                                throw new NasliSaDveLoptyException();
                            }
                            break;
                        case ' ':
                            break;

                        default:
                            throw new AssertionError();
                    }
                }

                y++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.hranicaY = y;
    }

    /**
     * Vrati nazov levelu.
     *
     * @return nazovLevelu
     */
    public String getNazovLevelu() {
        return this.nazovLevelu;
    }

    /**
     * Vrati pocet minci, ktore treba v leveli nazberat.
     *
     * @return pocetMinci
     */
    public int getPocetMinci() {
        return this.pocetMinci;
    }

    /**
     * Vrati loptu.
     *
     * @return lopta
     */
    public Lopta getLopta() {
        return this.lopta;
    }

    /**
     * Vrati vsetky nacitane prekazky.
     *
     * @return prekazky
     */
    public ArrayList<IPreskokovaPrekazka> getPrekazky() {
        return (ArrayList<IPreskokovaPrekazka>) this.prekazky.clone();
    }

    /**
     * Vrati vsetky nacitane akcne predmety.
     *
     * @return akcnePredmety
     */
    public ArrayList<IAkcnyPredmet> getAkcnePredmety() {
        return (ArrayList<IAkcnyPredmet>) this.akcnePredmety.clone();
    }

    /**
     * Vrati vsetky nacitane getNavesti.
     *
     * @return getNavesti
     */
    public ArrayList<INavest> getNavesti() {
        return (ArrayList<INavest>) this.navesti.clone();
    }

    public int getHranicaY() {
        return this.hranicaY;
    }

}
