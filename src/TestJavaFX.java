import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestJavaFX extends Application {
    // override start method in Application class
    @Override 
    public void start(Stage primaryStage) {
        // create a scene and place a button in the scene
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 200, 250);
        
        // set stage title
        primaryStage.setTitle("MyJavaFX");

        // place scene in stage
        primaryStage.setScene(scene);

        // display stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}