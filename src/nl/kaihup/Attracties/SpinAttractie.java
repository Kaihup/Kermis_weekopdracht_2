package nl.kaihup.Attracties;

public class SpinAttractie extends RisicoRijkeAttractie implements GokAttractie {

    public SpinAttractie(String naam, double prijs, double oppervlakte, int draaiLimiet) {
        super(naam, prijs, oppervlakte, draaiLimiet);
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
