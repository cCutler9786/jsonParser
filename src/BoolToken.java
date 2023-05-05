public class BoolToken extends Token{
    private static final String TOKEN_TYPE = "BOOL_TOKEN";
    private boolean value;

    public BoolToken(boolean value) {
        super(TOKEN_TYPE, Boolean.toString(value));
        this.value = value;
    }
}
