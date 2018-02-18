package zaymusEmanuel.bounce.predmety;

import zaymusEmanuel.bounce.predmety.preskokovePrekazky.IPreskokovaPrekazka;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Voda;
import zaymusEmanuel.bounce.predmety.akcnePredmety.IAkcnyPredmet;
import zaymusEmanuel.bounce.stavy.StavBounce;
import zaymusEmanuel.bounce.vstupy.VstupKlavesnice;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.predmety.preskokovePrekazky.Krabica;

/**
 * Lopta - hlavny element hry predstavujuci hraca.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Lopta extends PredmetObrazkovy {

    private final FarbaLopty farba;
    private final Ellipse2D.Double hraniceMalej;
    private final Ellipse2D.Double hraniceNafuknutej;

    private boolean pada;
    private boolean mozeSkakat;
    private boolean jeNafuknuta;
    private boolean prepichnuta;
    private int pocitadlo;

    private int posunX;
    private int posunY;

    private int realnaY;
    private int posunYvytahu = 0;
    private int posunXvytahu = 0;

    private int rychlostLopty;

    /**
     * Vytvori loptu na zadanych suradniciach.
     *
     * @param x suradnica X laveho horneho rohu
     * @param y suradnica Y laveho horneho rohu
     */
    public Lopta(int x, int y, FarbaLopty farba) {
        super(C.LOPTA_MALA + farba, x, y);
        this.realnaY = y * C.ROZLISENIE;

        this.farba = farba;
        this.hraniceMalej = new Ellipse2D.Double(super.getX() - 1, super.getY() - 1, C.ROZLISENIE + 2, C.ROZLISENIE + 2);
        this.hraniceNafuknutej = new Ellipse2D.Double(super.getX() - 1 - 15, super.getY() - 1 - 30, 122, 122);

        this.pada = true;
        this.mozeSkakat = true;
        this.jeNafuknuta = false;
        this.prepichnuta = false;
        this.pocitadlo = 0;

        this.posunX = 0;
        this.posunY = 0;

        this.rychlostLopty = 6;
    }

    /**
     * Vykona jeden tik, skontroluje vsetky mozne pohyby lopty a prehru.
     *
     * @param bounce aktualny stav hry
     */
    public void tik(StavBounce bounce) {

        if (VstupKlavesnice.jeStlacene(KeyEvent.VK_UP)) {
            this.skoc(10);
        }
        if (VstupKlavesnice.jeStlacene(KeyEvent.VK_LEFT)) {
            this.posunX = -this.rychlostLopty;
        }
        if (VstupKlavesnice.jeStlacene(KeyEvent.VK_RIGHT)) {
            this.posunX = this.rychlostLopty;
        }

        if (VstupKlavesnice.bolPustenyZnak(KeyEvent.VK_UP)) {
            this.posunY = 0;
        }
        if (VstupKlavesnice.bolPustenyZnak(KeyEvent.VK_LEFT) || VstupKlavesnice.bolPustenyZnak(KeyEvent.VK_RIGHT)) {
            this.posunX = 0;
        }

        if (this.kolidujeZhora(bounce) && this.posunY > 0) {
            this.posunY = 0;
        }
        if (this.kolidujeZdola(bounce) && this.posunY < 0) {
            this.posunY = 0;
        }
        if (this.kolidujeZlava(bounce) && this.posunX > 0) {
            this.posunX = 0;
        }
        if (this.kolidujeSprava(bounce) && this.posunX < 0) {
            this.posunX = 0;
        }

        this.padaj();

        bounce.setPosunProstrediaX(-this.posunX - this.posunXvytahu);
        bounce.setPosunProstrediaY(-this.posunY - this.posunYvytahu);
        this.realnaY += this.posunY + this.posunYvytahu;

        this.posunX = 0;
        this.posunYvytahu = 0;
        this.posunXvytahu = 0;

        this.skontrolujKoliziuSPredmetmy(bounce);

        if (this.realnaY > bounce.getHranicaY()) {
            this.prepichlaSiSa();
        }

        if (this.prepichnuta) {
            if (++this.pocitadlo >= 2) {
                bounce.prehra();
                this.prepichnuta = false;
            }
        }

    }

    public void posunSa(int x, int y) {
        this.posunXvytahu += x;
        this.posunYvytahu += y;
    }

    //nepouzite (iba v Elektrina)
    public void setRychlostLopty(int rychlostLopty) {
        this.rychlostLopty = rychlostLopty;
    }

    /**
     * Vrati hranice lopty reprezentovane kruhom.
     *
     * @return hraniceLopty
     */
    public Ellipse2D.Double getHranice() {
        if (this.jeNafuknuta) {
            return this.hraniceNafuknutej;
        }
        return this.hraniceMalej;
    }

    /**
     * Zvacsi loptu.
     */
    public void nafukniSa() {
        if (!this.jeNafuknuta) {
            super.pohyb(-15, -30);
            super.zmenObrazok(C.LOPTA_NAFUKNUTA + this.farba);
            this.jeNafuknuta = true;
        }
    }

    /**
     * Zmensi loptu.
     */
    public void zmensiSa() {
        if (this.jeNafuknuta) {
            super.pohyb(15, 30);
            super.zmenObrazok(C.LOPTA_MALA + this.farba);
            this.jeNafuknuta = false;
        }
    }

    /**
     * Zmeni graficku reprezentaciu lopty.
     */
    public void prepichlaSiSa() {
        if (this.jeNafuknuta) {
            super.zmenObrazok(C.LOPTA_NAFUKNUTA_PRASKUNTA + this.farba);
        } else {
            super.zmenObrazok(C.LOPTA_MALA_PRASKNUTA + this.farba);
        }
        this.prepichnuta = true;
    }

    /**
     * Skontroluje koliziu zhora so vsetkymi prekazkami hry.
     *
     * @param bounce aktualny stav hry
     * @return ci koliduje zhora
     */
    private boolean kolidujeZhora(StavBounce bounce) {
        for (IPreskokovaPrekazka prekazka : bounce.getPrekazky()) {
            if (!this.jeNafuknuta && (prekazka instanceof Voda)) {
                continue;
            }
            if (this.majuPrienik(this.getHranice(), prekazka.getVrch())) {
                this.mozeSkakat = true;
                this.pada = false;
                return true;
            } else {
                this.pada = true;
            }
        }
        return false;
    }

    /**
     * Skontroluje koliziu zdola so vsetkymi prekazkami hry.
     *
     * @param bounce aktualny stav hry
     * @return ci koliduje zdola
     */
    private boolean kolidujeZdola(StavBounce bounce) {
        for (IPreskokovaPrekazka prekazka : bounce.getPrekazky()) {
            if (!this.jeNafuknuta && (prekazka instanceof Voda)) {
                continue;
            }
            if (this.majuPrienik(this.getHranice(), prekazka.getSpodok())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Skontroluje koliziu zlava so vsetkymi prekazkami hry.
     *
     * @param bounce aktualny stav hry
     * @return ci koliduje zlava
     */
    private boolean kolidujeZlava(StavBounce bounce) {
        for (IPreskokovaPrekazka prekazka : bounce.getPrekazky()) {
            if (!this.jeNafuknuta && (prekazka instanceof Voda)) {
                continue;
            }

            if (this.majuPrienik(this.getHranice(), prekazka.getLavaStrana())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Skontroluje koliziu sprava so vsetkymi prekazkami hry.
     *
     * @param bounce aktualny stav hry
     * @return ci koliduje sprava
     */
    private boolean kolidujeSprava(StavBounce bounce) {
        for (IPreskokovaPrekazka prekazka : bounce.getPrekazky()) {
            if (!this.jeNafuknuta && (prekazka instanceof Voda)) {
                continue;
            }

            if (this.majuPrienik(this.getHranice(), prekazka.getPravaStrana())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Skontroluje prienik oblasti dvoch tvarov.
     *
     * @param tvar1 prvy tvar
     * @param tvar2 druhy tvar
     * @return ci maju tvary prienik
     */
    public boolean majuPrienik(Shape tvar1, Shape tvar2) {
        Area oblast1 = new Area(tvar1);
        oblast1.intersect(new Area(tvar2));
        return !oblast1.isEmpty();
    }

    /**
     * Padanie lopty.
     */
    private void padaj() {
        if (this.pada) {
            this.posunY += C.GRAVITACIA;
            if (this.posunY > C.MAX_POSUN_PADANIA) {
                this.posunY = C.MAX_POSUN_PADANIA;
            }
        }
    }

    /**
     * Skok lopty.
     *
     * @param vyskaSkoku
     */
    private void skoc(int vyskaSkoku) {
        if (this.mozeSkakat) {
            this.posunY -= vyskaSkoku;
            this.mozeSkakat = false;
        }
    }

    /**
     * Skontroluje koliziu so vsetkymi akcnymi predmetmi hry.
     *
     * @param bounce aktualny stav hry
     */
    private void skontrolujKoliziuSPredmetmy(StavBounce bounce) {
        for (IAkcnyPredmet predmet : bounce.getAkcnePredmety()) {
            if (this.majuPrienik(this.getHranice(), predmet.getCelkoveHranice())) {
                predmet.spustiAkciu(bounce, this);
            }
        }
    }

}
