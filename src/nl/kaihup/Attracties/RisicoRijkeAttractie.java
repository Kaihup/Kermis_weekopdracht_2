package nl.kaihup.Attracties;

import nl.kaihup.Exceptions.DraaiLimietOverSchredenException;
import nl.kaihup.Exceptions.OngeldigeInvoerException;
import nl.kaihup.Monteur;
import nl.kaihup.Utils;

public abstract class RisicoRijkeAttractie extends Attractie {
    private int draaiLimiet;
    private int kerenGedraaid = 0;

    {
        opstelingsKeuring(); // verplicht voor elke RisicoRijkeAttractie
    }

    RisicoRijkeAttractie(String naam, double prijs, double lengte, double breedte, int draaiLimiet) {
        super(naam, prijs, lengte, breedte);
        this.draaiLimiet = draaiLimiet;
    }

    /**
     * RiscoRijkeAttracties zijn verplicht een opstellingsKeuring te ondergaan voor de kermis open gaat.
     * De keuring wordt uitgevoerd door een Monteur.
     * Omdat de opstellingsKeuring verplicht voor elke RisicoRijke Attractie staat deze methode in het initializer block.
     */
    private void opstelingsKeuring() {
        System.out.println("\nOPSTELLINGSKEURING: "
                + "\n\tAttractie: " + super.getNaam()
                + "\n\tType: RisicorijkeAttractie\n");

        Monteur monteur = new Monteur();
        monteur.opstellingsControle(this);
    }

    /**
     * Wanneer er geen Exceptions zijn wordt super.draaien() aangeroepen.
     * Ook wordt dan het draaiLimiet bijgehouden door deze per ronde met 1 te verhogen.
     * <p>
     * Zodra het draaiLimiet is overschreden wordt er een DraaiLimietOverSchredenException gethrowt.
     * Een monteur wordt dan aangeroepen om het DraaiLimiet te resetten.
     * De attractie kan niet draaien zolang het draaiLimiet nog niet is gereset.
     *
     * @return true <-- attractie heeft gedraaid || false <-- attractie heeft niet gedraaid
     */
    @Override
    public boolean draaien() {
        try {
            if (draaiLimietOverschreden()) {
                throw new DraaiLimietOverSchredenException(getNaam());
            } else {
                this.kerenGedraaid += 1;
                return super.draaien();
            }
        } catch (DraaiLimietOverSchredenException doe) {
            System.err.println(Utils.ic(Utils.ANSI_RED, doe.getMessage()));
            Utils utils = new Utils();
            try {
                if (utils.geefBevestiging("", "m", "om een monteur op te roepen")) {
                    if (new Monteur().resetAttractieControle(this)) {
                        resetKerenGedraaid();
                        System.out.println("Attractie " + this.getNaam() + ": " + Utils.ic(Utils.ANSI_GREEN, "Reset draaiLimiet geslaagd."));
                    }
                } else {
                    throw new OngeldigeInvoerException("Er is geen monteur opgeroepen en het draailimiet is niet gereset.");
                }
            } catch (OngeldigeInvoerException oie) {
                System.out.println(Utils.ic(Utils.ANSI_RED, oie.getMessage()));
            }
        }
        return false;
    }

    /**
     * Controle op overschrijden van draaiLimiet
     *
     * @return true <-- overschreden || false <-- niet overschreden
     */
    public boolean draaiLimietOverschreden() {
        return (kerenGedraaid >= draaiLimiet);
    }

    private void resetKerenGedraaid() {
        this.kerenGedraaid = 0;
    }

    int getDraaiLimiet() {
        return draaiLimiet;
    }

    int getKerenGedraaid() {
        return kerenGedraaid;
    }
}
