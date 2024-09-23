package org.lukaszsinica.sudoku;

import java.awt.Button;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class PrimaryController {

	static ObservableList<String> list = FXCollections.observableArrayList();

	@FXML
	private ChoiceBox<String> difficulty;

	@FXML
	private Button button;

	@FXML
	public void initialize() {
		loadDifficulty();
	}

	public void loadDifficulty() {
		list.removeAll(list);
		String easy = "Easy";
		String medium = "Medium";
		String hard = "Hard";
		list.addAll(easy, medium, hard);
		difficulty.getItems().addAll(list);
		difficulty.setValue(easy);
	}

	@FXML
	private void startGame() throws IOException {
		String selectedDifficulty = difficulty.getValue();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePanel.fxml"));
		Parent root = loader.load();

		GamePanelController gamePanelController = loader.getController();
		gamePanelController.setDifficulty(selectedDifficulty);

		Stage stage = (Stage) difficulty.getScene().getWindow();
		Scene scene = new Scene(root, 640, 480);
		stage.setScene(scene);
		stage.show();
	}
}
