package nl.kaihup.Attracties;

public class LadderklimmenAttractie extends Attractie implements GokAttractie {

    public LadderklimmenAttractie(String naam, double prijs, double oppervlakte) {
        super(naam, prijs, oppervlakte);
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
