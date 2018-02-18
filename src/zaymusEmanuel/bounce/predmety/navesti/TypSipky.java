package zaymusEmanuel.bounce.predmety.navesti;

import zaymusEmanuel.bounce.C;

/**
 * TypSipky - mnozina typov sipiek. Nesu si informaciu o nazve animacie.
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
public enum TypSipky {
    HORE(C.ANIMACIA_SIPKA_HORE_cesta),
    DOLE(C.ANIMACIA_SIPKA_DOLE_cesta),
    VLAVO(C.ANIMACIA_SIPKA_Vlavo_cesta),
    VPRAVO(C.ANIMACIA_SIPKA_Vpravo_cesta);

    private String nazovAnimacie;

    /**
     * Vytvori jednoduchu sipku s grafickou reprezentaciou.
     *
     * @param nazovAnimacie nazov suboru s grafickou reprezentaciou
     */
    TypSipky(String nazovAnimacie) {
        this.nazovAnimacie = nazovAnimacie;
    }

    /**
     * Vrati nazov animacie sipky.
     *
     * @return nazovAnimacie
     */
    public String getNazovAnimacie() {
        return this.nazovAnimacie;
    }

}
