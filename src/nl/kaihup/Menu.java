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
                case 'k':   // Optie: Toon aantal verkochte kaartjes
                    kermis.getKassa().printKaartverkoopOverzicht(kermis.getAttracties());
                    break;
                case 'p':   // Optie: Print menu
                    printMenu();
                    break;
                case 'a': // Optie: Toon administratie
                    kermis.getKassa().printBeslastingAdministratie();
                    break;
                case 'q':   // Quit
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

    private boolean isValid(String input) {
        String validChars = "okpaq" + kermis.attractieIndices();
        if (input.length() != 1) {
            return false;
        }
        return input.matches("^[" + validChars + "]*$");
    }

    private void printMenu() {
        System.out.println("\n================= KERMIS MENU ====================");
        printAttractieOpties();
        printOverigeOpties();
        System.out.println("==================================================");
    }


    private void printAttractieOpties() {
        System.out.println("ATTRACTIES");
        for (Map.Entry<Character, Attractie> entry : kermis.getAttracties().entrySet()) {
            Attractie attractie = entry.getValue();
            System.out.println(Utils.lr(" " + entry.getKey() + " ") + " " + attractie.toString());
        }
    }

    private void printOverigeOpties() {
        System.out.println("\nOVERIGE OPTIES"
                + "\n" + Utils.lr(" o ") + " Toon omzet"
                + "\n" + Utils.lr(" k ") + " Verkochte kaartjes"
                + "\n" + Utils.lr(" a ") + " Toon administratie kansspelbelasting"
                + "\n" + Utils.lr(" q ") + " Programma stoppen");
    }

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

