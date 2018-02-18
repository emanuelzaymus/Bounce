package zaymusEmanuel.bounce.stavy;

import zaymusEmanuel.bounce.Hra;
import java.awt.Graphics2D;

/**
 * IStav - zoskupuje stavy v hra, ktore nasledne spravuje trieda Hra.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public interface IStav {

    /**
     * Vykona jeden tik v danom stave aplikacie.
     *
     * @param hra aktualna hra
     */
    void tik(Hra hra);

    /**
     * Vykresli vsetky objekty v danom stave s grafickou reprezentaciou.
     *
     * @param g2d
     */
    void render(Graphics2D g2d);
}
