import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    private FourSquareCipher fsc;
    private FileHandler fh;

    @FXML
    GridPane MatrixGrid;
    @FXML
    TextArea toEncode;
    @FXML
    TextArea toDecode;

    public Controller() {
        this.fsc = new FourSquareCipher();
        this.fh = new FileHandler();
    }

    private void RisePopup(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

    private void setMatrix(){
        try {
            this.setOneMatrix(fsc.getToEncryptUpTable(), 0, 0);
            fsc.printMatrix(fsc.getToEncryptUpTable());
            this.setOneMatrix(fsc.getToDecryptUpTable(), 1, 0);
            fsc.printMatrix(fsc.getToDecryptUpTable());
            this.setOneMatrix(fsc.getToDecryptDownTable(), 0, 1);
            fsc.printMatrix(fsc.getToDecryptDownTable());
            this.setOneMatrix(fsc.getToEncryptDownTable(), 1, 1);
            fsc.printMatrix(fsc.getToEncryptDownTable());
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    private void setOneMatrix(String[][] array, int x, int y){
        ColumnConstraints cc = new ColumnConstraints();
        //cc.setPercentWidth(100.0/);
        GridPane matrix = new GridPane();
        for(int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){

                matrix.add(new TextField(array[i][j]), i, j);
            }
        }
        matrix.setGridLinesVisible(true);
        MatrixGrid.add(matrix, x, y);
    }

    @FXML
    protected void randMatrix(){
        try {
            fsc.randMatrix();
            this.setMatrix();
        }catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void encode() throws Throwable {
        try {
            this.toDecode.setText(fsc.encryptString(this.toEncode.getText()));
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void encodeFromFile() throws IOException {
        try {
            File file = new FileChooser().showOpenDialog(toEncode.getScene().getWindow());
            if (file != null) {
                toEncode.setText(FileHandler.readFile(file.getPath()));
            }
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void encodeToFile() throws IOException {
        try {
            File file = new FileChooser().showSaveDialog(toEncode.getScene().getWindow());
            if (file != null) {
                System.out.println(file.getPath());
                FileHandler.writeFile(file.getPath(), toEncode.getText());
            }
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void decode(){
        try {
            this.toEncode.setText(fsc.decryptString(this.toDecode.getText()));
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void decodeFromFile() throws IOException {
        try {
            File file = new FileChooser().showOpenDialog(toDecode.getScene().getWindow());
            if (file != null) {
                toDecode.setText(FileHandler.readFile(file.getPath()));
            }
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void decodeToFile() throws IOException {
        try {
            File file = new FileChooser().showSaveDialog(toDecode.getScene().getWindow());
            if (file != null) {
                System.out.println(file.getPath());
                FileHandler.writeFile(file.getPath(), toDecode.getText());
            }
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void MatrixToFile() throws IOException {
        try {
            File file = new FileChooser().showSaveDialog(MatrixGrid.getScene().getWindow());
            if (file != null) {
                System.out.println(file.getPath());
                FileHandler.writeFile(file.getPath(), fsc.getMatrixJSON());
            }
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

    @FXML
    protected void MatrixFromFile() throws IOException {
        try {
            File file = new FileChooser().showOpenDialog(MatrixGrid.getScene().getWindow());
            if (file != null) {
                fsc.setMatrixJSON(FileHandler.readFile(file.getPath()));
                this.setMatrix();
            }
        }
        catch (Throwable e){
            RisePopup(e.getMessage());
        }
    }

}
