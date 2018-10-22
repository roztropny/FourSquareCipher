public class FullMatrix {
    public String[][] toEncryptUpTable;
    public String[][] toEncryptDownTable;
    public String[][] toDecryptUpTable;
    public String[][] toDecryptDownTable;

    public FullMatrix(String[][] toEncryptUpTable, String[][] toEncryptDownTable, String[][] toDecryptUpTable, String[][] toDecryptDownTable) {
        this.toEncryptUpTable = toEncryptUpTable;
        this.toEncryptDownTable = toEncryptDownTable;
        this.toDecryptUpTable = toDecryptUpTable;
        this.toDecryptDownTable = toDecryptDownTable;
    }
}
