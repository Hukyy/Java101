

public class Board {
    public static final int TIE_VAL = -1;
    public static final int NO_VAL = 0;
    public static final int X_VAL = 1;
    public static final int O_VAL = 2;


    private int[][] board;
    private int size;


    public Board() {
        this(3);
    }

    public Board(int size) {
        this.size = size;
        board = new int[size][size];

    }

    public int getSize() {
        return size;
    }

    public void print() {
        char[][] pritnableBoard = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char val = ' ';
                if (board[i][j] == X_VAL) {
                    val = 'X';
                }
                if (board[i][j] == O_VAL) {
                    val = 'O';
                }
                pritnableBoard[i][j] = val;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (size == 3) {
            stringBuilder.append("+---+---+---+\n");
            stringBuilder.append("| " + pritnableBoard[0][0] + " | " + pritnableBoard[0][1] + " | " + pritnableBoard[0][2] + " |\n");
            stringBuilder.append("+---+---+---+\n");
            stringBuilder.append("| " + pritnableBoard[1][0] + " | " + pritnableBoard[1][1] + " | " + pritnableBoard[1][2] + " |\n");
            stringBuilder.append("+---+---+---+\n");
            stringBuilder.append("| " + pritnableBoard[2][0] + " | " + pritnableBoard[2][1] + " | " + pritnableBoard[2][2] + " |\n");
            stringBuilder.append("+---+---+---+\n");
        } else if (size == 4) {
            stringBuilder.append("+---+---+---+---+\n");
            stringBuilder.append("| " + pritnableBoard[0][0] + " | " + pritnableBoard[0][1] + " | " + pritnableBoard[0][2] + " | " + pritnableBoard[0][3] + " |\n");
            stringBuilder.append("+---+---+---+---+\n");
            stringBuilder.append("| " + pritnableBoard[1][0] + " | " + pritnableBoard[1][1] + " | " + pritnableBoard[1][2] + " | " + pritnableBoard[1][3] + " |\n");
            stringBuilder.append("+---+---+---+---+\n");
            stringBuilder.append("| " + pritnableBoard[2][0] + " | " + pritnableBoard[2][1] + " | " + pritnableBoard[2][2] + " | " + pritnableBoard[2][3] + " |\n");
            stringBuilder.append("+---+---+---+---+\n");
            stringBuilder.append("| " + pritnableBoard[3][0] + " | " + pritnableBoard[3][1] + " | " + pritnableBoard[3][2] + " | " + pritnableBoard[3][3] + " |\n");
            stringBuilder.append("+---+---+---+---+\n");
        }
        System.out.println(stringBuilder.toString());
    }


    public void setVal(int x, int y, int val) {
        board[x][y] = val;
    }

    public int getVal(int x, int y) {
        return board[x][y];
    }

    public int placedTiles(){
        int result = 0;
        for (int x=0;x<size;x++){
            for (int y=0;y<size;y++){
                if (board[x][y] == NO_VAL){
                    result++;
                }
            }
        }
        return result;
    }


    public int victory() {

        for (int x = 0; x < size; x++) { /// check rows
            boolean victory = true;
            int val = board[x][0];
            if (val == NO_VAL) {
                continue;
            }
            for (int y = 0; y < size; y++) {
                if (board[x][y] != val) {
                    victory = false;
                    break;
                }
            }
            if (victory) {
                return val;
            }
        }

        for (int y = 0; y < size; y++) { /// check columns
            int val = board[0][y];
            boolean victory = true;
            if (val == NO_VAL) {
                continue;
            }
            for (int x = 0; x < size; x++) {
                if (board[x][y] != val) {
                    victory = false;
                    break;
                }
            }
            if (victory) {
                return val;
            }
        }

        int val = board[0][0];
        boolean victory = true;
        if (val != NO_VAL) {
            for (int i = 0; i < size; i++) { // check main diagonal
                if (board[i][i] != val) {
                    victory = false;
                    break;
                }
            }
        }
        if (victory) {
            return val;
        }

        val = board[0][size - 1];
        victory = true;
        if (val != NO_VAL) {
            for (int i = 0; i < size; i++) { // check second diagonal
                if (board[i][size - i - 1] != val) {
                    victory = false;
                    break;
                }
            }
        }
        if (victory) {
            return val;
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (board[x][y] == NO_VAL) {
                    return NO_VAL;
                }
            }
        }

        return TIE_VAL;

    }

}
