/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy;

import java.awt.Graphics2D;
import java.awt.Shape;
import zaymusEmanuel.bounce.predmety.Grafika;

/**
 *
 * @author 3M0
 */
public class TlacitkoObrazkove extends Tlacitko {

    private final Grafika obrazok;
    private Grafika obrazokOznacony = null;
    private Grafika obrazokStlaceny = null;
    private boolean stlacene = false;

    public TlacitkoObrazkove(int x, int y, Shape hranice, Grafika obrazok) {
        super(x, y, hranice);
        this.obrazok = obrazok;
    }

    public TlacitkoObrazkove(int x, int y, Shape hranice, Grafika obrazok, Grafika obrazokOznacony) {
        super(x, y, hranice);
        this.obrazok = obrazok;
        this.obrazokOznacony = obrazokOznacony;
    }

    public TlacitkoObrazkove(int x, int y, Shape hranice, Grafika obrazok, Grafika obrazokOznacony, Grafika obrazokStlaceny) {
        super(x, y, hranice);
        this.obrazok = obrazok;
        this.obrazokOznacony = obrazokOznacony;
        this.obrazokStlaceny = obrazokStlaceny;
    }

    public void setOznacene(boolean oznacene) {
        if (this.obrazokOznacony != null) {
            super.setOznacene(oznacene);
        }
    }

    public void setStlacene(boolean stlacene) {
        if (this.obrazokStlaceny != null) {
            this.stlacene = stlacene;
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        if (this.stlacene) {
            this.obrazokStlaceny.render(g2d, super.getX(), super.getY());
            return;
        }
        if (super.isOznacene()) {
            this.obrazokOznacony.render(g2d, super.getX(), super.getY());
        } else {
            this.obrazok.render(g2d, super.getX(), super.getY());
        }
    }

}
