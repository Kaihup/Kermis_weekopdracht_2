package nl.kaihup.Exceptions;

public class NietEenRisicoRijkeAttractieException extends Exception {
    public NietEenRisicoRijkeAttractieException(String attractieNaam) {
        super("NietEenRisicoRijkeAttractieException: " + attractieNaam + " is geen RisicoRijkeAttractie.");
    }
}