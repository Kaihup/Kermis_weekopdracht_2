package nl.kaihup;

import java.util.Scanner;

public class Utils {


    // Utils is een ondersteunende class, welke voor consistentie in het programma zorgt met betrekking tot:
    // 1) Input vragen aan de user
    // 2) Weergave van console output


    /**
     * Vraag user middels een scanner een specifieke situatie te bevestigen
     *
     * @param inputVraag        vraag de user om een situatie te bevestigen
     * @param acceptabeleWaarde de waarde die ingevoerd moet worden door de user om te bevestigen
     * @param bericht           extra informatie achter acceptabeleWaarde
     */
    public boolean geefBevestiging(String inputVraag, String acceptabeleWaarde, String bericht) {

        if (!inputVraag.isEmpty()) {
            System.out.println(Utils.lr(inputVraag));
        }
        System.out.println(Utils.lr(" " + acceptabeleWaarde + " ") + " " + bericht);
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim();
        if (confirm.length() > acceptabeleWaarde.length()) {
            confirm = confirm.substring(0, acceptabeleWaarde.length());
        }

        return confirm.equalsIgnoreCase(acceptabeleWaarde);
    }

    /**
     * Vraag user om middels een scanner een specifieke vraag te bevestigen met ja of met nee.
     *
     * @param inputVraag vraag de user om een situatie te bevestigen
     * @return "ja" == true || alle overige input, inclusief "nee" == false
     */
    boolean geefBevestiging(String inputVraag) {

        System.out.println(Utils.lr(inputVraag));
        System.out.println(Utils.lr(" ja ") + " om te bevestigen || " + Utils.lr(" nee ") + " om te annuleren");
        Scanner scanner = new Scanner(System.in);
        String confirm = scanner.nextLine().trim();
        if (confirm.length() > 2) {
            confirm = confirm.substring(0, 2);
        }

        return confirm.equalsIgnoreCase("ja");
    }

    /**
     * Stopt het programma
     */
    void stopProgramma() {
        System.out.println("EINDE PROGRAMMA");
        System.exit(0);
    }


    /**
     * Hulpmiddel voor ontwikkelaar: wordt gebruikt om test/debug info berichten aan/uit te zetten
     * Deze output wrodt weergegeven in een afwijkende kleur (CYAN) zodat deze makkelijk te onderscheiden is van de overige output.
     */
    static void println(String message) {
        boolean aan = false;
        if (aan) {
            System.out.println(Utils.ic(Utils.ANSI_CYAN, message));
        }
    }

    /**
     * Eenduidig format om double waarden te printen naar de console
     *
     * @return String in format 0.00
     */
    public static String df(double input) {
        return String.format("%.2f", input);
    }

    /**
     * Console kleuren
     * Kleuren welke gebruikt worden om tekst op te laten vallen in de console
     */
    public static final String ANSI_RED = "\u001B[31m";     // Exceptions
    public static final String ANSI_GREEN = "\u001B[32m";   // Success
    public static final String ANSI_CYAN = "\u001B[36m";    // Developer info / Debug
    public static final String ANSI_RESET = "\u001B[0m";    // Reset kleur


    /**
     * Wordt gebruikt om op een eenduidige manier gekleurde Strings te generen.
     * Aan het einde van de string wordt de kleur gereset zodat bij de eerstvolgende System.out de default kleur wordt weergegeven
     *
     * @return String in specifieke ANSI Color
     */
    public static String ic(String ANSI_COLOR, String tekst) {
        return ANSI_COLOR + tekst + ANSI_RESET;
    }

    /**
     * Wordt gebruikt om op een eenduidige manier revered gekleurde Strings te genereren.
     * Reverst houdt in dat achtergrond- en tekst- kleur in de console worden omgedraaid.
     *
     * @return String in reversed cololor
     */
    public static String lr(String tekst) {
        return "\u001b[0;7m" + tekst + ANSI_RESET;
    }

}
