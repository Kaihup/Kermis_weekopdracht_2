package nl.kaihup.Attracties;

public class HawaiiAttractie extends RisicoRijkeAttractie {

    public HawaiiAttractie(String naam, double prijs, double lengte, double breedte, int draaiLimiet) {
        super(naam, prijs, lengte, breedte, draaiLimiet);
    }

    @Override
    public boolean draaien() {
        return super.draaien();
    }
}
