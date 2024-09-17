package org.openjfx.javafx;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


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

public class Sudoku {
	static final int DEFAULT_NUMBER_OF_ERRORS = 8;

	static Integer[][] board = new Integer[9][9];
	static Integer[] sudokuNumbers = {1,2,3,4,5,6,7,8,9};

	static boolean checkAnswer(Integer row, Integer col, Integer tileAnswer, Integer[][] board, Integer[][] playerBoard) {
		if(playerBoard[row][col] != 0) {
			return false;
		} else if(board[row][col] == tileAnswer) {
			playerBoard[row][col] = tileAnswer;
			return true;
		} else {
			return false;
		}
	}

	static void setStartBoard(Integer[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = 0;
			}
		}
	}

	static boolean getTheBoard(Integer[][] board, Integer[] sudokuNumbers) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
		        if(board[i][j] == 0) {
					//shuffling the sudokuNumbers
		        	List<Integer> intList = Arrays.asList(sudokuNumbers);
		 	        Collections.shuffle(intList);
		 	        intList.toArray(sudokuNumbers);
			        for(int value : sudokuNumbers) {
			        	//checking row and col
			        	if(!checkRow(board[i], value)  && !checkCol(board, value, j)) {
			        		Integer[][] square = new Integer[3][3];
			        		checkWorkingSquare(square, board, i, j);
			        		if(!checkRow(square[0], value) && !checkRow(square[1], value) && !checkRow(square[2], value) ) {
			        			board[i][j] = value;
			        			if(getTheBoard(board, sudokuNumbers)) {
			        				return true;
			        			}
	                            board[i][j] = 0;
			        		}
	 		        	}
			        }
			        return false;
		        }
			}
		}
		return true;
	}

	private static boolean checkRow(Integer[] board, int number) {
		for(int element : board) {
			if(element == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkCol(Integer[][] board, int number, int col) {

		for (Integer[] element : board) {
			if(element[col] == number) {
				return true;
			}
		}
		return false;
	}

	private static void checkWorkingSquare(Integer[][] square, Integer[][] board, int row, int col) {
	    int startRow = (row / 3) * 3;
	    int startCol = (col / 3) * 3;

	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            square[i][j] = board[startRow + i][startCol + j];
	        }
	    }
	}

	static void preparePlayerBoard(Integer[][] playerBoard, Integer difficulty) {
		Random random = new Random();
		String[] chosenFields = new String[difficulty * DEFAULT_NUMBER_OF_ERRORS];
		chosenFields[(difficulty * DEFAULT_NUMBER_OF_ERRORS) - 1] = "0";
		int i = 0;
		while(chosenFields[(difficulty * DEFAULT_NUMBER_OF_ERRORS) - 1] == "0") {
			int row = random.nextInt(8) + 0;
			int col = random.nextInt(8) + 0;
			String stringField = String.valueOf(row) + String.valueOf(col);
			List<String> chosenFieldsList = Arrays.asList(chosenFields);
			if(chosenFieldsList.contains(stringField)) {
				continue;
			}
			chosenFields[i] = stringField;
			playerBoard[row][col] = 0;
			i++;
		}
	}
}
