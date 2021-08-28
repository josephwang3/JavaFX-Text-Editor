// Joseph Wang
// 5/1/2021
// Practice JavaFX program to test different functionality

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class TestJavaFX extends Application {

    private static final String MEDIA_URL = "http://liveexample.pearsoncmg.com/common/sample.mp4";

    // override start method in Application class
    @Override 
    public void start(Stage primaryStage) throws Exception {

        /*Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/

        java.net.URL resource = getClass().getResource("organfinale.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        
        // create a scene and place a button in the scene
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 200, 250);
        
        // set stage title
        primaryStage.setTitle("MyJavaFX");

        // place scene in stage
        primaryStage.setScene(scene);

        // display stage
        primaryStage.show();

        /*String musicFileName = "organfinale.mp3";
        Media media = new Media("file://C:/Users/Work/OneDrive - LeTourneau University/15 LETU/21S/COSC 2103 CS 2/Java/FinalProject/JavaFXTest/src/organfinale.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}