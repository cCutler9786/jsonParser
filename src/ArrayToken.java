public class ArrayToken extends Token{
    private static final String TOKEN_TYPE = "ARRAY_TOKEN";
    private String value;

    public ArrayToken(String value){
        super(TOKEN_TYPE, value);
        this.value = value;
    }
}
