public class Main {
    public static void main(String[] args) {
        String JSONexample = "{ " +
                "\"Menu\": \"Nothing\"," +
                "\"Drinks\": \"Soda\"" +
                "}";

        Lexer lexerExample = new Lexer(JSONexample);
        System.out.println(lexerExample.tokenize());
    }
}
