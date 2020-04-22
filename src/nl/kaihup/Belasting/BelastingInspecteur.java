package nl.kaihup.Belasting;

import nl.kaihup.Attracties.Attractie;
import nl.kaihup.Attracties.GokAttractie;
import nl.kaihup.Kermis;
import nl.kaihup.Utils;

import java.util.HashMap;

public class BelastingInspecteur {

    /**
     * Tijdens kansSpelBelastingControle wordt belasting geïnd van de belastingplichtige Attracties
     * Daarnaast wordt direct een BelastingRapport opgestuurd naar centrale kassa van de kermis.
     */
    public void kansSpelBelastingControle(Kermis kermis) {
        HashMap<String, Double[]> controleDetails = new HashMap<>();
        /* Controle welke attracties GokAttracties  zijn */
        for (Attractie attractie : kermis.getAttracties().values()) {
            if (attractie instanceof GokAttractie) {
                Double[] bedragen = new Double[3];
                bedragen[0] = attractie.getOmzetTotaal(); // totale omzet
                bedragen[1] = attractie.getOmzetBelastbaar(); // belastbare omzet
                bedragen[2] = ((GokAttractie) attractie).kansSpelBelastingBetalen(); // betaalde belasting over belastbare omzet

                /* Gegevens geïnspecteerde GokAttractie verzamelen in HashMap <-- nodig voor BelastingRapport */
                controleDetails.put(attractie.getNaam(), bedragen);
            }
        }
        /* Rapport Opsturen */
        kermis.getKassa().administreerBelastingRapport(stuurBelastingRapport(controleDetails));
    }

    /**
     * BelastingRapport met details over controle en heffingen
     *
     * @param controleDetails worden verzameld in {@link #kansSpelBelastingControle(Kermis)
     */
    private BelastingRapport stuurBelastingRapport(HashMap<String, Double[]> controleDetails) {
        BelastingRapport rapport = new BelastingRapport(controleDetails);
        rapport.printInspectieRapport();
        System.out.println("Belastinginspecteur: " + Utils.ic(Utils.ANSI_GREEN, "Inspectie afgerond. Rapport opgestuurd naar kermis."));
        return rapport;
    }

}
