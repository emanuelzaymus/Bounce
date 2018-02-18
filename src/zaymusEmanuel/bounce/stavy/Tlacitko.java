/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author 3M0
 */
public abstract class Tlacitko {

    private int x;
    private int y;
    private Shape hranice;
    private boolean oznacene;

    public Tlacitko(int x, int y, Shape hranice) {
        this.x = x;
        this.y = y;
        this.hranice = hranice;
        this.oznacene = false;
    }

    protected int getX() {
        return this.x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected int getY() {
        return this.y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected boolean isOznacene() {
        return this.oznacene;
    }

    protected void setOznacene(boolean oznacene) {
        this.oznacene = oznacene;
    }

    protected Shape getHranice() {
        return this.hranice;
    }

    protected void setHranice(Rectangle hranice) {
        this.hranice = hranice;
    }

    public abstract void render(Graphics2D g2);

}
