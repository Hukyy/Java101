package threads;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class SudokuSquareTask implements Callable<Boolean>{
	Sudoku sudoku;
	private int squareNum;
	
	public SudokuSquareTask(Sudoku sudoku, int squareNum) {
		this.sudoku = sudoku;
		this.squareNum = squareNum;
	}

	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName("Square thread " + squareNum);
		boolean[] check = new boolean[10];
		int[][] square = sudoku.getSquare(squareNum);
		Arrays.fill(check, Boolean.FALSE);
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				int num = square[i][j];
				
				if (check[num] == true || num>9 || num<1){
					System.out.println("FALSE" + Thread.currentThread().getName());
					return false;
				}
				check[num]=true;
			}
		}
		System.out.println("TRUE" + Thread.currentThread().getName());
		return true;
	}
}
