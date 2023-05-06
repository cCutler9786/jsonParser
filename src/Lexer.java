import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {

    /* Breaks up the json String into tokens to work with */

    String json;
    int index = 0; // The char we are at in the json String
    char currChar;

    public Lexer(String json){
        this.json = json;
    }

    public ArrayList<Token> tokenize() throws Exception {
        ArrayList<Token> tokens = new ArrayList<>();
        currChar = nextChar();

        if(currChar == '{'){ // The start for the json
            tokens.add(mapToken());
        }else if (currChar == ' '){ // Skip blank spaces
            tokenize();
        }else{ // Ran into a value, and it did not start the hashmap object so it prints an error and tells you where it is
            System.out.println("Did not have an open Object for the first next to " + currChar + " At index: "+index);
            return null;
        }
        return tokens;
    }

    private MapToken mapToken() throws Exception {
        HashMap<StringToken, Token> currHashMap = new HashMap<>(); // For the MapToken when created

        // Checks what part of the string we are in
        boolean onKey = false;
        boolean onValue = false;
        boolean pastEqual = false;


        StringBuilder placeHolder = new StringBuilder(); // The text inside the key or value
        StringToken key = null; // the key for the hashMap
        Token value; // the value for the hashMap

        while((currChar = nextChar()) != 0) {
            if (currChar == '"') { // The start of a string value of the start of a key
                if (!onKey && !pastEqual && key == null) { // Start of a key
                    onKey = true;
                } else if(onKey){ // end of the key
                    key = new StringToken(placeHolder.toString());
                    onKey = false;
                    placeHolder = new StringBuilder();
                }else if(onValue){ // end of the value
                    onValue = false;
                    value = new StringToken(placeHolder.toString());
                    placeHolder = new StringBuilder();
                    pastEqual = false;
                    currHashMap.put(key, value);
                }else if(pastEqual){ // start of the value
                    onValue = true;
                }else{ // Error missed the equal token ":"
                    throw new Exception("Missing EQUAL_TOKEN \":\" after \""+key.getValue()+"\" at index "+index);
                }
            }else if(currChar == ':'){ // equal the value that comes next
                pastEqual = true;
            }else if(currChar == '}'){ // End of this hashmap
                return new MapToken(currHashMap);
            }else if (onKey || onValue){ // the inside text of the key or value
                placeHolder.append(currChar);
            }else if(currChar == ','){
                /* @todo make an error check for a , and if its in the right place */
                key = null;
            }
        }
        // The hashMap is invalid and should be doubled check for an error
        throw new Exception("Object HashMap did not have a closing token: \"}\"");
    }

    private char nextChar(){ // Moves to the next char in the json String
        index++;
        if(index == json.length() + 1){
            return 0;
        }
        return json.charAt(index - 1);
    }
}
