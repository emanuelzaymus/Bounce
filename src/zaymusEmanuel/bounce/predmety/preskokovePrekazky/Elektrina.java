/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.predmety.preskokovePrekazky;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.predmety.Lopta;
import zaymusEmanuel.bounce.predmety.PredmetAnimovany;
import zaymusEmanuel.bounce.predmety.akcnePredmety.IAkcnyPredmet;
import zaymusEmanuel.bounce.stavy.StavBounce;

/**
 *
 * @author 3M0
 */
public class Elektrina extends PredmetAnimovany implements IAkcnyPredmet, IPreskokovaPrekazka {

    private boolean spusetene = false;
    private int pocitadlo = 0;

    public Elektrina(int x, int y) {
        super(C.ELEKTRINA_cesta, 0, 60, x, y);
    }

    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX() + 22, super.getY() + 20, 45, 70);
    }

    @Override
    public void tik(int posunX, int posunY) {
        super.pohyb(posunX, posunY);
        if (this.spusetene && ++this.pocitadlo >= 5 * C.FPS) {
            this.spusetene = false;
        }
    }

    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        lopta.setRychlostLopty(18);
        this.spusetene = true;
        this.pocitadlo = 0;
    }

    @Override
    public Rectangle2D getVrch() {
        return new Rectangle2D.Double(super.getX() + 22 + 17, super.getY() + 20, 45 - 34, 2);
    }

    @Override
    public Rectangle2D getSpodok() {
        return new Rectangle2D.Double(super.getX() + 22 + 17, super.getY() + C.ROZLISENIE - 2, 45 - 34, 2);
    }

    @Override
    public Rectangle2D getPravaStrana() {
        return new Rectangle2D.Double(super.getX() + 67 - 2, super.getY() + 20 + 8, 2, 70 - 16);
    }

    @Override
    public Rectangle2D getLavaStrana() {
        return new Rectangle2D.Double(super.getX() + 22, super.getY() + 20 + 8, 2, 70 - 16);
    }

}
