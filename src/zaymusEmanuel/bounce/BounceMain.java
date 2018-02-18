package zaymusEmanuel.bounce;

/**
 * BounceMain - main trieda aplikacie.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public class BounceMain {

    /**
     * Vytvory nove instancie tried Platno a Hra, prida hru platnu a
     * spusti hru.
     */
    public static void main(String[] args) {
        Platno platno = Platno.getInstancia();
        Hra hra = new Hra();

        platno.pridaj(hra);
        platno.zobraz();

        hra.start();
    }

}
