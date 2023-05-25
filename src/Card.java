public class Card {

    //card klasse mit eigenschaften
    private final String sign;   // Kartenfarben als String angegeben
    private String color;       //Kartenzeichen als finale String angegeben/kann nicht geändert werden

    public Card(String sign, String color) {
        this.sign = sign;
        this.color = color;
    }

    public String getSign() {
        return sign;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + ' ' + sign;
    }
}
