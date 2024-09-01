package com.LukaszSinica.Sudoku.Sudoku.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Sudoku
// create 9x9 array with 0 in it [x]
// create 1 to 9 array  [x]
// Function for getting board: [x]
// Check if row have the number [x]
// Check if col have the number [x]
// populate the array [x]
// get the full board [x]
// print the board [x]


@SpringBootApplication
public class SudokuAppApplication {
	static Integer[][] board = new Integer[9][9];
	static Integer[] sudokuNumbers = {1,2,3,4,5,6,7,8,9};
    
	public static void main(String[] args) {
		SpringApplication.run(SudokuAppApplication.class, args);
		SudokuAppApplication sudokuApp = new SudokuAppApplication();
		sudokuApp.setStartBoard(board);
		sudokuApp.getTheBoard(board, sudokuNumbers);
		sudokuApp.printBoard(board);

	}
	
	private void setStartBoard(Integer[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				board[i][j] = 0;
			}
		}
	}

	private boolean getTheBoard(Integer[][] board, Integer[] sudokuNumbers) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
		        if(board[i][j] == 0) {
					//shuffling the sudokuNumbers
		        	List<Integer> intList = Arrays.asList(sudokuNumbers);  
		 	        Collections.shuffle(intList);  
		 	        intList.toArray(sudokuNumbers);
			        for(int value : sudokuNumbers) {
			        	//checking row
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
		
		for(int i = 0; i < board.length; i++) {
			if(board[i][col] == number) {
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
	
	private static boolean checkBoard(Integer[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void printBoard(Integer[][] board) {
	    for (int i = 0; i < board.length; i++) {
	        if (i % 3 == 0 && i != 0) {
	            // Print a horizontal line after every 3 rows
	            System.out.println("------+-------+------");
	        }
	        
	        for (int j = 0; j < board[i].length; j++) {
	            if (j % 3 == 0 && j != 0) {
	                // Print a vertical line after every 3 columns
	                System.out.print("| ");
	            }
	            
	            // Print the board element
	            System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " "); // Using '.' for empty cells
	        }
	        System.out.println(); // Move to the next line after each row
	    }
	}

}
