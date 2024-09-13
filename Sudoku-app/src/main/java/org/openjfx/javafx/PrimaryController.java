package org.openjfx.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class PrimaryController {

	static ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML
	private  ChoiceBox<String> difficulty;
	
    public void loadDifficulty() {
    	list.removeAll(list);
    	String easy = "easy";
    	String medium = "medium";
    	String hard = "hard";
    	list.addAll(easy,medium,hard);
    	difficulty.getItems().addAll(list);
    }
}
