package nl.kaihup.Belasting;

import nl.kaihup.Attracties.GokAttractie;
import nl.kaihup.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BelastingRapport {

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String datum = localDateTime.format(dateFormatter);
    private final String tijd = localDateTime.format(timeFormatter);
    private final int referentieNummer = -(datum.replaceAll(":", "") + tijd.replaceAll(":", "")).hashCode();

    private final double belastingPercentage = GokAttractie.kanSpelBelastingPercentage;
    private HashMap<String, Double[]> attractieDetails;


    BelastingRapport(HashMap<String, Double[]> attractieDetails) {
        this.attractieDetails = attractieDetails;
    }


    /**
     * @return double[] met een opsomming van de bedragen per attractie.
     */
    private double[] berekenTotalen() {
        double[] totalen = new double[3];
        totalen[0] = berekenTotaalBedrag(0); // Totale omzet van alle geïnspecteerde attracties
        totalen[1] = berekenTotaalBedrag(1); // Totale omzet van alle geïnspecteerde attracties
        totalen[2] = berekenTotaalBedrag(2); // Totale te betalen Kansspelbelasting (30%) van alle attracties over belastbare omzet
        return totalen;
    }

    /**
     * Op basis van aangeleverde Double[] in constructor kan per index het totale bedrag worden berekend.
     * {@link #berekenTotalen()}}
     */
    private double berekenTotaalBedrag(int index) {
        double resultaat = 0;
        for (Double[] values : attractieDetails.values()) {
            resultaat += values[index];
        }
        return resultaat;
    }

    /**
     * Print op basis van attractieDetails het volledige rapport naar de console.
     */
    void printRapport() {
        /* Head */
        System.out.println("\n========================== RAPPORT KANSSPELBELASTING ===========================\n"
                + "Referentienummer: " + referentieNummer
                + "\nDatum: " + datum
                + "\nTijd: " + tijd
                + "\nOnderwerp: Kansspelbelasting"
                + "\nBelastingpercentage: " + belastingPercentage + "%");

        /* Aanleiding rapport */
        System.out.println("\nDe Belastinginspecteur is bij u langs geweest om de kansppelbelasting te innen." +
                "\nDe belasting is reeds voldaan. Dit rapport is voor uw eigen administratie."

                + "\n\nGeïnspecteerde Gokattracties: ");

        /* Details per geïnspecteerde Attracties*/
        for (Map.Entry<String, Double[]> attractie : attractieDetails.entrySet()) {
            System.out.println("\n" + attractie.getKey() + ":" // Attractie naam
                    + "\n\t€ " + Utils.df(attractie.getValue()[0]) + " totale omzet"
                    + "\n\t€ " + Utils.df(attractie.getValue()[1]) + " belastbare omzet"
                    + "\n\t€ " + Utils.df(attractie.getValue()[2]) + " kansspelbelasting");
        }

        /* Totaal */
        double[] totaal = berekenTotalen();
        System.out.println("\nTotaal Gokattracites:"
                + "\n\t€ " + Utils.df(totaal[0]) + " totale omzet gokattracties"
                + "\n\t€ " + Utils.df(totaal[1]) + " belastbare omzet gokattracties"
                + "\n\t€ " + Utils.df(totaal[2]) + " totale kansspelbelasting betaald")
        ;
        /* Footer */
        System.out.println("================================================================================");
    }

    /**
     * Print op basis van attractieDetails het een samenvatting van het rapport naar de console..
     */
    public void printSamenvatting() {
        /* Head */
        System.out.println("\t____________________________________________________________"
                + "\n\tOnderwerp: Kansspelbelasting\t\tReferentienummer: " + referentieNummer
                + "\n\tdatum: " + datum + ", tijd:" + tijd
                + "\n\tBelastingpercentage: " + belastingPercentage + "%"
                + "\n\tGeïnspecteerde Gokattracties: ");

        /* Details per geïnspecteerde Attractie */
        for (Map.Entry<String, Double[]> attractie : attractieDetails.entrySet()) {
            System.out.println("\t" + attractie.getKey() + ":" // Attractie naam)
                    + "\n\t€ " + Utils.df(attractie.getValue()[1]) + " belastbare omzet"
                    + "\n\t€ " + Utils.df(attractie.getValue()[2]) + " kansspelbelasting");
        }

        /* Totaal */
        double[] totaal = berekenTotalen();
        System.out.println("\t---------------------"
                + "\n\tTotaal Gokattracites:"
                + "\n\t€ " + Utils.df(totaal[1]) + " totaal belastbare omzet gokattracties"
                + "\n\t€ " + Utils.df(totaal[2]) + " totaal kansspelbelasting betaald\n");
    }
}
