public class BoolToken extends Token{
    private static final String TOKEN_TYPE = "BOOL_TOKEN";
    private String value;

    public BoolToken(String value) {
        super(TOKEN_TYPE, value);
        this.value = value;
    }
}
