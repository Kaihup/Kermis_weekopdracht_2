package nl.kaihup;

import nl.kaihup.Attracties.Attractie;
import nl.kaihup.Belasting.BelastingRapport;

import java.util.ArrayList;
import java.util.HashMap;

public class Kassa {

    private ArrayList<BelastingRapport> belastingAdministratie = new ArrayList<>();

    /* Print omzet overzicht */
    void printOmzetOverzicht(HashMap<Character, Attractie> attracties) {
        System.out.println("OMZET: ");
        double resultaat = 0;
        for (Attractie attractie : attracties.values()) {
            System.out.println("\t€ " + Utils.df(attractie.getOmzetTotaal()) + " " + attractie.getNaam());
            resultaat += attractie.getOmzetTotaal();
        }
        System.out.println("\t---------------------");
        System.out.println("\t€ " + Utils.df(resultaat) + " Totaal");
    }

    /* Print ticketverkoop overzicht */
    void printKaartverkoopOverzicht(HashMap<Character, Attractie> attracties) {
        System.out.println("VERKOCHTTE KAARTJES: ");
        int resultaat = 0;
        for (Attractie attractie : attracties.values()) {
            System.out.println("\t" + attractie.getTicketsVerkocht() + " : " + attractie.getNaam());
            resultaat += attractie.getTicketsVerkocht();
        }
        System.out.println("\t---------------------");
        System.out.println("\t" + resultaat + " : Totaal");
    }


    /* Return totale omzet */
    double getOmzetTotaal(HashMap<Character, Attractie> attracties) {
        double resultaat = 0;
        for (Attractie attractie : attracties.values()) {
            resultaat += attractie.getOmzetTotaal();
        }
        return resultaat;
    }

    /* Return totaal aantal kaartjes verkocht */
    int getKaartjesTotaal(HashMap<Character, Attractie> attracties) {
        int resultaat = 0;
        for (Attractie attractie : attracties.values()) {
            System.out.println("\t" + attractie.getTicketsVerkocht());
            resultaat += attractie.getTicketsVerkocht();
        }
        return resultaat;
    }

    ArrayList<BelastingRapport> getBelastingAdministratie() {
        return belastingAdministratie;
    }

    void printBeslastingAdministratie() {
        if (!belastingAdministratie.isEmpty()) {
            System.out.println("BELASTING ADMINISTRATIE");
            for (BelastingRapport rapport : belastingAdministratie) {
                rapport.printSamenvatting();
            }
        } else {
            System.out.println("De administratie bevat momenteel nog geen rapporten. " +
                    "\nPas na de eerste belastinginspectie zijn hier rapportages zichtbaar");
        }
    }


    public void administreerBelastingRapport(BelastingRapport rapport) {
        belastingAdministratie.add(rapport);
        System.out.println("Centrale kassa: " + Utils.ic(Utils.ANSI_GREEN, "Rapport verwerkt en toegevoegd aan administratie van kermis."));
    }

}
