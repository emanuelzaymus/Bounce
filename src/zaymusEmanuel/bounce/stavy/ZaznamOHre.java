package zaymusEmanuel.bounce.stavy;

/**
 * ZaznamOHre - reprezentuje jednoduchy zaznam o odohratej hre (pocet bodov,
 * celkovy cas, cislo posledneho levelu, meno hraca).
 *
 * @author Emanuel Zaymus
 * @version 2017/05/12
 */
class ZaznamOHre implements Comparable<ZaznamOHre> {

    private final long pocetBodov;
    private final long cas;
    private final int level;
    private final String menoHraca;

    /**
     * Inicializuje atributy.
     *
     * @param pocetBodov pocet bodov, ktore hrac ziskaa v hre
     * @param cas celkovy cas trvania hry
     * @param level poradove cislo posledneho hraneho levelu
     * @param menoHraca meno
     */
    ZaznamOHre(long pocetBodov, long cas, int level, String menoHraca) {
        this.pocetBodov = pocetBodov;
        this.cas = cas;
        this.level = level;
        this.menoHraca = menoHraca;
    }

    /**
     * Vrati uceleny vypis zaznamu (pocet bodov, cas, posledny level, meno).
     *
     * @return string
     */
    @Override
    public String toString() {
        return String.format("       %03d                  %02d:%02d            %02d        %s",
                this.pocetBodov, this.cas / 60, this.cas % 60, this.level, this.menoHraca);
    }

    /**
     * Vrati pocet bodov.
     *
     * @return pocetBodov
     */
    public long getPocetBodov() {
        return this.pocetBodov;
    }

    /**
     * Vrati celkovy cas.
     *
     * @return cas
     */
    public long getCas() {
        return this.cas;
    }

    /**
     * Vrati poradove cislo posledneho hraneho levelu.
     *
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Vrati meno hraca.
     *
     * @return menoHraca
     */
    public String getMenoHraca() {
        return this.menoHraca;
    }

    /**
     * Porovna pocet bodov (this) s poctom bodov zaznamu odovzdaneho parametrom,
     * pripadne porovna casy zaznamov.
     *
     * @param porovnavany porovnavany zaznam o hre
     * @return -1 ak je this mensie, 1 ak je vacsie
     */
    @Override
    public int compareTo(ZaznamOHre porovnavany) {
        int vysledok = Long.compare(this.pocetBodov, porovnavany.pocetBodov);

        if (vysledok == 0) {
            return -Long.compare(this.cas, porovnavany.cas);
        }
        return vysledok;
    }

}
