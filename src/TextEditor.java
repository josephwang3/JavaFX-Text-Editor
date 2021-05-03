import java.awt.Toolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.Optional;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TextEditor extends Application {
    int lineNumber = 1;
    VBox lineNumbers = new VBox();

    /** method to create the BorderPane */
    public BorderPane getPane(Stage stage) {
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
        lineNumbers.setPadding(new Insets(5, 5, 0, 5));

        // add line number for line 1
        lineNumbers.getChildren().add(new Label(String.valueOf(lineNumber)));
        lineNumber = lineNumber + 1;

        // create text area
        TextArea textArea = new TextArea();

        // create overall BorderPane
        BorderPane pane = new BorderPane();

        pane.setTop(options);
        pane.setLeft(lineNumbers);
        pane.setCenter(textArea);

        // add line numbers
        textArea.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                lineNumbers.getChildren().add(new Label(String.valueOf(lineNumber)));
                lineNumber = lineNumber + 1;        
            }
        });

        // when new button pressed, open new window
        btnNew.setOnAction(e -> {
            // create a scene
            Stage newStage = new Stage();
            Scene scene = new Scene(getPane(stage), 800, 500);
            newStage.setTitle("Wang Pad");
            newStage.setScene(scene);
            newStage.show();
        });

        // create a FileChooser
        FileChooser fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File("C:\\Users\\Work\\OneDrive - LeTourneau University\\15 LETU\\21S\\COSC 2103 CS 2\\Java\\FinalProject\\JavaFXTest\\src"));

        // set open button to open windows explorer to open a file
        btnOpen.setOnAction(e -> {
            try {
                // get file from FileChooser
                File openFile = fileChooser.showOpenDialog(stage);
                
                // create Scanner to read file
                Scanner input = new Scanner(openFile);

                // read data to text area
                while (input.hasNextLine()) {
                    textArea.appendText(input.nextLine() + "\n");
                }
                input.close();

                // change stage title to opened file name
                stage.setTitle(openFile.getName() + " - Wang Pad");
            } 
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        // set save button to open windows explorer to save a file
        btnSave.setOnAction(e -> {
            try {
                // get file name from FileChooser
                File saveFile = fileChooser.showSaveDialog(stage);
                
                // create PrintWriter to write output
                PrintWriter output = new PrintWriter(saveFile);

                // write data from text area to file
                output.write(textArea.getText());
                output.close();

                // change stage title to saved file name
                stage.setTitle(saveFile.getName() + " - Wang Pad");
            } 
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        return pane;
    }

    // override start method in Application class
    @Override 
    public void start(Stage primaryStage) {
        // create a scene
        Scene scene = new Scene(getPane(primaryStage), 800, 500);
        
        // set stage title
        primaryStage.setTitle("Untitled - Wang Pad");

        // place scene in stage
        primaryStage.setScene(scene);

        // display stage
        primaryStage.show();

        // show close confirmation window
        primaryStage.setOnCloseRequest(e -> {
            // play default windows sound using AWT
            Toolkit.getDefaultToolkit().beep();

            // create confirmation alert
            Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            closeConfirmation.setTitle("Confirm Close");
            closeConfirmation.setHeaderText(null);
            closeConfirmation.setContentText("Are you sure you want to close this window?");

            // OK and Cancel buttons for confirmation window
            ButtonType btnTypeOK = new ButtonType("OK");
            ButtonType btnTypeCancel = new ButtonType("Cancel");
            closeConfirmation.getButtonTypes().setAll(btnTypeOK, btnTypeCancel);

            // track results of close confirmation box
            Optional<ButtonType> result = closeConfirmation.showAndWait();

            // for cancel button, don't close window
            if (result.isPresent() && result.get() == btnTypeCancel) {
                e.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}