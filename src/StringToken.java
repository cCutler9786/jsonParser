public class StringToken extends Token{
    private static final String TOKEN_TYPE = "STRING_TOKEN";
    private String value;

    public StringToken(String value){
        super(TOKEN_TYPE, value);
        this.value = value;
    }
}
