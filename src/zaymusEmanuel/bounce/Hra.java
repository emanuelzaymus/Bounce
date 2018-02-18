package zaymusEmanuel.bounce;

import zaymusEmanuel.bounce.stavy.IStav;
import zaymusEmanuel.bounce.stavy.StavNacitavanie;
import zaymusEmanuel.bounce.vstupy.VstupMysi;
import zaymusEmanuel.bounce.vstupy.VstupKlavesnice;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 * Hra - riadi chod aplikacie.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class Hra extends Canvas implements Runnable {

    private boolean spustene;
    private IStav aktualnyStav;

    /**
     * Vytvori nove instancie VstupMysi a VstupKlavesnice, prida lisenery.
     */
    public Hra() {
        VstupMysi vstupMysi = new VstupMysi();
        addMouseListener(vstupMysi);
        addMouseMotionListener(vstupMysi);

        VstupKlavesnice vstupKlavesnice = new VstupKlavesnice();
        addKeyListener(vstupKlavesnice);

        this.spustene = false;
        this.aktualnyStav = new StavNacitavanie();
    }

    /**
     * Spusti hru.
     */
    public void start() {
        this.spustene = true;
        new Thread(this, "HraBounce-Thread").start();
    }

    /**
     * Zastavi hru.
     */
    public void stop() {
        this.spustene = false;
    }

    /**
     * Na poziadanie meni stav aplikacie.
     */
    public void zmenStavNa(IStav dalsiStav) {
        this.aktualnyStav = dalsiStav;
    }

    /**
     * Vykonava vsetky operacie 60x za sekundu.
     */
    @Override
    public void run() {
        requestFocus();

        double nsNaTik = 1000000000 / C.FPS;
        long poslednyTik = System.nanoTime();

        while (this.spustene) {
            if (System.nanoTime() - poslednyTik > nsNaTik) {
                poslednyTik = System.nanoTime();

                this.tik();

                VstupKlavesnice.aktualizuj();
                VstupMysi.aktualizuj();

                this.render();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    /**
     * Spusta tik v aktualnom stave aplikacie.
     */
    private void tik() {
        this.aktualnyStav.tik(this);
    }

    /**
     * Vykresluje vsetky potrebne obrazky.
     */
    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, C.SIRKA, C.VYSKA);

        this.aktualnyStav.render(g2d);

        g.dispose();
        bs.show();
    }

}
