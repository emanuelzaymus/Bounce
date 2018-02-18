/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaymusEmanuel.bounce.stavy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import zaymusEmanuel.bounce.C;

/**
 *
 * @author 3M0
 */
public class Zaznamy {

    private final String nazovSuboru;
    private final int x;
    private final int y;
    private ArrayList<ZaznamOHre> zaznamy;

    public Zaznamy(String nazovSuboru, int x, int y) {
        this.nazovSuboru = nazovSuboru;
        this.x = x;
        this.y = y;
        this.zaznamy = new ArrayList<>();
    }

    public void nacitajZoSuboru() throws IOException {
        try (DataInputStream citac = new DataInputStream(new FileInputStream(new File(C.DATA_VYSLEDKY_cesta + this.nazovSuboru)))) {
            while (citac.available() > 0) {
                this.zaznamy.add(new ZaznamOHre(citac.readLong(), citac.readLong(), citac.readInt(), citac.readUTF()));
            }
        } catch (EOFException ex) {
        }
    }

    public void zapisDoSuboru(ZaznamOHre zaznam) throws IOException {
        this.nacitajZoSuboru();
        this.zaznamy.add(zaznam);

        try (DataOutputStream zapisovac = new DataOutputStream(new FileOutputStream(new File(C.DATA_VYSLEDKY_cesta + this.nazovSuboru)))) {
            for (ZaznamOHre z : this.zaznamy) {
                zapisovac.writeLong(z.getPocetBodov());
                zapisovac.writeLong(z.getCas());
                zapisovac.writeInt(z.getLevel());
                zapisovac.writeUTF(z.getMenoHraca());
            }
        }
    }

    public void prepisAkJeLepsi(ZaznamOHre zaznam) throws IOException {
        this.nacitajZoSuboru();
        boolean boloNajdeneMeno = false;
        boolean zapis = false;

        for (int i = 0; i < this.zaznamy.size(); i++) {
            if (this.zaznamy.get(i).getMenoHraca().equals(zaznam.getMenoHraca())) {
                boloNajdeneMeno = true;
                ZaznamOHre z = this.zaznamy.get(i);

                if (z.getPocetBodov() < zaznam.getPocetBodov() || z.getPocetBodov() == zaznam.getPocetBodov() && z.getCas() > zaznam.getCas()) {
                    this.zaznamy.remove(i);
                    this.zaznamy.add(zaznam);
                    zapis = true;
                }
                break;
            }
        }
        if (!boloNajdeneMeno) {
            this.zaznamy.add(zaznam);
        }

        if (!boloNajdeneMeno || zapis) {
            try (DataOutputStream zapisovac = new DataOutputStream(new FileOutputStream(new File(C.DATA_VYSLEDKY_cesta + this.nazovSuboru)))) {
                for (ZaznamOHre zaz : this.zaznamy) {
                    zapisovac.writeLong(zaz.getPocetBodov());
                    zapisovac.writeLong(zaz.getCas());
                    zapisovac.writeInt(zaz.getLevel());
                    zapisovac.writeUTF(zaz.getMenoHraca());
                }
            }
        }
    }

    public void usporiadajZostupnePodlaBodov() {
        Collections.sort(this.zaznamy, Collections.reverseOrder());
    }

    public void render(Graphics2D g2d, boolean sMenom) {
        g2d.setFont(new Font("Arial Black", Font.PLAIN, 25));
        g2d.setColor(Color.BLACK);
        if (sMenom) {
            g2d.drawString("Počet bodov   Celkový čas   Po level   Meno", this.x, this.y);
        } else {
            g2d.drawString("Počet bodov   Celkový čas   Po level", this.x, this.y);
        }
        int i = this.y + 10;
        for (ZaznamOHre z : this.zaznamy) {
            g2d.drawString(z.toString(), this.x, i = i + 30);
        }
    }

    public ArrayList<ZaznamOHre> getZaznamy() {
        return this.zaznamy;
    }

    public long celkoveSkore() {
        long ret = 0;
        for (ZaznamOHre z : this.zaznamy) {
            ret += z.getPocetBodov();
        }
        return ret;
    }

}
