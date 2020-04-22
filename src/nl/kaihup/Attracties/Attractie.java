package nl.kaihup.Attracties;

import nl.kaihup.Utils;

public abstract class Attractie {

    private String naam;
    private double prijs;
    private double omzetTotaal;
    private double omzetBelastbaar;
    private double bedragGereserveerdVoorBelasting;
    private double oppervlakte; // Staat in opdracht omschrijving. Wordt nu (nog) niks mee gedaan.
    private int ticketsVerkocht;


    public Attractie(String naam, double prijs, double lengte, double breedte) {
        this.naam = naam;
        this.prijs = prijs;
        if (lengte * breedte >= 0) {
            this.oppervlakte = lengte * breedte;
        } else {
            this.oppervlakte = 0;
        }

    }


    /**
     * Methode laat Attractie draaien
     * Afhankelijk welke subclass deze methode aanroept wordt er informatie geprint die alleen voor die class geldig is.
     * Op deze manier krijgen wordt de informatie voor alle Attracties op een eenduidige manier weergegeven.
     */
    public boolean draaien() {
        /* Voor alle Attracties */
        ticketsVerkocht += 1;
        omzetTotaal += prijs;
        System.out.println(naam.toUpperCase());
        System.out.println("\tAttractie draait");
        System.out.println("\t" + ticketsVerkocht + " : tickets verkocht");
        System.out.println("\t€ " + Utils.df(omzetTotaal) + " : totale omzet ");

        /* Alleen Voor GokAttracties */
        if (this instanceof GokAttractie) {
            omzetBelastbaar += prijs;
            bedragGereserveerdVoorBelasting += (prijs / 100 * GokAttractie.kanSpelBelastingPercentage);
            System.out.println("\t€ " + Utils.df(omzetTotaal) + " : belastbare omzet sinds laatste inspectie");
            System.out.println("\t€ " + Utils.df(bedragGereserveerdVoorBelasting) + " : gereserveerd voor belasting (" + GokAttractie.kanSpelBelastingPercentage + "%)");
        }
        /* Alleen voor RisicoRijkeAttracties */
        if (this instanceof RisicoRijkeAttractie) {
            RisicoRijkeAttractie attractie = ((RisicoRijkeAttractie) this);
            System.out.println("\t" + attractie.getDraaiLimiet() + " : draaiLimiet");
            System.out.println("\t" + (attractie.getDraaiLimiet() - attractie.getKerenGedraaid()) + " : rondes tot reset draaiLimiet");
        }

        return true;
    }


    /**
     * Wordt gebruikt om belasting af te dragen aan de BelastingInspecteur.
     * Bij aanroep wordt het gereserveerde bedrag voor belasting direct van de omzet afgetrokken.
     *
     * @return aan belasting te betalen bedrag.
     */
    double belastingAfdracht() {
        double bedrag = bedragGereserveerdVoorBelasting;
        omzetTotaal -= bedragGereserveerdVoorBelasting; // belasting aftrekken van de omzet
        bedragGereserveerdVoorBelasting = 0; // belasting is betaald, reset var
        omzetBelastbaar = 0; // // belasting is betaald, reset var

        return bedrag;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public double getOmzetTotaal() {
        return omzetTotaal;
    }

    public double getOmzetBelastbaar() {
        return omzetBelastbaar;
    }

    public int getTicketsVerkocht() {
        return ticketsVerkocht;
    }

    @Override
    public String toString() {
        return naam + ": €" + prijs;
    }
}
