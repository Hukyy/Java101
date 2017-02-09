package threads;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class SudokuRowTask implements Callable<Boolean>{
	Sudoku sudoku;
	private int rowNum;
	
	public SudokuRowTask(Sudoku sudoku, int rowNum) {
		this.sudoku = sudoku;
		this.rowNum = rowNum;
	}

	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName("Row thread " + rowNum);
		boolean[] check = new boolean[10];
		int[] row = sudoku.getRow(rowNum);
		Arrays.fill(check, Boolean.FALSE);
		for (int i=0;i<9;i++){
			int num = row[i];
			
			if (num>9 || num<1 || check[num] == true ){
				System.out.println("FALSE" + Thread.currentThread().getName());
				return false;
			}
			check[num]=true;
			
		}
		System.out.println("TRUE" + Thread.currentThread().getName());
		return true;
	}
	
	
	
}
