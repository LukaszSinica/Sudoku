package org.openjfx.javafx;

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private Integer[][] playerBoard = new Integer[9][9]; 
    private String difficulty; 
    private Sudoku sudoku;

    @FXML
    private GridPane sudokuGrid;
	
    @FXML
    private Label difficultyLabel;
    
    @FXML
    private Label errorCounter;
    
    @FXML
    public void initialize() {
        setSudoku(new Sudoku());
    }
    
	@FXML
	private void backToMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
                
        Stage stage = (Stage) sudokuGrid.getScene().getWindow();        
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
	}

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        setupGameBoard();
    }

    private void setupGameBoard() {
        difficultyLabel.setText(difficulty);
        int difficultyNumber = convertDifficultyToNumber(difficulty);
        
        Sudoku.setStartBoard(Sudoku.board);
        Sudoku.getTheBoard(Sudoku.board, Sudoku.sudokuNumbers);
        
        for (int i = 0; i < 9; i++) {
            playerBoard[i] = Arrays.copyOf(Sudoku.board[i], 9);
        }
        
        Sudoku.preparePlayerBoard(playerBoard, difficultyNumber);
        setUpBoardInGridPane();
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
                sudokuGrid.add(cell, col, row);
                textFields[row][col] = cell;
            }
        }
    }
    
    private int convertDifficultyToNumber(String difficulty) {
        switch (difficulty) {
            case "Medium":
                return 2;
            case "Hard":
                return 3;
            default:
                return 1;
        }
    }

    private void checkSingleCell(int row, int col) {
        TextField cell = textFields[row][col];
        String userInput = cell.getText();

        if (!userInput.isEmpty() && cell.isEditable()) {
            try {
                int number = Integer.parseInt(userInput);

                if (Sudoku.checkAnswer(row, col, number, Sudoku.board, playerBoard)) {
                    cell.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
                } else {
                    cell.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    counter++;
                    errorCounter.setText(counter.toString());
                }
            } catch (NumberFormatException e) {
                cell.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            }
        } else if (cell.isEditable()) {
            cell.setStyle(null);
        }
    }


	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
}
