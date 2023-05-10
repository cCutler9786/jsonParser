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
            throw new Exception("Did not have an open HashMap Token for the first character next to " + currChar + " At index: "+index);
        }
        return tokens;
    }

    private MapToken mapToken() throws Exception {

        HashMap<StringToken, Token> currHashMap = new HashMap<>(); // For the MapToken when created

        // Checks what part of the string we are in
        boolean onKey = false;
        boolean onValue = false;
        boolean pastEqual = false;
        boolean onNumber = false;
        boolean onUndefinedWord = false;

        StringBuilder placeHolder = new StringBuilder(); // The text inside the key or value
        StringToken key = null; // the key for the hashMap
        Token value = null; // the value for the hashMap


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
                }else if (value != null) {
                    throw new Exception("Unexpected Token at index "+index+", Expected \",\" got TOKEN");
                }else{ // Error missed the equal token ":"
                    throw new Exception("Missing EQUAL_TOKEN \":\" after \""+key.getValue()+"\" at index "+index);
                }

            }else if(currChar == ':'){ // equal the value that comes next
                pastEqual = true;
            }else if(currChar == '}'){ // End of this hashmap
                return new MapToken(currHashMap);
            }else if (onKey || onValue){ // the inside text of the key or value
                if(onNumber && !isNumber(currChar)){ // Checks it's a number and adds it if number is done
                    onValue = false;
                    value = new DoubleToken(Double.parseDouble(placeHolder.toString()));
                    placeHolder = new StringBuilder();
                    pastEqual = false;
                    currHashMap.put(key, value);

                    value = null;
                    key = null;
                    onNumber = false;
                }else if(onUndefinedWord && !Character.isLetter(currChar)) {
                    onValue = false;
                    if(placeHolder.toString().equals("true")){
                        value = new BoolToken(true);
                    }else if(placeHolder.toString().equals("false")){
                        value = new BoolToken(false);
                    }else{
                        throw new Exception("Boolean type error at index "+index+", Expected boolean got \""+placeHolder+"\"");
                    }
                    placeHolder = new StringBuilder();
                    pastEqual = false;
                    currHashMap.put(key, value);

                    value = null;
                    key = null;
                    onUndefinedWord = false;
                }else {
                    placeHolder.append(currChar);
                }
            }else if(currChar == ','){ // Checks if the "," is at the right place at then end of the line
                if(value == null){
                    throw new Exception("Unexpected Token \",\" at index "+index);
                }
                value = null;
                key = null;
            }else if(key != null && (value == null && isNumber(currChar))){ // if the value is a number
                onValue = true;
                onNumber = true;
                placeHolder.append(currChar);
            }else if(key != null && Character.isLetter(currChar)){
                onValue = true;
                onUndefinedWord = true;
                placeHolder.append(currChar);
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

    private boolean isNumber(char chr){
        if(chr == '.'){ return true; }
        try{
            Integer.parseInt(String.valueOf(chr));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
