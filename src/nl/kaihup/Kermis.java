package nl.kaihup;

import nl.kaihup.Attracties.*;

import java.util.HashMap;

public class Kermis {

    private String naam;
    private HashMap<Character, Attractie> attracties;
    private Kassa kassa = new Kassa();

    public Kermis(String naam) {

        this.naam = naam;
        printnaam();
        this.attracties = plaatsAttracties();
        printKermisOpen();
    }

    /**
     * Initiëren van alle kermis Attracties
     * key is char zodat deze te gebruiken zijn in combinatie met de switch (char) in het menu
     *
     * @return alle kermis attracties in een HashMap met als key het nummer waarmee ze in het menu kunnen worden aangeroepen.
     */
    HashMap<Character, Attractie> plaatsAttracties() {
        HashMap<Character, Attractie> tePlaatsenAttracties = new HashMap<>();
        tePlaatsenAttracties.put('1', new BotsautoAttractie("Botsauto's", 2.50, 12.50, 18.00));
        tePlaatsenAttracties.put('2', new SpinAttractie("Spin", 2.25, 14.50, 10.0, 5));
        tePlaatsenAttracties.put('3', new SpiegelpaleisAttractie("Spiegelpaleis", 2.75, 12.50, 8.00));
        tePlaatsenAttracties.put('4', new SpookhuisAttractie("Spookhuis", 2.75, 9.50, 6.50));
        tePlaatsenAttracties.put('5', new HawaiiAttractie("Hawaii", 2.90, 15.00, 13.0, 10));
        tePlaatsenAttracties.put('6', new LadderklimmenAttractie("Ladderklimmen", 5.00, 14.00, 12.50));
        return tePlaatsenAttracties;
    }

    private void printnaam() {
        System.out.println("\n===================== " + naam.toUpperCase() + " ======================");
    }


    private void printKermisOpen() {
        System.out.println("\nWELKOM, " + naam.toUpperCase() + " IS NU GEOPEND!");
    }


    String attractieKeys() {
        StringBuilder sb = new StringBuilder();
        for (Character ch : attracties.keySet()) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public Kassa getKassa() {
        return kassa;
    }

    public String getNaam() {
        return naam;
    }

    public HashMap<Character, Attractie> getAttracties() {
        return attracties;
    }

}



