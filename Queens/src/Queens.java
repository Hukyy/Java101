import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Queens {
    private int n;
    private int[][] board;
    private int[] indexPerCol;
    private Random random;

    public Queens(int n) {
        this.n = n;
        board = new int[n][n];
        indexPerCol = new int[n];
        random = new Random();
        initialize();
    }

    public void minConflicts() {
        minConflicts(20000);
    }

    private void initialize() {

        for (int col = 0; col < n; col++) {
            int row = random.nextInt(n);
            indexPerCol[col] = row;
            update(row, col, 1);
        }
    }

    private void update(int row, int col, int val) {
        for (int i = col - 1; i >= 0; i--) {
            board[row][i] += val;
        }
        for (int i = col + 1; i < n; i++) {
            board[row][i] += val;
        }
        diagonally(row, col, val);
    }

    private void diagonally(int row, int col, int val) {
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) { //updates the conflicts count on the main diagonal over the position of the queen
            board[i][j] += val;
        }
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) { //updates the conflicts count on the main diagonal under the position of the queen
            board[i][j] += val;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) { //updates the conflicts count on the second diagonal over the position of the queen
            board[i][j] += val;
        }
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) { //updates the conflicts count on the second diagonal under the position of the queen
            board[i][j] += val;
        }
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
                put(col, row);
            } else {
                int col = random.nextInt(n);
                while (board[indexPerCol[col]][col] == 0 && checkedQueens < n) {
                    col++;
                    checkedQueens++;
                    if (col==n){
                        col=0;
                    }
                }
                if (checkedQueens >= n) {
                    return;
                }
                int row = getMinConflictIndexPerColumn(col);
                put(col, row);
            }
        }
    }

    private int getMinConflictIndexPerColumn(int col) {
        List<Integer> indices = new ArrayList<>();
        int min = board[0][col];
        for (int row = 0; row < n; row++) {
            if (board[row][col] < min) {
                min = board[row][col];
                indices.clear();
            }
            if (board[row][col] == min) {
                indices.add(row);
            }
        }
        int rand = random.nextInt(indices.size());
        int indexToReturn = indices.get(rand);
        return indexToReturn;
    }

    public void put(int col, int toRow) {
        int fromRow = indexPerCol[col];
        update(fromRow, col, -1);
        indexPerCol[col] = toRow;
        update(toRow, col, 1);
    }

    @Override
    public String toString() {
        char[][] display = new char[n][n];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Arrays.fill(display[i], '_');
        }
        for (int col = 0; col < n; col++) {
            int row = indexPerCol[col];
            display[row][col] = '*';
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                sb.append(display[i][j]);
                sb.append(' ');
            }
            sb.append(display[i][n - 1]);
            sb.append('\n');
        }
        return sb.toString();
    }
}
