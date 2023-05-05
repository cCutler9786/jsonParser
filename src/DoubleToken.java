public class DoubleToken extends Token{
    public static String TOKEN_TYPE = "DOUBLE_TOKEN";
    public double value;

    public DoubleToken(double value) {
        super(TOKEN_TYPE, Double.toString(value));
        this.value = value;
    }
}
