package org.openjfx.javafx;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Sudoku {
	public static final int DEFAULT_NUMBER_OF_ERRORS = 8;

	public static Integer[][] board = new Integer[9][9];
	public static Integer[] sudokuNumbers = {1,2,3,4,5,6,7,8,9};

	public static boolean checkAnswer(Integer row, Integer col, Integer tileAnswer, Integer[][] board, Integer[][] playerBoard) {
		if(playerBoard[row][col] != 0) {
			return false;
		} else if(board[row][col] == tileAnswer) {
			playerBoard[row][col] = tileAnswer;
			return true;
		} else {
			return false;
		}
	}

	public static void setStartBoard(Integer[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = 0;
			}
		}
	}

	public static boolean getTheBoard(Integer[][] board, Integer[] sudokuNumbers) {
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

	public static void preparePlayerBoard(Integer[][] playerBoard, Integer difficulty) {
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

	public static Integer[][] getBoard() {
		return board;
	}

	public static void setBoard(Integer[][] board) {
		Sudoku.board = board;
	}

	public static Integer[] getSudokuNumbers() {
		return sudokuNumbers;
	}

	public static void setSudokuNumbers(Integer[] sudokuNumbers) {
		Sudoku.sudokuNumbers = sudokuNumbers;
	}
}
