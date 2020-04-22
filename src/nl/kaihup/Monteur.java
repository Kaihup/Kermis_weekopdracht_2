package nl.kaihup;

import nl.kaihup.Attracties.Attractie;
import nl.kaihup.Attracties.RisicoRijkeAttractie;
import nl.kaihup.Exceptions.OpstellingsControleException;

public class Monteur {


    /**
     * Voor alle RisicoRijkeAttracties geldt dat een opstellingscontrole verplicht is.
     * Deze opstellingscontrole wordt op aanvraag van de betreffende attractie uitgevoerd door de Monteur.
     * <p>
     * De attractie moet goed opgesteld zijn voor de kermis open kan. Zo niet, dan throwt de methode een OpstelilngsControleException.
     */
    public void opstellingsControle(Attractie attractie) {
        while (true) {
            try {
                if (new Utils().geefBevestiging("Opstellingskeuring: Is de attracie \"" + attractie.getNaam() + "\" correct opgesteld?")) {
                    System.out.println("Monteur: " + Utils.ic(Utils.ANSI_GREEN, "Opstellingcontrole voor de attractie \"" + attractie.getNaam() + "\" geslaagd."));
                    break;
                } else {
                    throw new OpstellingsControleException(attractie.getNaam());
                }
            } catch (OpstellingsControleException e) {
                System.out.println(Utils.ic(Utils.ANSI_RED, e.getMessage()));
            }
        }
    }

    /**
     * Voor alle RisicoRijkeAttractie geldt dat zij een draaiLimiet hebben. Wanneer het limiet bereikt is
     * moet de attractie gecontroleerd worden door de monteur om te bepalen of de attractie gereset kan worden.
     *
     * @return true <-- goedkeuring voor attractie reset || false <-- geen goedkeuring voor attractie reset
     */
    public boolean resetAttractieControle(Attractie attractie) {
        if (attractie instanceof RisicoRijkeAttractie) {
            if (((RisicoRijkeAttractie) attractie).draaiLimietOverschreden()) {
                System.out.println("Monteur: " + Utils.ic(Utils.ANSI_GREEN, "Controle uitgevoerd. Reset draaiLimiet van " + attractie.getNaam() + "."));
                return true;
            }
        }
        return false;
    }
}
