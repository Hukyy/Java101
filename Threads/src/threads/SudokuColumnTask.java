package threads;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class SudokuColumnTask implements Callable<Boolean>{
	Sudoku sudoku;
	private int columnNum;
	
	public SudokuColumnTask(Sudoku sudoku, int columnNum) {
		this.sudoku = sudoku;
		this.columnNum = columnNum;
	}

	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName("Column thread " + columnNum);
		boolean[] check = new boolean[10];
		int[] column = sudoku.getColumn(columnNum);
		Arrays.fill(check, Boolean.FALSE);
		for (int i=0;i<9;i++){
			int num = column[i];
			
			if ( num>9 || num<1||check[num] == true){
				System.out.println("FALSE" + Thread.currentThread().getName());
				return false;
			}
			check[num]=true;
			
		}
		System.out.println("TRUE" + Thread.currentThread().getName());
		return true;
	}
}
