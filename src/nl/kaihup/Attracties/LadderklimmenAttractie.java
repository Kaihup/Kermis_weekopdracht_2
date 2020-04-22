package nl.kaihup.Attracties;

public class LadderklimmenAttractie extends Attractie implements GokAttractie {

    public LadderklimmenAttractie(String naam, double prijs, double lengte, double breedte) {
        super(naam, prijs, lengte, breedte);
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
