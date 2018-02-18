/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.Hra;
import zaymusEmanuel.bounce.predmety.FarbaLopty;
import zaymusEmanuel.bounce.predmety.Grafika;
import zaymusEmanuel.bounce.vstupy.VstupKlavesnice;
import zaymusEmanuel.bounce.vstupy.VstupMysi;

/**
 *
 * @author 3M0
 */
public class StavProfil implements IStav {

    private final Grafika pozadie = new Grafika(C.POZADIE_OKRAJ);

    private Text menoHraca;
    private Text najSkoreText;
    private Text celkoveSkoreText;

    private Text nadpisZznmy;
    private final Zaznamy zaznamy;
    private TlacitkoObrazkove tlacitkoSpat;
    private TlacitkoTextove tlacitkoHraj;

    private HashMap<FarbaLopty, TlacitkoLopta> lopty;
    private FarbaLopty vybrataFarba;

    public StavProfil(String menoHraca) {
        this.zaznamy = new Zaznamy(menoHraca + ".bin", 1260 / 2, 150);
        try {
            this.zaznamy.nacitajZoSuboru();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        this.zaznamy.usporiadajZostupnePodlaBodov();

        this.vytvorProstredie(menoHraca);
    }

    public StavProfil(long pocetBodov, long cas, int level, String menoHraca) {
        this.zaznamy = new Zaznamy(menoHraca + ".bin", 1260 / 2, 150);
        try {
            this.zaznamy.zapisDoSuboru(new ZaznamOHre(pocetBodov, cas, level, ""));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        this.zaznamy.usporiadajZostupnePodlaBodov();

        this.vytvorProstredie(menoHraca);
    }

    private void vytvorProstredie(String meno) {
        this.menoHraca = new Text(meno, new Font("Arial Black", Font.PLAIN, 45), Color.BLACK, C.SIRKA / 6, 100);

        this.nadpisZznmy = new Text("Tabuľka osobných výsledkov", new Font("Arial Black", Font.PLAIN, 30), Color.BLACK, C.SIRKA / 2, 110);
        ArrayList<ZaznamOHre> zznmy = this.zaznamy.getZaznamy();
        String popis = "Najvyššie skóre: " + ((zznmy.size() > 0) ? zznmy.get(0).getPocetBodov() : 0);
        this.najSkoreText = new Text(popis, new Font("Arial Black", Font.PLAIN, 25), Color.BLACK, 1260 / 6, 150);
        this.celkoveSkoreText = new Text("Celkové skóre: " + this.zaznamy.celkoveSkore(), new Font("Arial Black", Font.PLAIN, 25), Color.BLACK, C.SIRKA / 6, 200);

        this.tlacitkoSpat = new TlacitkoObrazkove(50, 50, new Ellipse2D.Double(50, 50, 112, 112), new Grafika(C.TLACITKO_SPAT), new Grafika(C.TLACITKO_SPAT_OZNACENE), new Grafika(C.TLACITKO_SPAT_STLACENE));
        this.tlacitkoHraj = new TlacitkoTextove("HRAJ", new Font("Arial Black", Font.PLAIN, 38), C.SIRKA / 4 + 43, C.VYSKA / 12 * 10 + 10);

        this.lopty = new HashMap<>();
        this.lopty.put(FarbaLopty.Cervena, new TlacitkoLopta(C.LOPTA_NAFUKNUTA + FarbaLopty.Cervena, C.SIRKA / 10, 230));
        this.lopty.get(FarbaLopty.Cervena).setZvolene(true);
        this.lopty.put(FarbaLopty.Modra, new TlacitkoLopta(C.LOPTA_NAFUKNUTA + FarbaLopty.Modra, C.SIRKA / 10 * 3, 230));
        this.lopty.put(FarbaLopty.Zelena, new TlacitkoLopta(C.LOPTA_NAFUKNUTA + FarbaLopty.Zelena, C.SIRKA / 10, 230 + 180));
        this.lopty.put(FarbaLopty.Zlta, new TlacitkoLopta(C.LOPTA_NAFUKNUTA + FarbaLopty.Zlta, C.SIRKA / 10 * 3, 230 + 180));

        this.vybrataFarba = FarbaLopty.Cervena;
    }

    @Override
    public void tik(Hra hra) {
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_ENTER)) {
            hra.zmenStavNa(new StavBounce(this.menoHraca.getText(), this.vybrataFarba));
        }
        if (VstupKlavesnice.bolStlacenyZnak(KeyEvent.VK_BACK_SPACE)) {
            hra.zmenStavNa(new StavMenuPrihlaseny(this.menoHraca.getText()));
        }

        for (FarbaLopty i : this.lopty.keySet()) {
            this.lopty.get(i).setOznacene(this.lopty.get(i).getHranice().intersects(new Rectangle(VstupMysi.getX(), VstupMysi.getY(), 1, 1)));
            if (this.lopty.get(i).isOznacene() && VstupMysi.boloStlacene(MouseEvent.BUTTON1)) {
                this.lopty.get(this.vybrataFarba).setZvolene(false);
                this.vybrataFarba = i;
                this.lopty.get(i).setZvolene(true);
            }
        }

        this.tlacitkoSpat.setOznacene(this.tlacitkoSpat.getHranice().intersects(new Rectangle(VstupMysi.getX(), VstupMysi.getY(), 1, 1)));
        if (this.tlacitkoSpat.isOznacene()) {
            this.tlacitkoSpat.setStlacene(VstupMysi.jeStlacene(MouseEvent.BUTTON1));
            if (VstupMysi.boloPustene(MouseEvent.BUTTON1)) {
                hra.zmenStavNa(new StavMenuPrihlaseny(this.menoHraca.getText()));
            }
        }

        this.tlacitkoHraj.setOznacene(this.tlacitkoHraj.getHranice().intersects(new Rectangle(VstupMysi.getX(), VstupMysi.getY(), 1, 1)));
        if (this.tlacitkoHraj.isOznacene() && VstupMysi.boloStlacene(MouseEvent.BUTTON1)) {
            hra.zmenStavNa(new StavBounce(this.menoHraca.getText(), this.vybrataFarba));
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        this.pozadie.render(g2d, 0, 0);

        for (TlacitkoLopta tlacitko : this.lopty.values()) {
            tlacitko.render(g2d);
        }

        this.nadpisZznmy.render(g2d);
        this.menoHraca.render(g2d);
        this.najSkoreText.render(g2d);
        this.celkoveSkoreText.render(g2d);

        this.zaznamy.render(g2d, false);

        this.tlacitkoSpat.render(g2d);
        this.tlacitkoHraj.render(g2d);
    }

}
