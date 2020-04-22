package nl.kaihup;

import nl.kaihup.Attracties.Attractie;
import nl.kaihup.Belasting.BelastingInspecteur;
import nl.kaihup.Exceptions.OngeldigeInvoerException;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Menu {
    private Kermis kermis;
    private int draaiCount = 0;
    private boolean inspectieGeweest = false;
    private Random rand = new Random();


    Menu(Kermis kermis) {
        this.kermis = kermis;
    }

    /**
     * De Menu loop zorgt ervoor dat het programma blijft draaien na het uitvoeren van een menu optie
     * De einige manier om de loop te doorbreken is door het programma te stoppen.
     */
    void loop() {
        boolean printOptions = true;
        while (true) {
            try {
                keuzeMenu(printOptions);
            } catch (OngeldigeInvoerException e) {
                System.out.println(Utils.ic(Utils.ANSI_RED, e.getMessage()));
            } finally {
                printOptions = false;
            }
        }
    }

    /**
     * Het keuzeMenu toont alle voor de user beschikbare opties.
     * user input wordt gevalideerd. Zodra de input als valide wordt herkent stuurt het menu de user door naar de gekozen optie.
     * <p>
     * Toevoeging: In elke 15 draaibeurten vindt er 1 keer een {@link #randomBelastingInspectie(int)} plaats.
     */
    private void keuzeMenu(boolean printMenuOpties) throws OngeldigeInvoerException {
        /* print alle menu opties naar console */
        if (printMenuOpties) {
            printMenu();
        }

        /* Vraag user voor input */
        System.out.println(Utils.lr(" Kies een optie van het menu (of 'p' print menu): "));
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();

        /* Valideer input */
        if (!isValid(input)) { // Ongeldige input
            throw new OngeldigeInvoerException();
        } else { // Geldige input

            /* Naar geselecteerde optie */
            char keuze = input.charAt(0);
            switch (keuze) {
                case 'o':   // Optie: Toon omzet
                    kermis.getKassa().printOmzetOverzicht(kermis.getAttracties());
                    break;
                case 'k':   // Toon aantal verkochte kaartjes
                    kermis.getKassa().printKaartverkoopOverzicht(kermis.getAttracties());
                    break;
                case 'p':   // Print menu
                    printMenu();
                    break;
                case 'a':   // Toon administratie
                    kermis.getKassa().printBeslastingAdministratie();
                    break;
                case 'q':   // Stop programma
                    Utils ut = new Utils();
                    if (ut.geefBevestiging("Weet je zeker dat je wil stoppen?")) {
                        ut.stopProgramma();
                    }
                    break;
                default: // Kies attractie:
                    if (kermis.getAttracties().get(keuze).draaien()) {
                        Utils.println("Draaicount = " + draaiCount);
                        randomBelastingInspectie(15);
                    }
            }
        }
    }

    /**
     * Controleer user input op geldigheid.
     * User input is geldig wanneer deze matcht met 1 van de karakters in validChars.
     * <p>
     * Wanneer een karakter uit validCharas wordt aangepast moet dit ook gebeuren in {@link #printOverigeOpties()}
     * en in {@link #keuzeMenu(boolean)}
     *
     * @return true <-- geldig || false <-- ongeldig
     */
    private boolean isValid(String input) {
        String validChars = "okpaq" + kermis.attractieKeys();
        if (input.length() != 1) {
            return false;
        }
        return input.matches("^[" + validChars + "]*$");
    }

    /**
     * Print het keuzemenu naar de console
     */
    private void printMenu() {
        System.out.println("\n================= KERMIS MENU ====================");
        printAttractieOpties();
        printOverigeOpties();
        System.out.println("==================================================");
    }


    /**
     * Print een lijst op basis van kermis.getAttracties() met daarin;
     * 1) Attractie key
     * 2) Attractie toString()
     */
    private void printAttractieOpties() {
        System.out.println("ATTRACTIES");
        for (Map.Entry<Character, Attractie> entry : kermis.getAttracties().entrySet()) {
            Attractie attractie = entry.getValue();
            System.out.println(Utils.lr(" " + entry.getKey() + " ") + " " + attractie.toString());
        }
    }

    /**
     * Menu opties anders dan het laten draaien van Attracties
     * De optie letters moeten overeen komen met de letters in {@link #isValid} en {@link #keuzeMenu(boolean)}
     */
    private void printOverigeOpties() {
        System.out.println("\nOVERIGE OPTIES"
                + "\n" + Utils.lr(" o ") + " Toon omzet"
                + "\n" + Utils.lr(" k ") + " Verkochte kaartjes"
                + "\n" + Utils.lr(" a ") + " Toon administratie kansspelbelasting"
                + "\n" + Utils.lr(" q ") + " Programma stoppen");
    }

    /**
     * Elke n interval moet er 1 controle van de BelastingInspecteur plaatsvinden.
     * Deze controle gebeurt op een willekeurig moment. Is de belastinginspecteur bij de laatste
     * interval nog steeds niet op een willekeurig moment aangeroepen, dan doet de methode dit alsnog.
     *
     * @param interval het aantal draaibeurten waarin de BelastingInspecteur 1 controle uitvoert
     */
    private void randomBelastingInspectie(int interval) {
        int randomGetal = rand.nextInt(interval);
        if (draaiCount <= interval) {

            draaiCount++;

            if (!inspectieGeweest && (randomGetal == 0 || draaiCount == interval)) {
                new BelastingInspecteur().kansSpelBelastingControle(kermis);
                inspectieGeweest = true;
            }

            if (draaiCount == interval) { // reset
                inspectieGeweest = false;
                draaiCount = 0;
            }
        }
    }
}

