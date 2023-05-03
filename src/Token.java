public abstract class Token{
    String TOKEN_TYPE;
    String value;

    public Token(String TOKEN_TYPE, String value){
        this.TOKEN_TYPE = TOKEN_TYPE;
        this.value = value;
    }

    public String toString(){
        return "Token: "+TOKEN_TYPE+", Contains: "+value;
    }
}
