import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextEditor extends Application {
    /** method to create the BorderPane */
    public BorderPane getPane() {
        // create horizonal box for options with horizontal gap of 15 and padding of 5 all around
        HBox options = new HBox(15);
        // padding is top, right, bottom, left
        options.setPadding(new Insets(5, 5, 5, 5));

        // create buttons for options
        Button btnNew = new Button("New");
        Button btnOpen = new Button("Open");
        Button btnSave = new Button("Save");

        // add buttons to HBox
        options.getChildren().addAll(btnNew, btnOpen, btnSave);
        
        // create vertical box for line numbers with vertical gap of 15
        VBox lineNumbers = new VBox();
        lineNumbers.setPadding(new Insets(5, 5, 0, 5));

        // create labels for line numbers 1 - 10
        for (int i = 1; i < 10; i++) {
            lineNumbers.getChildren().add(new Label(String.valueOf(i)));
        }

        // create text area
        TextArea textArea = new TextArea();
        //textArea.setPadding(new Insets(5, 5, 5, 5));

        // create overall BorderPane
        BorderPane pane = new BorderPane();

        pane.setTop(options);
        pane.setLeft(lineNumbers);
        pane.setCenter(textArea);

        return pane;
    }

    // override start method in Application class
    @Override 
    public void start(Stage primaryStage) {
        // create a scene
        Scene scene = new Scene(getPane(), 800, 500);
        
        // set stage title
        primaryStage.setTitle("Wang Pad");

        // place scene in stage
        primaryStage.setScene(scene);

        // display stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}