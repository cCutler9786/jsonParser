import java.util.ArrayList;

public class ArrayToken extends Token{
    private static final String TOKEN_TYPE = "ARRAY_TOKEN";
    private ArrayList<?> value;

    public ArrayToken(ArrayList<?> value){
        super(TOKEN_TYPE, value.toString());
        this.value = value;
    }
}
