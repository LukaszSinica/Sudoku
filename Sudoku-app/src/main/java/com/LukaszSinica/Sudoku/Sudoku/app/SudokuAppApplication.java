package com.LukaszSinica.Sudoku.Sudoku.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Sudoku
// create 9x9 array with 0 in it [x]
// create 1 to 9 array  [x]
// Function for getting board: [x]
// Check if row have the number [x]
// Check if col have the number [x]
// populate the array [x]
// get the full board [x]
// print the board [x]
// Creating player board with blank spaces[x]
// Allow player to chose the row and col he want to put the number [x]
// ALlow player to chose the number he want to select [x]
// Check for answer [x]
// Return the board with added answer on the board [x]

public class SudokuAppApplication extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 400, 300));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
