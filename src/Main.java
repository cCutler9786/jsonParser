public class Main {
    public static void main(String[] args) {
        String JSONexample = "{\"menu\": {  \n" +
                "  \"id\": \"file\",  \n" +
                "  \"value\": \"File\",  \n" +
                "  \"popup\": {  \n" +
                "    \"menuitem\": [  \n" +
                "      {\"value\": \"New\", \"onclick\": \"CreateDoc()\"},  \n" +
                "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},  \n" +
                "      {\"value\": \"Save\", \"onclick\": \"SaveDoc()\"}  \n" +
                "    ]  \n" +
                "  }  \n" +
                "}}  ";

        System.out.println(JSONexample);
    }
}
