public class Main {
    public static void main(String[] args) {
        FourSquareCipher fsc = new FourSquareCipher();
        String[][] matrix = fsc.getToEncryptTable1();
        String str = new String();
        for(int i=0; i< matrix.length; i++, str = ""){
            for (int j = 0; j < matrix[i].length; j++){
                str = str+matrix[i][j];
            }
            System.out.println(str);
        }
        System.out.println(1.5 % 1);
    }
}
