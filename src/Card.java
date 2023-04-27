public class Card {
    private final String sign;
    private String color;

    public Card(String sign, String color) {
        this.sign = sign;
        this.color = color;
    }

    public String getZeichen() {
        return sign;
    }

    public String getFarbe() {
        return color;
    }

    public void setFarbe(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return sign + ' ' + color;
    }
}
