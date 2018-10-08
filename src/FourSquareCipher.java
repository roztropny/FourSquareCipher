import java.util.Arrays;
import java.util.Collections;

public class FourSquareCipher {
    private String[][] toEncryptTable1;
    private String[][] toEncryptTable2;
    private String[][] toDecryptTable1;
    private String[][] toDecryptTable2;

    public FourSquareCipher() {
        this.toEncryptTable1 = new String[][]{
                {"a", "ą", "b", "c", "ć", "d"},
                {"e", "ę", "f", "g", "h", "i"},
                {"j", "k", "l", "ł", "m", "n"},
                {"ń", "o", "ó", "p", "q", "r"},
                {"s", "ś", "t", "u", "v", "w"},
                {"x", "y", "z", "ź", "ż", " "},
        };
        this.toEncryptTable2 = this.toEncryptTable1;
        this.toDecryptTable1 = this.toEncryptTable1;
        this.toUpperMatrix(this.toDecryptTable1);
        this.toDecryptTable2 = this.toDecryptTable1;
        this.shuffleMatrix(this.toEncryptTable1);
        this.shuffleMatrix(this.toEncryptTable2);
        this.shuffleMatrix(this.toDecryptTable1);
        this.shuffleMatrix(this.toDecryptTable2);
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

    public String[][] getToEncryptTable1(){
        return this.toEncryptTable1;
    }

    public String encryptString(String in){//TODO
        return new String();
    }

    public String decryptString(String in){
        //TODO
        return new String();
    }




}
