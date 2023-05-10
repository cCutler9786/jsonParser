public class Main {
    public static void main(String[] args) throws Exception {
        String JSONexample = "{ " +
                "\"Menu\": falsef," +
                "\"Drinks\": \"Soda\"," +
                "\"Food\": \"Steak\","+
                "}";

        Lexer lexerExample = new Lexer(JSONexample);
        System.out.println(lexerExample.tokenize());
    }
}
