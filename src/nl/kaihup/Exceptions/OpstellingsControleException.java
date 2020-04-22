package nl.kaihup.Exceptions;

public class OpstellingsControleException extends Exception {

    public OpstellingsControleException(String attractieNaam) {
        super("OpstellingsControleException: De attractie " + attractieNaam + " is niet goed opgesteld. "
                + "\nDe kermis kan zonder geslaagde opstellingkeuring niet open");
    }

}
