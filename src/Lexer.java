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

    public ArrayList<Token> tokenize(){
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

    private MapToken mapToken(){
        HashMap<StringToken, Token> currHashMap = new HashMap<>(); // For the MapToken when created

        // Checks what part of the string we are in
        boolean onKey = false;
        boolean onValue = false;
        boolean pastEqual = false;

        String placeHolder = ""; // The text inside the key or value
        StringToken key = null; // the key for the hashMap
        Token value; // the value for the hashMap

        while((currChar = nextChar()) != 0) {
            if (currChar == '"') { // The start of a string value of the start of a key
                if (!onKey && !pastEqual) { // Start of a key
                    onKey = true;
                } else if(onKey){ // end of the key
                    onKey = false;
                    key = new StringToken(placeHolder);
                    placeHolder = "";
                }else if(onValue){ // end of the value
                    onValue = false;
                    value = new StringToken(placeHolder);
                    placeHolder = "";
                    pastEqual = false;
                    currHashMap.put(key, value);
                }else{ // start of the value
                    onValue = true;
                }
            }else if(currChar == ':'){ // equal the value that comes next
                pastEqual = true;
            }else if(currChar == '}'){ // End of this hashmap
                return new MapToken(currHashMap);
            }else if (onKey || onValue){ // the inside text of the key or value
                placeHolder += currChar;
            }
        }
        // The hashMap is invalid and should be doubled check for an error
        System.out.println("Object HashMap did not have a closing token: \"}\"");
        return null;
    }

    private char nextChar(){ // Moves to the next char in the json String
        index++;
        if(index == json.length() + 1){
            return 0;
        }
        return json.charAt(index - 1);
    }
}
