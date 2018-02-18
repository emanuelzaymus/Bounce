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
import zaymusEmanuel.bounce.predmety.PredmetObrazkovy;
import zaymusEmanuel.bounce.predmety.akcnePredmety.IAkcnyPredmet;
import zaymusEmanuel.bounce.stavy.StavBounce;

/**
 *
 * @author 3M0
 */
public class Krabica extends PredmetObrazkovy implements IPreskokovaPrekazka, IAkcnyPredmet {

    private boolean pada = false;
    private int posunPadania = 0;

    public Krabica(int x, int y) {
        super(C.KRABICA, x, y);
    }

//    public void setPada(boolean pada) {
//        this.pada = pada;
//    }
//
//    public void setPosunPadania(int posunPadania) {
//        this.posunPadania = posunPadania;
//    }
    @Override
    public Rectangle2D getVrch() {
        return new Rectangle2D.Double(super.getX() + 4, super.getY(), C.ROZLISENIE - 8, 2);
    }

    @Override
    public Rectangle2D getSpodok() {
        return new Rectangle2D.Double(super.getX() + 4, super.getY() - 2 + C.ROZLISENIE, C.ROZLISENIE - 8, 2);
    }

    @Override
    public Rectangle2D getPravaStrana() {
        return new Rectangle2D.Double(super.getX() - 2 + C.ROZLISENIE, super.getY() + 8, 2, C.ROZLISENIE / 3 - 16);
    }

    @Override
    public Rectangle2D getLavaStrana() {
        return new Rectangle2D.Double(super.getX(), super.getY() + 8, 2, C.ROZLISENIE / 3 - 16);
    }

    @Override
    public void tik(int posunX, int posunY) {
//        if (this.pada) {
//            this.posunPadania += C.GRAVITACIA;
//            if (this.posunPadania > C.MAX_POSUN_PADANIA) {
//                this.posunPadania = C.MAX_POSUN_PADANIA;
//            }
//        }
        super.pohyb(posunX, posunY + this.posunPadania);
    }

    @Override
    public Shape getCelkoveHranice() {
        return new Rectangle2D.Double(super.getX(), super.getY(), C.ROZLISENIE, C.ROZLISENIE);
    }

    @Override
    public void spustiAkciu(StavBounce bounce, Lopta lopta) {
        if (lopta.majuPrienik(lopta.getHranice(), this.getVrch())) {
            return;
        }
        if (lopta.majuPrienik(lopta.getHranice(), this.getSpodok())) {
            return;
        }

        if (lopta.majuPrienik(lopta.getHranice(), this.getLavaStranaPosuvacia()) && bounce.getPosunProstrediaX() < 0) {
            for (IPreskokovaPrekazka prekazka : bounce.getPrekazky()) {
                if (prekazka instanceof Voda) {
                    continue;
                }
                if (!lopta.majuPrienik(this.getPravaStrana(), prekazka.getLavaStrana())) {
                    return;
                }
            }
            super.pohyb(-bounce.getPosunProstrediaX(), 0);
            return;
        }

        if (lopta.majuPrienik(lopta.getHranice(), this.getPravaStranaPosuvacia()) && bounce.getPosunProstrediaX() > 0) {
            for (IPreskokovaPrekazka prekazka : bounce.getPrekazky()) {
                if (prekazka instanceof Voda) {
                    continue;
                }
                if (lopta.majuPrienik(this.getLavaStrana(), prekazka.getPravaStrana())) {
                    return;
                }
            }
            super.pohyb(-bounce.getPosunProstrediaX(), 0);
        }

    }

    private Rectangle2D getPravaStranaPosuvacia() {
        return new Rectangle2D.Double(super.getX() - 2 + C.ROZLISENIE, super.getY(), 2, C.ROZLISENIE - 3);
    }

    private Rectangle2D getLavaStranaPosuvacia() {
        return new Rectangle2D.Double(super.getX(), super.getY(), 2, C.ROZLISENIE - 3);
    }

}
