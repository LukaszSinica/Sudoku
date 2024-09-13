package org.openjfx.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GamePanelController {

	@FXML
	private Button backButton;
	
	@FXML
	private void backToMenu() throws IOException {
		App.setRoot("primary");
	}
}
