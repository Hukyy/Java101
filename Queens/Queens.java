import java.util.*;

class Queens{
    private int n;
    private int[] rows;
    private int[] mainDiagonals;
    private int[] secondDiagonals;
    private int[] indexOfRowPerCol;
    Random random;

    public Queens(int n){
        this.n = n;
        this.rows = new int[n];
        this.mainDiagonals = new int[2*n-1];
        this.secondDiagonals = new int[2*n-1];
        this.indexOfRowPerCol = new int[n];
        this.random = new Random();
        putFirstNQueens();
    }

    private void putFirstNQueens(){
        for (int col=0;col<n;col++){
            int row = random.nextInt(n);
            
            indexOfRowPerCol[col] = row;
            rows[row]++;
            mainDiagonals[row+col]++;
            int secondDiagonalIndex = n-1-(row-col);
            secondDiagonals[secondDiagonalIndex]++;
        }
    }

public void minConflicts(){
    minConflicts(20000);
}

private void minConflicts(int tries) {
        int counter = 0;
        int checkedQueens;
        while (counter < tries) {
            counter++;
            checkedQueens = 0;
            if (counter % 50 == 0) {
                int col = random.nextInt(n);
                int row = random.nextInt(n);
                move(col, row);
            } else {
                int col = random.nextInt(n);
                int row = indexOfRowPerCol[col];
                while (rows[row]+ mainDiagonals[getIndexForMainDiagonal(row,col)] + secondDiagonals[getIndexForSecondDiagonal(row,col)] == 3 && checkedQueens < n) {
                    col++;
                    checkedQueens++;
                    if (col==n){
                        col=0;
                    }
                    row = indexOfRowPerCol[col];
                }
                if (checkedQueens >= n) {
                    return;
                }
                int toRow = getMinConflictIndexPerColumn(col);
                // System.out.println(toString());
                move(col, toRow);
            }
        }
    }

    private int getMinConflictIndexPerColumn(int col){
        List<Integer> indices = new ArrayList<>();
        int originalRow = indexOfRowPerCol[col];
        int mainDiagonalIndex = getIndexForMainDiagonal(originalRow,col);
        int secondDiagonalIndex = getIndexForSecondDiagonal(originalRow,col);
        int queensOnMainDiagonal = mainDiagonals[mainDiagonalIndex];
        int queensOnSecondDiagonal = secondDiagonals[secondDiagonalIndex];
        int min = rows[0]+ queensOnMainDiagonal + queensOnSecondDiagonal;
        if (originalRow == 0) {
            min-=3;
        }
        for (int row = 0; row < n; row++) {
            mainDiagonalIndex = getIndexForMainDiagonal(row,col);
            secondDiagonalIndex = getIndexForSecondDiagonal(row,col);
            queensOnMainDiagonal = mainDiagonals[mainDiagonalIndex];
            queensOnSecondDiagonal = secondDiagonals[secondDiagonalIndex];
            int currentCountQueens = rows[row] + queensOnMainDiagonal + queensOnSecondDiagonal;
            if (row == originalRow){
                currentCountQueens-=3;
            }
            if (currentCountQueens < min) {
                min = currentCountQueens;
                indices.clear();
            }
            if (currentCountQueens == min) {
                indices.add(row);
            }
        }
        int rand = random.nextInt(indices.size());
        int indexToReturn = indices.get(rand);
        return indexToReturn;
    }

    public void move(int col, int row){
        int rowOfCol = indexOfRowPerCol[col];
        rows[rowOfCol]--;
        rows[row]++;
        int mainDiagonalIndexAfter = getIndexForMainDiagonal(row,col);
        int secondDiagonalIndexAfter = getIndexForSecondDiagonal(row,col);
        int mainDiagonalIndexBefore = getIndexForMainDiagonal(rowOfCol,col);
        int secondDiagonalIndexBefore = getIndexForSecondDiagonal(rowOfCol,col);

        mainDiagonals[mainDiagonalIndexBefore]--;
        mainDiagonals[mainDiagonalIndexAfter]++;
        secondDiagonals[secondDiagonalIndexBefore]--;
        secondDiagonals[secondDiagonalIndexAfter]++;

        indexOfRowPerCol[col] = row;

    }
    private int getIndexForMainDiagonal(int row, int col){
        return row+col;
    }
    private int getIndexForSecondDiagonal(int row,int col){
        return n-1-(row-col);
    }
    @Override
    public String toString(){
        // return "I'm done!";
        char[][] display = new char[n][n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Arrays.fill(display[i], '_');
        }
        for (int col = 0; col < n; col++) {
            int row = indexOfRowPerCol[col];
            display[row][col] = 'Q';
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                sb.append(display[i][j]);
                sb.append(' ');
            }
            sb.append(display[i][n - 1]);
            sb.append('\n');
        }
        // sb.append("\n main diagonal conflicts : \n");
        // for (int i=0;i<2*n-1;i++){
        //     sb.append("diagonal " + i + " " + mainDiagonals[i] + "\n");

        // }
        // sb.append("\n\n");
        // for (int i=0;i<2*n-1;i++){
        //     sb.append("second diagonal " + i + " " + secondDiagonals[i] + "\n");
        // }
        return sb.toString();
    }
}