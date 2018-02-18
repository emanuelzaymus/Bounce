/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import zaymusEmanuel.bounce.C;
import zaymusEmanuel.bounce.predmety.Grafika;

/**
 *
 * @author 3M0
 */
public class TlacitkoLopta extends Tlacitko {

    private final Grafika lopta;
    private final Grafika ramik;
    private final Grafika ramikOznaceny;
    private final Grafika ramikZvoleny;

    private boolean zvolene;

    public TlacitkoLopta(String nazovSuboru, int x, int y) {
        super(x, y, new Rectangle(x, y, 150, 150));
        this.lopta = new Grafika(nazovSuboru);

        this.ramik = new Grafika(C.RAMIK);
        this.ramikOznaceny = new Grafika(C.RAMIK_OZNACENY);
        this.ramikZvoleny = new Grafika(C.RAMIK_ZVOLENY);

        this.zvolene = false;
    }

    public boolean isZvolene() {
        return this.zvolene;
    }

    public void setZvolene(boolean zvolene) {
        this.zvolene = zvolene;
    }

    @Override
    public void render(Graphics2D g2d) {
        if (super.isOznacene()) {
            this.ramikOznaceny.render(g2d, super.getX(), super.getY());
        } else if (this.zvolene) {
            this.ramikZvoleny.render(g2d, super.getX(), super.getY());
        } else {
            this.ramik.render(g2d, super.getX(), super.getY());
        }
        this.lopta.render(g2d, super.getX() + 15, super.getY() + 15);
    }

}
