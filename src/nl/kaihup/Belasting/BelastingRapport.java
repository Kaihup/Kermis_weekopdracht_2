package nl.kaihup.Belasting;

import nl.kaihup.Attracties.GokAttractie;
import nl.kaihup.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BelastingRapport {

    private final String DATE_FORMATTER = "yyyy-MM-dd";
    private final String TIME_FORMATTER = "HH:mm:ss";
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMATTER);
    private final String datum = localDateTime.format(dateFormatter);
    private final String tijd = localDateTime.format(timeFormatter);
    private final int referentieNummer = -(datum.replaceAll(":", "") + tijd.replaceAll(":", "")).hashCode();

    private final double belastingPercentage = GokAttractie.kanSpelBelastingPercentage;
    private HashMap<String, Double[]> attractieDetails;


    BelastingRapport(HashMap<String, Double[]> attractieDetails) {
        this.attractieDetails = attractieDetails;
    }


    private double[] berekenTotalen() {
        double[] totalen = new double[3];
        totalen[0] = berekenTotaalBedrag(0); // Totale omzet van alle geinspecteerde attracties
        totalen[1] = berekenTotaalBedrag(1); // Totale omzet van alle geinspecteerde attracties
        totalen[2] = berekenTotaalBedrag(2); // Totale te betalen Kansspelbelasting (30%) van alle attracties over belastbare omzet
        return totalen;
    }


    private double berekenTotaalBedrag(int index) {
        double resultaat = 0;
        for (Double[] values : attractieDetails.values()) {
            resultaat += values[index];
        }
        return resultaat;
    }


    void printInspectieRapport() {
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
