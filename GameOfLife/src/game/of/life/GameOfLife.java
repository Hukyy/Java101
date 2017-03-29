package game.of.life;

public class GameOfLife {
	private char[][] matrix;
	private int size=10;
	
	public GameOfLife(int size){
		this.matrix = new char[size][size];
		this.size = size;
		for (int i=0;i<size;i++){
			for (int j = 0; j < size; j++) {
				this.matrix[i][j] = '□';
			}
		}
	}
	public boolean isEmpty(int x,int y){
		return matrix[x][y]=='□';
	}
	public void insert(int x, int y){
		this.matrix[x][y] = '■';
	}
	
	public boolean isAliveNeighbour(int x, int y){
		/*if (x < 0 || x>this.size-1 || y< 0 || y> this.size -1){
			return false;
		}*/
		x = Math.floorMod(x, size);
		y = Math.floorMod(y, size);
		return this.matrix[x][y] == '■';
	}
	public boolean aliveInNextGen(int x, int y){
		int aliveNeighbours = 0;
		for (int i=-1;i<=1; i++){
			for (int j=-1; j<= 1; j++){
				aliveNeighbours += (isAliveNeighbour(x+i, y+j))? 1: 0;
			}
		}
		boolean currentAlive = isAliveNeighbour(x, y);
		aliveNeighbours -= currentAlive ? 1: 0;
		if (currentAlive){
			if (aliveNeighbours< 2 || aliveNeighbours >3){
				return false;
			}
			return true;
		}
		if (aliveNeighbours == 3){
			return true;
		}
		return false;
	}
	
	public void nextGeneration(){
		char[][] result = new char[size][size];
		for (int i=0; i<this.size;i++){
			for (int j = 0; j < this.size; j++) {
				if (aliveInNextGen(i,j)){
					result[i][j] = '■';
				}else {
					result[i][j] = '□';
				}
			}
		}
		matrix = result;
	}
	
	public String toString(){
		String result = "";
		for (int i=0; i<this.size;i++){
			for (int j = 0; j < this.size; j++) {
				result += this.matrix[i][j] + " ";
			}
			result+= "\n";
		}
		return result;
	}
}









