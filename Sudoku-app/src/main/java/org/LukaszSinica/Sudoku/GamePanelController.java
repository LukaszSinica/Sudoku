package org.LukaszSinica.Sudoku;

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GamePanelController {

	private Integer counter = 0;
	private TextField[][] textFields = new TextField[9][9];
	public Integer[][] playerBoard = new Integer[9][9];
	private String difficulty;
	private Sudoku sudoku;
	private Integer numberOfAnswers = 0;
	private Integer numberOfAnsweredTiles = 0;
	private boolean gameOver = false;
	private boolean gameWin = false;
	@FXML
	private GridPane sudokuGrid;

	@FXML
	private Label difficultyLabel;

	@FXML
	private Label errorCounter;

	@FXML
	private Label mainLabel;

	@FXML
	private void backToMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
		Parent root = loader.load();

		Stage stage = (Stage) sudokuGrid.getScene().getWindow();
		Scene scene = new Scene(root, 640, 480);

		stage.setScene(scene);
		stage.show();
	}

	private void checkSingleCell(int row, int col) {
		if (gameOver || gameWin) {
			return;
		}
		TextField cell = textFields[row][col];
		String userInput = cell.getText();

		if (!userInput.isEmpty() && cell.isEditable()) {
			try {
				int number = Integer.parseInt(userInput);

				if (Sudoku.checkAnswer(row, col, number, Sudoku.getBoard(), playerBoard)) {
					cell.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
					cell.setEditable(false);
					numberOfAnsweredTiles++;
					if (numberOfAnsweredTiles == numberOfAnswers) {
						gameWin = true;
						endTheGame();
					}
				} else {
					cell.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
					counter++;
					errorCounter.setText(counter.toString() + "/3");
				}
				if (counter == 3) {
					gameOver = true;
					endTheGame();
				}
			} catch (NumberFormatException e) {
				cell.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
			}
		} else if (cell.isEditable()) {
			cell.setStyle(null);
		}
	}

	private int convertDifficultyToNumber(String difficulty) {
		switch (difficulty) {
		case "Medium":
			numberOfAnswers = Sudoku.DEFAULT_NUMBER_OF_ERRORS * 2;
			return 2;
		case "Hard":
			numberOfAnswers = Sudoku.DEFAULT_NUMBER_OF_ERRORS * 3;
			return 3;
		default:
			numberOfAnswers = Sudoku.DEFAULT_NUMBER_OF_ERRORS;
			return 1;
		}
	}

	private void endTheGame() {
		if (gameOver) {
			mainLabel.setText("Game Over");
			mainLabel.setStyle("-fx-text-fill: red");
		} else {
			mainLabel.setText("You Won!");
			mainLabel.setStyle("-fx-text-fill: green");
		}
		sudokuGrid.setDisable(true);
		sudokuGrid.requestFocus();
	}

	public Sudoku getSudoku() {
		return sudoku;
	}

	@FXML
	public void initialize() {
		setSudoku(new Sudoku());
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
		setupGameBoard();
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	private void setUpBoardInGridPane() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				TextField cell = new TextField();
				cell.setPrefWidth(50);
				cell.setPrefHeight(50);

				if (playerBoard[row][col] != 0) {
					cell.setText(playerBoard[row][col].toString());
					cell.setEditable(false);
				} else {
					cell.setText("");
					cell.setTextFormatter(new TextFormatter<>(change -> {
						String newText = change.getControlNewText();
						if (newText.matches("[1-9]?")) {
							return change;
						}
						return null;
					}));
				}
				int currentRow = row;
				int currentCol = col;
				cell.textProperty().addListener((observable, oldValue, newValue) -> {
					checkSingleCell(currentRow, currentCol);
				});
				cell.setAlignment(Pos.CENTER);
				StringBuilder style = new StringBuilder("-fx-font-weight: bold; -fx-border-color: black; -fx-border-width: ");

				boolean thickTop = (row % 3 == 0);
				boolean thickLeft = (col % 3 == 0);
				boolean thickRight = (col == 8);
				boolean thickBottom = (row == 8);

				style.append(thickTop ? "2" : "1").append("px ")
						.append(thickRight ? "2" : ((col + 1) % 3 == 0 ? "2" : "1")).append("px ")
						.append(thickBottom ? "2" : ((row + 1) % 3 == 0 ? "2" : "1")).append("px ")
						.append(thickLeft ? "2" : "1").append("px;");

				cell.setStyle(style.toString());
				sudokuGrid.add(cell, col, row);
				textFields[row][col] = cell;
			}
		}
		errorCounter.setText(counter.toString() + "/3");
	}

	private void setupGameBoard() {
		difficultyLabel.setText(difficulty);
		int difficultyNumber = convertDifficultyToNumber(difficulty);

		Sudoku.setStartBoard(Sudoku.getBoard());
		Sudoku.getTheBoard(Sudoku.getBoard(), Sudoku.getSudokuNumbers());

		for (int i = 0; i < 9; i++) {
			playerBoard[i] = Arrays.copyOf(Sudoku.getBoard()[i], 9);
		}

		Sudoku.preparePlayerBoard(playerBoard, difficultyNumber);
		setUpBoardInGridPane();
	}
}
