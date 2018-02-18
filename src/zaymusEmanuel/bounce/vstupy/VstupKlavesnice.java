package zaymusEmanuel.bounce.vstupy;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

/**
 * VstupKlavesnice - zaznamenava stlacene a pustene klavesy.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class VstupKlavesnice extends KeyAdapter {

    private static HashSet<Integer> stlaceneKlavesy;
    private static HashSet<Integer> posledneStlacene;

    /**
     * Vytvori atrinuty pre stlacene klavesy a poslede stlacene klavesy.
     */
    public VstupKlavesnice() {
        this.stlaceneKlavesy = new HashSet<>();
        this.posledneStlacene = new HashSet<>();
    }

    /**
     * Zaznamena stlacenu klavesu.
     *
     * @param ke stlacena klavesa
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        stlaceneKlavesy.add(ke.getKeyCode());
    }

    /**
     * Zaznamena pustenu klavesu.
     *
     * @param ke pustenu klavesa
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        stlaceneKlavesy.remove(ke.getKeyCode());
    }

    /**
     * Aktualizuje posledne stlacene klavesy.
     */
    public static void aktualizuj() {
        posledneStlacene.clear();
        posledneStlacene.addAll(stlaceneKlavesy);
    }

    /**
     * Vrati informaciu, ci je klavesa stlacena.
     *
     * @param klavesa
     * @return ci je dana klavesa stlacena
     */
    public static boolean jeStlacene(int klavesa) {
        return stlaceneKlavesy.contains(klavesa);
    }

    /**
     * Vrati informaciu, ci bola klavesa stlacena.
     *
     * @param klavesa
     * @return ci bola dana klavesa stlacena
     */
    public static boolean bolStlacenyZnak(int klavesa) {
        return jeStlacene(klavesa) && !bolNaposledyStlaceny(klavesa);
    }

    /**
     * Vrati informaciu, ci bola klavesa pustena.
     *
     * @param klavesa
     * @return ci bola dana klavesa pustena
     */
    public static boolean bolPustenyZnak(int klavesa) {
        return !jeStlacene(klavesa) && bolNaposledyStlaceny(klavesa);
    }

    /**
     * Vrati informaciu, ci bola klavesa naposledy stlacena.
     *
     * @param klavesa
     * @return ci bola dana klavesa stlacena naposledy
     */
    private static boolean bolNaposledyStlaceny(int klavesa) {
        return posledneStlacene.contains(klavesa);
    }

}
