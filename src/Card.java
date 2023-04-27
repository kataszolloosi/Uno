public class Karte {
    private final String zeichen;
    private String farbe;

    public Karte(String zeichen, String farbe) {
        this.zeichen = zeichen;
        this.farbe = farbe;
    }

    public String getZeichen() {
        return zeichen;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    @Override
    public String toString() {
        return "Karte{" +
                "zeichen='" + zeichen + '\'' +
                ", farbe='" + farbe + '\'' +
                '}';
    }
}
