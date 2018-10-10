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
        this.toEncryptUpTable = new String[][]{
                {"a", "ą", "b", "c", "ć", "d"},
                {"e", "ę", "f", "g", "h", "i"},
                {"j", "k", "l", "ł", "m", "n"},
                {"ń", "o", "ó", "p", "q", "r"},
                {"s", "ś", "t", "u", "v", "w"},
                {"x", "y", "z", "ź", "ż", " "},
        };
        this.toEncryptDownTable = this.toEncryptUpTable;
        this.toDecryptUpTable = this.toEncryptUpTable;
        this.toUpperMatrix(this.toDecryptUpTable);
        this.toDecryptDownTable = this.toDecryptUpTable;
        this.shuffleMatrix(this.toEncryptUpTable);
        this.shuffleMatrix(this.toEncryptDownTable);
        this.shuffleMatrix(this.toDecryptUpTable);
        this.shuffleMatrix(this.toDecryptDownTable);
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
                if(matrix[i][j] == string){
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
        Position charTwoPosition = getCharPosition(charTwo, this.toDecryptDownTable);
        String encrypted = this.toDecryptUpTable[charOnePosition.row][charTwoPosition.col] + this.toDecryptDownTable[charTwoPosition.row][charOnePosition.col];
        return encrypted;
    }

    public String encryptString(String in) throws Throwable {
        if((in.length() % 2) == 0){
            String out = "";
            for(int i = 0; i < in.length() - 1; i = i + 2){
                out += encryptPair(String.valueOf(in.charAt(i)), String.valueOf(in.charAt(i + 1)));
            }
            return out;
        }else{
            throw new Throwable("Tekst wejsciowy zawiera nieparzysta liczbe znakow");
        }
    }

    public String decryptString(String in){
        //TODO
        return new String();
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
