public class MapToken extends Token{
    private static final String TOKEN_TYPE = "MAP_TOKEN";
    private String value;

    public MapToken(String value){
        super(TOKEN_TYPE, value);
        this.value = value;
    }
}
