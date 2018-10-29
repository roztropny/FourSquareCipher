import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent menu = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Four Square Cipher");
        primaryStage.setScene(new Scene(menu));
        primaryStage.show();
    }
}
