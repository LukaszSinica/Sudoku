package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.lukaszsinica.sudoku.Sudoku;

public class SudokuClassTest {

	private Integer[] get3x3Square(Integer[][] board, int startRow, int startCol) {
		Integer[] square = new Integer[9];
		int index = 0;
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				square[index++] = board[i][j];
			}
		}
		return square;
	}

	private Integer[] getColumn(Integer[][] board, int col) {
		Integer[] column = new Integer[9];
		for (int i = 0; i < 9; i++) {
			column[i] = board[i][col];
		}
		return column;
	}

	private boolean hasDuplicates(Integer[] arr) {
		Set<Integer> set = new HashSet<>(Arrays.asList(arr));
		return set.size() != arr.length;
	}

	@Test
	public void testBoardGeneration() {
		Sudoku.setStartBoard(Sudoku.board);
		boolean boardGenerated = Sudoku.getTheBoard(Sudoku.board, Sudoku.sudokuNumbers);
		assertTrue(boardGenerated);

		for (int i = 0; i < 9; i++) {
			assertFalse(hasDuplicates(Sudoku.board[i]));
			assertFalse(hasDuplicates(getColumn(Sudoku.board, i)));
		}
		for (int row = 0; row < 9; row += 3) {
			for (int col = 0; col < 9; col += 3) {
				assertFalse(hasDuplicates(get3x3Square(Sudoku.board, row, col)));
			}
		}
	}

	@Test
	public void testBoardReset() {
		Sudoku.setStartBoard(Sudoku.board);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertEquals(0, Sudoku.board[i][j].intValue());
			}
		}
	}

	@Test
	public void testCheckAnswer() {
		Sudoku.setStartBoard(Sudoku.board);
		Sudoku.getTheBoard(Sudoku.board, Sudoku.sudokuNumbers);

		Integer[][] playerBoard = new Integer[9][9];
		Sudoku.setStartBoard(playerBoard);

		boolean correctAnswer = Sudoku.checkAnswer(0, 0, Sudoku.board[0][0], Sudoku.board, playerBoard);
		assertTrue(correctAnswer);

		boolean incorrectAnswer = Sudoku.checkAnswer(0, 0, Sudoku.board[0][0] + 1, Sudoku.board, playerBoard);
		assertFalse(incorrectAnswer);
	}

	@Test
	public void testPlayerBoardPreparation() {
		Sudoku.setStartBoard(Sudoku.board);
		Sudoku.getTheBoard(Sudoku.board, Sudoku.sudokuNumbers);

		Integer[][] playerBoard = new Integer[9][9];
		for (int i = 0; i < 9; i++) {
			playerBoard[i] = Arrays.copyOf(Sudoku.board[i], 9);
		}

		Sudoku.preparePlayerBoard(playerBoard, 2);
		int emptyFields = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (playerBoard[i][j] == 0) {
					emptyFields++;
				}
			}
		}
		assertEquals(Sudoku.DEFAULT_NUMBER_OF_ERRORS * 2, emptyFields);
	}
}
