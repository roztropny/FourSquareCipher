import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;

public class FourSquareCipher {
    /*
    ------------------------------------------
    |toEncryptUpTable   | toDecryptUpTable   |
    |----------------------------------------|
    |toDecryptDownTable | toEncryptDownTable |
    ------------------------------------------
     */
    private String[][] toEncryptUpTable;
    private String[][] toEncryptDownTable;
    private String[][] toDecryptUpTable;
    private String[][] toDecryptDownTable;

    public FourSquareCipher() {


    }

    public void randMatrix(){
        String[][] charArray = new String[][]{
                {"a", "ą", "b", "c", "ć", "d"},
                {"e", "ę", "f", "g", "h", "i"},
                {"j", "k", "l", "ł", "m", "n"},
                {"ń", "o", "ó", "p", "q", "r"},
                {"s", "ś", "t", "u", "v", "w"},
                {"x", "y", "z", "ź", "ż", " "},
        };
        this.randMatrix(charArray);
    }

    public void randMatrix(String[][] charArray){
        this.toEncryptUpTable = new String[charArray.length][];
        this.toEncryptDownTable = new String[charArray.length][];
        this.toDecryptUpTable = new String[charArray.length][];
        this.toDecryptDownTable = new String[charArray.length][];
        for(int i = 0; i < charArray.length; i++){
            this.toEncryptUpTable[i] = charArray[i].clone();
            this.toEncryptDownTable[i] = charArray[i].clone();
            this.toDecryptUpTable[i] = charArray[i].clone();
            this.toDecryptDownTable[i] = charArray[i].clone();
        }
        this.toUpperMatrix(this.toDecryptUpTable);
        this.toUpperMatrix(this.toDecryptDownTable);
        this.shuffleMatrix(this.toEncryptUpTable);
        this.shuffleMatrix(this.toEncryptDownTable);
        this.shuffleMatrix(this.toDecryptUpTable);
        this.shuffleMatrix(this.toDecryptDownTable);
    }

    public void printMatrix(String[][] matrix){
        String str = new String();
        for(int i=0; i< matrix.length; i++, str = ""){
            for (int j = 0; j < matrix[i].length; j++){
                str = str+matrix[i][j];
            }
            System.out.println(str);
        }
    }

    private void toUpperArray(String[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = array[i].toUpperCase();
        }
    }

    public void toUpperMatrix(String[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            this.toUpperArray(matrix[i]);
        }
    }

    private void shuffleArray(String[] array){
        Collections.shuffle(Arrays.asList(array));
    }

    public void shuffleMatrix(String[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            this.shuffleArray(matrix[i]);
        }
        Collections.shuffle(Arrays.asList(matrix));
    }

    private Position getCharPosition(String string, String[][] matrix) throws Throwable{
        Position position = null;
        outer : for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j].equals(string)){
                    position = new Position(i, j);
                    break outer;
                }
            }
        }
        if(position == null){
            throw new Throwable("Znak \""+string+"\" nie znajduje sie w macerzy kodowania");
        }
        return position;
    }

    private String encryptPair(String charOne, String charTwo) throws Throwable{
        Position charOnePosition = getCharPosition(charOne, this.toEncryptUpTable);
        Position charTwoPosition = getCharPosition(charTwo, this.toEncryptDownTable);
        String encrypted = this.toDecryptUpTable[charOnePosition.row][charTwoPosition.col] + this.toDecryptDownTable[charTwoPosition.row][charOnePosition.col];
        return encrypted;
    }

    private String decryptPair(String charOne, String charTwo) throws Throwable{
        Position charOnePosition = getCharPosition(charOne, this.toDecryptUpTable);
        Position charTwoPosition = getCharPosition(charTwo, this.toDecryptDownTable);
        String encrypted = this.toEncryptUpTable[charOnePosition.row][charTwoPosition.col] + this.toEncryptDownTable[charTwoPosition.row][charOnePosition.col];
        return encrypted;
    }

    public String encryptString(String in) throws Throwable {
        //in = in.replaceAll("\n","").replaceAll("\r", "");
        if((in.replaceAll("\n","").replaceAll("\r", "").length() % 2) == 0){
            String out = "";
            for(int i = 0; i < in.length() - 1; i = i + 2){
                if(String.valueOf(in.charAt(i)).equals("\n")){
                    out += "\n";
                    i--;
                }else if(String.valueOf(in.charAt(i + 1)).equals("\n")){
                    String str = encryptPair(String.valueOf(in.charAt(i)), String.valueOf(in.charAt(i + 2)));
                    str = str.charAt(0) + "\n" + str.charAt(1);
                    out += str;
                    i++;
                }else{
                    out += encryptPair(String.valueOf(in.charAt(i)), String.valueOf(in.charAt(i + 1)));
                }

            }
            return out;
        }else{
            throw new Throwable("Tekst wejsciowy zawiera nieparzysta liczbe znakow");
        }
    }

    public String decryptString(String in) throws Throwable{
        in = in.replaceAll("\n","").replaceAll("\r", "");
        if((in.replaceAll("\n","").replaceAll("\r", "").length() % 2) == 0){
            String out = "";
            for(int i = 0; i < in.length() - 1; i = i + 2){
                if(String.valueOf(in.charAt(i)).equals("\n")){
                    out += "\n";
                    i--;
                }else if(String.valueOf(in.charAt(i + 1)).equals("\n")){
                    String str = decryptPair(String.valueOf(in.charAt(i)), String.valueOf(in.charAt(i + 2)));
                    str = str.charAt(0) + "\n" + str.charAt(1);
                    out += str;
                    i++;
                }else{
                    out += decryptPair(String.valueOf(in.charAt(i)), String.valueOf(in.charAt(i + 1)));
                }
            }
            return out;
        }else{
            throw new Throwable("Tekst wejsciowy zawiera nieparzysta liczbe znakow");
        }
    }

    public String getMatrixJSON(){
        FullMatrix matrix = new FullMatrix(toEncryptUpTable, toEncryptDownTable, toDecryptUpTable,  toDecryptDownTable);
        String json = new Gson().toJson(matrix);
        return json;
    }

    public void setMatrixJSON(String json){
        FullMatrix matrix = new Gson().fromJson(json, FullMatrix.class);
        toEncryptUpTable = matrix.toEncryptUpTable;
        toEncryptDownTable = matrix.toEncryptDownTable;
        toDecryptUpTable = matrix.toDecryptUpTable;
        toDecryptDownTable = matrix.toDecryptDownTable;
    }

    public String[][] getToEncryptUpTable() {
        return toEncryptUpTable;
    }

    public String[][] getToEncryptDownTable() {
        return toEncryptDownTable;
    }

    public String[][] getToDecryptUpTable() {
        return toDecryptUpTable;
    }

    public String[][] getToDecryptDownTable() {
        return toDecryptDownTable;
    }
}
