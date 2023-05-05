public abstract class Token{
    private String TOKEN_TYPE;
    private String value;

    public Token(String TOKEN_TYPE, String value){
        this.TOKEN_TYPE = TOKEN_TYPE;
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public String getTokenType(){
        return TOKEN_TYPE;
    }

    public String toString(){
        return TOKEN_TYPE+" = "+value;
    }
}
