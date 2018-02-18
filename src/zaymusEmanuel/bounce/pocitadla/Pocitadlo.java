/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.pocitadla;

import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.stavy.Text;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Pocitadlo - vseobecne pocitadlo lubovolneho typu.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public abstract class Pocitadlo {

    private Grafika grafika;
    private Text text;

    private int aktualnyPocet;
    private int cielovyPocet;
    private long celkovyPocet;

    private int x;
    private int y;

    /**
     * Vytvori vseobecne pocitadlo.
     *
     * @param nazovSuboru nazov suboru s grafickou reprezentaciou
     * @param popis popis na zobrazovanie
     * @param zaciatocnyPocet zaciatocny pocet na pocitadle
     * @param cielovyPocet cielovy pocet pocitadla, ktory sa ocakava
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Pocitadlo(String nazovSuboru, String popis, int zaciatocnyPocet, int cielovyPocet, int x, int y) {
        this.grafika = new Grafika(nazovSuboru);
        this.text = new Text(popis, new Font("Berlin Sans FB Demi", Font.PLAIN, 38), Color.BLACK, x + this.grafika.getSirkaObrazku() + 10, y + 40);

        this.aktualnyPocet = zaciatocnyPocet;
        this.cielovyPocet = cielovyPocet;
        this.celkovyPocet = zaciatocnyPocet;

        this.x = x;
        this.y = y;
    }

    /**
     * Vykresli pocitadlo.
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        this.grafika.render(g2d, this.x, this.y);
        this.text.render(g2d);
    }

    /**
     * Vrati celkovy pocet, ktory bol napocitany od vytvorenia pocitadla.
     *
     * @return celkovyPocet
     */
    public long getCelkovyPocet() {
        return this.celkovyPocet;
    }

    /**
     * Nastavenie novych hodnot.
     *
     * @param zaratatNapocitane informacia ci sa ma zapocitavat naposledy
     * napocitana hodnota
     * @param zaciatocnyPocet novy zaciatocny pocet na pocitadle
     * @param cielovyPocet novy cielovy pocet pocitadla, ktory sa ocakava
     */
    protected void setZaciatocnyACielovyPocet(boolean zaratatNapocitane, int zaciatocnyPocet, int cielovyPocet) {
        if (zaratatNapocitane) {
            this.celkovyPocet += this.aktualnyPocet;
        }
        this.aktualnyPocet = zaciatocnyPocet;
        this.cielovyPocet = cielovyPocet;
    }

    /**
     * Inkrementuje aktualy pocet.
     */
    protected void pridaj() {
        this.aktualnyPocet++;
    }

    /**
     * Dekrementuje aktualny pocet.
     */
    protected void uber() {
        this.aktualnyPocet--;
    }

    /**
     * Vrati aktualnu hodnotu na pocitadle.
     *
     * @return aktualnyPocet
     */
    protected int getAktualnyPocet() {
        return this.aktualnyPocet;
    }

    /**
     * Vrati pozadovany cielovy pocet.
     *
     * @return cielovyPocet
     */
    protected int getCielovyPocet() {
        return this.cielovyPocet;
    }

    /**
     * Zmeni graficku reprezentaciu pocitadla.
     *
     * @param nazovSuboru nazov suboru s grafickou reprezentaciou
     */
    protected void zmenObrazok(String nazovSuboru) {
        this.grafika = new Grafika(nazovSuboru);
    }

    /**
     * Aktualizuje popis pocitadla.
     *
     * @param aktualizovanyPopis novy popis
     */
    protected void setPopis(String aktualizovanyPopis) {
        this.text.setText(aktualizovanyPopis);
    }

}
