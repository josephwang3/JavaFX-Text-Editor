// Joseph Wang
// 5/1/2021
// Practice JavaFX program to display a circle as the base of an emoji

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ShowCircle extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a circle and set its properties
    Circle circle = new Circle();
    circle.setCenterX(100);
    circle.setCenterY(100);
    circle.setRadius(50);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.YELLOW);

    // Create eyes
    Circle eye1 = new Circle(75, 85, 10);
    eye1.setStroke(Color.BLACK);
    eye1.setFill(Color.BLUE);
    
    Circle eye2 = new Circle(125, 85, 10);
    eye2.setStroke(Color.BLACK);
    eye2.setFill(Color.BLUE);
    
    // Create a pane to hold the circle 
    Pane pane = new Pane();
    pane.getChildren().addAll(circle, eye1, eye2);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("ShowCircle"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}