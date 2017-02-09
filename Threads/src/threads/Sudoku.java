package threads;

public class Sudoku {
	private int[][] sudoku;
	
	public Sudoku(int[][] sudoku) {
		this.sudoku=sudoku;
	}
	
	public int[] getRow(int i){
		return sudoku[i];
	}
	
	public int[] getColumn(int i){
		int[] column = new int[9];
		for (int j=0;j<9;j++){
			column[j]=sudoku[i][j];
		}
		return column;
	}
	
	public int[][] getSquare(int i){
		int[][] square= new int[3][3];
		
		int startRow = (i/3) * 3;
		int startColumn= (i % 3) * 3;
		
		for (int j = startRow, row = 0;j<startRow+3;j++,row++){
			for (int k= startColumn, column = 0; k<startColumn+3;k++,column++){
				square[row][column]=sudoku[j][k];
			}
		}
		
		return square;
	}
}
