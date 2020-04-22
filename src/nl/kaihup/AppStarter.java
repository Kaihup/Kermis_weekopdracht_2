package nl.kaihup;

class AppStarter {

    /**
     * Dit is het beginpunt van de Kermis applicatie.
     * Initialisatiee van Kermis en Menu vinden hier plaats.
     * Bevat daarnaast een infinite loop waardoor het menu net zo lang beschikbaar blijft tot de applicatie
     * door de user wordt gestopt.
     */
    void initKermisApp(String kermisNaam) {
        Kermis kermis = new Kermis(kermisNaam);
        Menu menu = new Menu(kermis);
        menu.loop();
    }
}
