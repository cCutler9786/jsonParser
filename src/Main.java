public class Main {
    public static void main(String[] args) throws Exception {
        String JSONexample = "{ " +
                "\"Menu\": \"Nothing\"" +
                "\"Drinks\": \"Soda\"" +
                "}";

        Lexer lexerExample = new Lexer(JSONexample);
        System.out.println(lexerExample.tokenize());
    }
}
