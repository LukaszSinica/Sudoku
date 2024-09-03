package com.LukaszSinica.Sudoku.Sudoku.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
// Creating player board with blank spaces[x]
// 
//
//
//

@SpringBootApplication
public class SudokuAppApplication {
	static final int DEFAULT_NUMBER_OF_ERRORS = 8;

	static Integer[][] board = new Integer[9][9];
	static Integer[] sudokuNumbers = {1,2,3,4,5,6,7,8,9};
    
	public static void main(String[] args) {
		SpringApplication.run(SudokuAppApplication.class, args);
		SudokuAppApplication sudokuApp = new SudokuAppApplication();
		sudokuApp.setStartBoard(board);
		sudokuApp.getTheBoard(board, sudokuNumbers);
		Integer[][] playerBoard = board;
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose difficulty of the board: ");
		System.out.println("1 - Easy, 2 - Medium, 3 - Hard");
		System.out.println("If you want to exit type: 0");
        Integer input = scan.nextInt();
        if(input == 0) {
			System.out.println("Exit the program");
			
		}
        else {
			sudokuApp.preparePlayerBoard(playerBoard, input);
			sudokuApp.printBoard(playerBoard);
			System.out.println("The board is starting from [0,0] to [8,8].");
			System.out.println("If you want to type number in the board type like this example: 11 for 2 row and 2 column");
			System.out.println("You can still exit by typing: 0");
			while(input != 0) {
				input = scan.nextInt();
			}
			if(input == 0) {
				System.out.println("Exit the program");
			}
        }
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
	
	private void preparePlayerBoard(Integer[][] playerBoard, Integer difficulty) {
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
