package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		int[][] arr = {{4,3,5,2,6,9,7,8,1},
						{6,8,2,5,7,1,4,9,3},
						{1,9,7,8,3,4,5,6,2},
						{8,2,6,1,9,5,3,4,7},
						{3,7,4,6,8,2,9,1,5},
						{9,5,1,7,4,3,6,2,8},
						{5,1,9,3,2,6,8,7,4},
						{2,4,8,9,5,7,1,3,6},
						{7,6,3,4,1,8,2,5,9}};
		Sudoku sudoku = new Sudoku(arr);
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i=0;i<9;i++){
			SudokuRowTask rowTask = new SudokuRowTask(sudoku,i);
			SudokuColumnTask colTask = new SudokuColumnTask(sudoku, i);
			SudokuSquareTask squareTask = new SudokuSquareTask(sudoku, i);
			service.submit(rowTask);
			service.submit(colTask);
			service.submit(squareTask);
		}
		//service.
		service.shutdown();

	}

}
