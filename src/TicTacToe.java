// Joseph Wang
// 5/1/2021
// Practice JavaFX program to play Tic Tac Toe

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;

public class TicTacToe extends Application {
    // indicate which player has a turn, initially X player
    private char whoseTurn = 'X';

    // create and initialize cell
    private Cell[][] cell = new Cell[3][3];

    // create and initialize status label
    private Label lblStatus = new Label("X's turn to play");

    @Override // override start method in Application class
    public void start(Stage primaryStage) {
        // pane to hold cell
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                pane.add(cell[i][j] = new Cell(), j, i);
            
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);
        
        // create scene and place it in stage
        Scene scene = new Scene(borderPane, 450, 170);
        primaryStage.setTitle("TicTacToe"); // set stage title
        primaryStage.setScene(scene); // place scene in stage
        primaryStage.show(); // display stage
    }

    /** determine if the cells are all occupied */
    public boolean isFull() {
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if (cell[i][j].getToken() == ' ') 
                    return false;

        return true;
    }

    /** determine if player with specified token wins */
    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++) // win by three in a row
            if (cell[i][0].getToken() == token 
                    && cell[i][1].getToken() == token 
                    && cell[i][2].getToken() == token) {
                return true;
            }

        for (int j = 0; j < 3; j++) // win by three in a column
            if (cell[0][j].getToken() == token 
                    && cell[1][j].getToken() == token 
                    && cell[2][j].getToken() == token) {
                return true;
            }
        
        if (cell[0][0].getToken() == token // one diagonal
                && cell[1][1].getToken() == token 
                && cell[2][2].getToken() == token) {
            return true;
        }

        if (cell[0][2].getToken() == token // second diagonal
                && cell[1][1].getToken() == token 
                && cell[2][0].getToken() == token) {
            return true;
        }

        return false;
    }

    // inner class for cell
    public class Cell extends Pane {
        // token used for this cell
        private char token  = ' ';
        
        public Cell() { // constructor
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        /** return token */
        public char getToken() {
            return token;
        }

        /** set new token */
        public void setToken(char c) {
            token = c;

            if (token == 'X') {
                Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10)); // bind positions if resizing frame?
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                line2.startYProperty().bind(this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));

                // add lines to pane
                this.getChildren().addAll(line1, line2);
            }
            else if (token == 'O') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, 
                    this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.WHITE);
                
                getChildren().add(ellipse);
            }
        }

        /** handle mouse click event */
        private void handleMouseClick() {
            // if cell is empty and game not over
            if (token == ' ' && whoseTurn != ' ') {
                setToken(whoseTurn); // set token in cell

                // check game status
                if (isWon(whoseTurn)) {
                    lblStatus.setText(whoseTurn + " won! The game is over");
                    whoseTurn = ' '; // game is over
                }
                else if (isFull()) {
                    lblStatus.setText("Draw! The game is over");
                    whoseTurn = ' '; // game is over
                }
                else {
                    // change turn
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
                    // display whose turn
                    lblStatus.setText(whoseTurn + "'s turn");
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}