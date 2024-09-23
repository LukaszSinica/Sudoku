package org.lukaszsinica.sudoku;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SudokuMain extends Application {

	private static Scene scene;

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(SudokuMain.class.getResource(fxml + ".fxml"));

		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(SudokuMain.class.getResource("primary.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		scene = new Scene(root, 640, 480);
		stage.setScene(scene);
		stage.show();
	}

}