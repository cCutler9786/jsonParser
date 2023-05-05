import java.util.HashMap;

public class MapToken extends Token{
    private static final String TOKEN_TYPE = "MAP_TOKEN";
    private HashMap<StringToken, Token> value;

    public MapToken(HashMap<StringToken, Token> value){
        super(TOKEN_TYPE, value.toString());
        this.value = value;
    }
}
