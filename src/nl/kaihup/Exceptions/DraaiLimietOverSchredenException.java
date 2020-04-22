package nl.kaihup.Exceptions;

public class DraaiLimietOverSchredenException extends Exception {
    public DraaiLimietOverSchredenException(String attractieNaam) {
        super("DraaiLimietOverSchredenException: Draailimiet van " + attractieNaam + " is bereikt."
                + "\nRoep een monteur op om de attractie te controleren en te resetten.");
    }
}

