package org.openjfx.javafx;

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GamePanelController {

    private TextField[][] textFields = new TextField[9][9];
    private Integer[][] playerBoard = new Integer[9][9]; 
    private String difficulty; 
    private Sudoku sudoku;

    @FXML
    private GridPane sudokuGrid;
	
    @FXML
    private Label difficultyLabel;
    
    @FXML
    public void initialize() {
        setSudoku(new Sudoku());
    }
    
	@FXML
	private void backToMenu() throws IOException {
		App.setRoot("primary");
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
                }

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

    public void checkBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String userInput = textFields[row][col].getText();
                if (!userInput.isEmpty()) {
                    int number = Integer.parseInt(userInput);
                    Sudoku.checkAnswer(row, col, number, Sudoku.board, playerBoard);
                }
            }
        }
        setUpBoardInGridPane();
    }

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
}
