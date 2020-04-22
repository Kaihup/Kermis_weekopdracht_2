package nl.kaihup.Exceptions;

public class OngeldigeInvoerException extends Exception {

    public OngeldigeInvoerException() {
        super("OngeldigeInvoerException: er is geen geldige invoer gegeven.");
    }

    public OngeldigeInvoerException(String addTomessage) {
        super("OngeldigeInvoerException: er is geen geldige invoer gegeven."
                + "\n" + addTomessage + "\n");
    }

}