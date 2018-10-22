import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

public class Main extends Application {

    public static void main(String[] args) {
        /*
        FourSquareCipher fsc = new FourSquareCipher();
        String[][] matrix = fsc.getToEncryptUpTable();
        String str = new String();
        for(int i=0; i< matrix.length; i++, str = ""){
            for (int j = 0; j < matrix[i].length; j++){
                str = str+matrix[i][j];
            }
            System.out.println(str);
        }
        System.out.println(1.5 % 1);
        */
        launch(args);
        /*
        String[][] arr = {{"1", "2"}, {"3", "4"}, {"5", "6"}};
        String[][] arr2 = arr;
        for(int i = 0; i < arr2.length; i++){
            Collections.shuffle(Arrays.asList(arr2));
        }
        Collections.shuffle(Arrays.asList(arr2));
        String str = new String();
        for(int i=0; i< arr.length; i++, str = ""){
            for (int j = 0; j < arr[i].length; j++){
                str = str+arr[i][j];
            }
            System.out.println(str);
        }
        for(int i=0; i< arr.length; i++, str = ""){
            for (int j = 0; j < arr[i].length; j++){
                str = str+arr2[i][j];
            }
            System.out.println(str);
        }
        */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent menu = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Four Square CipherController");
        primaryStage.setScene(new Scene(menu));
        primaryStage.show();
    }
}
