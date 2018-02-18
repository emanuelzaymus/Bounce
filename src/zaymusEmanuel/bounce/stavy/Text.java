package zaymusEmanuel.bounce.stavy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * Text - reprezentuje jednoduchy text na zobrazovanie.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Text {

    private String text;
    private final Font font;
    private int x;
    private int y;

    private Color farba;

    /**
     * Inicializuje atributy.
     *
     * @param text samotny text
     * @param font font textu
     * @param farba farba textu
     * @param x X-ova suradnica laveho dolneho rohu
     * @param y Y-ova suradnica laveho dolneho rohu
     */
    public Text(String text, Font font, Color farba, int x, int y) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;

        this.farba = farba;
    }

    /**
     * Vypise text.
     *
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        g2d.setFont(this.font);
        g2d.setColor(this.farba);
        g2d.drawString(this.text, this.x, this.y);
    }

    /**
     * Nastavi text.
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Nastavi farbu.
     *
     * @param novaFarba
     */
    void setFarba(Color novaFarba) {
        this.farba = novaFarba;
    }

    /**
     * Nastavi X-ovu suradnicu laveho dolneho rohu.
     *
     * @param x X-ova suradnica laveho dolneho rohu
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Nastavi Y-ovu suradnicu laveho dolneho rohu.
     *
     * @param y Y-ova suradnica laveho dolneho rohu
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Vrati nastaveny text.
     *
     * @return text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Vrati nastaveny font.
     *
     * @return font
     */
    public Font getFont() {
        return this.font;
    }

}
