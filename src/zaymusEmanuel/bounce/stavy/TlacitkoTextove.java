package zaymusEmanuel.bounce.stavy;

import zaymusEmanuel.bounce.predmety.Grafika;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import zaymusEmanuel.bounce.C;

/**
 * TlacitkoTextove - reprezentuje jednoduche tlacitko ako text.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class TlacitkoTextove extends Tlacitko {

    private Text text;
    private final Color farba = Color.BLUE;
    private final Color farbaOznacena = Color.RED;

    private boolean mamMetriku;

    private Grafika loptaKuTlacitku1;
    private Grafika loptaKuTlacitku2;

    /**
     * Nastavi text do podoby tlacitka na zadanych suradniciach XY.
     *
     * @param popis popis tlacitka
     * @param x X-ova suradnica laveho dolneho rohu
     * @param y Y-ova suradnica laveho dolneho rohu
     */
    public TlacitkoTextove(String popis, int x, int y) {
        super(x, y, null);
        this.text = new Text(popis, new Font("Berlin Sans FB Demi", Font.BOLD, 44), this.farba, x, y);

        this.vytvor();
    }

    public TlacitkoTextove(String popis, Font font, int x, int y) {
        super(x, y, null);
        this.text = new Text(popis, font, this.farba, x, y);

        this.vytvor();
    }

    private void vytvor() {
        this.mamMetriku = false;
        this.loptaKuTlacitku1 = new Grafika(C.LOPTA_KU_TLACITKU);
        this.loptaKuTlacitku2 = new Grafika(C.LOPTA_KU_TLACITKU);
    }

    /**
     * Vykresli tlacitko - popis a pripadne sprevadzajuce obrazky.
     *
     * @param g2d
     */
    @Override
    public void render(Graphics2D g2d) {
        if (!this.mamMetriku) {
            g2d.setFont(this.text.getFont());
            FontMetrics fm = g2d.getFontMetrics();
            super.setX(super.getX() - fm.stringWidth(this.text.getText()) / 2 - 30);
            this.text.setX(super.getX());
            super.setHranice(new Rectangle(super.getX(), super.getY() - fm.getHeight(), fm.stringWidth(this.text.getText()), fm.getHeight() + 15));

            this.mamMetriku = true;
        }
        if (super.isOznacene()) {
            this.text.setFarba(this.farbaOznacena);

            this.loptaKuTlacitku1.render(g2d, super.getX() - 50, super.getY() - 37);
            this.loptaKuTlacitku2.render(g2d, (int) (super.getX() + ((Rectangle) super.getHranice()).getWidth() + 5), super.getY() - 37);
        } else {
            this.text.setFarba(this.farba);
        }
        this.text.render(g2d);
    }

}
