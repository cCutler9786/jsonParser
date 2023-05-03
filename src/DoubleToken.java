public class DoubleToken extends Token{
    public static String TOKEN_TYPE = "DOUBLE_TOKEN";
    public String value;

    public DoubleToken(String value) {
        super(TOKEN_TYPE, value);
        this.value = value;
    }
}
