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
public class Vytah extends PredmetAnimovany implements IAkcnyPredmet, IPreskokovaPrekazka {

    private final boolean maIstVertikalne;
    private final int delta;
    private final int maxPosun;
    private int doterajsiPosun;
    private int znamienko;

    public Vytah(boolean maIstVertikalne, int x, int y) {
        super((maIstVertikalne ? C.VYTAH_DOLE_cesta : C.VYTAH_DOPRAVA_cesta), (maIstVertikalne ? C.VYTAH_HORE_cesta : C.VYTAH_DOLAVA_cesta), 0, 30, x, y);
        this.maIstVertikalne = maIstVertikalne;
        this.delta = 3;
        this.maxPosun = C.ROZLISENIE * 4;

        this.doterajsiPosun = 0;
        this.znamienko = 1;
    }

    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX(), super.getY(), C.ROZLISENIE * 2, C.ROZLISENIE / 2);
    }

    @Override
    public void tik(int posunX, int posunY) {
        if (this.doterajsiPosun <= this.maxPosun) {
            this.doterajsiPosun += this.delta;
        } else {
            this.znamienko *= -1;
            this.doterajsiPosun = 0;

            super.zmenAnimaciu();
        }
        if (this.maIstVertikalne) {
            super.pohyb(posunX, posunY + this.delta * this.znamienko);
        } else {
            super.pohyb(posunX + this.delta * this.znamienko, posunY);
        }
    }

    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        if (this.maIstVertikalne) {
//            bounce.setPosunProstrediaY(-this.delta * this.znamienko);
            lopta.posunSa(0, this.delta * this.znamienko);
        } else {
//            bounce.setPosunProstrediaX(-this.delta * this.znamienko);
            lopta.posunSa(this.delta * this.znamienko, 0);
        }
    }

    @Override
    public Rectangle2D getVrch() {
        return new Rectangle2D.Double(super.getX() + 17, super.getY(), C.ROZLISENIE * 2 - 34, 2);
    }

    @Override
    public Rectangle2D getSpodok() {
        return new Rectangle2D.Double(super.getX() + 17, super.getY() - 2 + C.ROZLISENIE / 2, C.ROZLISENIE * 2 - 34, 2);
    }

    @Override
    public Rectangle2D getPravaStrana() {
        return new Rectangle2D.Double(super.getX() - 2 + C.ROZLISENIE * 2, super.getY() + 8, 2, C.ROZLISENIE / 2 - 16);
    }

    @Override
    public Rectangle2D getLavaStrana() {
        return new Rectangle2D.Double(super.getX(), super.getY() + 8, 2, C.ROZLISENIE / 2 - 16);
    }

}
