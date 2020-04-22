package nl.kaihup;

import nl.kaihup.Attracties.Attractie;
import nl.kaihup.Attracties.RisicoRijkeAttractie;
import nl.kaihup.Exceptions.NietEenRisicoRijkeAttractieException;
import nl.kaihup.Exceptions.OpstellingsControleException;

public class Monteur {


    public boolean opstellingsControle(Attractie attractie) {
        while (true) {
            try {
                if (new Utils().geefBevestiging("Opstellingskeuring: Is de attracie \"" + attractie.getNaam() + "\" correct opgesteld?")) {
                    System.out.println("Monteur: " + Utils.ic(Utils.ANSI_GREEN, "Opstellingcontrole voor de attractie \"" + attractie.getNaam() + "\" geslaagd."));
                    return true;
                } else {
                    throw new OpstellingsControleException(attractie.getNaam());
                }
            } catch (OpstellingsControleException e) {
                System.out.println(Utils.ic(Utils.ANSI_RED, e.getMessage()));
            }
        }
    }

    public boolean resetAttractieControle(Attractie attractie) {
        try {
            if (attractie instanceof RisicoRijkeAttractie) {
                if (((RisicoRijkeAttractie) attractie).draaiLimietOverschreden()) {
                    System.out.println("Monteur: " + Utils.ic(Utils.ANSI_GREEN, "Controle uitgevoerd. Reset draaiLimiet van " + attractie.getNaam() + "."));
                    return true;
                }
            } else {
                throw new NietEenRisicoRijkeAttractieException(attractie.getNaam());
            }
        } catch (NietEenRisicoRijkeAttractieException e) {
            System.out.println(Utils.ic(Utils.ANSI_RED, e.getMessage()));
        }
        return false;
    }
}
