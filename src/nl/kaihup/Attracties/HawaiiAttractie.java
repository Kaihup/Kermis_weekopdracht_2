package nl.kaihup.Attracties;

public class HawaiiAttractie extends RisicoRijkeAttractie {

    public HawaiiAttractie(String naam, double prijs, double oppervlakte, int draaiLimiet) {
        super(naam, prijs, oppervlakte, draaiLimiet);
    }

    @Override
    public boolean draaien() {
        return super.draaien();
    }
}
