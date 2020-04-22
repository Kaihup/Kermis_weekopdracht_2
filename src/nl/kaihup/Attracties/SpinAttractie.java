package nl.kaihup.Attracties;

public class SpinAttractie extends RisicoRijkeAttractie implements GokAttractie {

    public SpinAttractie(String naam, double prijs, double lengte, double breedte, int draaiLimiet) {
        super(naam, prijs, lengte, breedte, draaiLimiet);
    }

    @Override
    public boolean draaien() {
        return super.draaien();
    }

    @Override
    public double kansSpelBelastingBetalen() {
        return super.belastingAfdracht();
    }
}
