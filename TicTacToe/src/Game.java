import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private int aiplayer;

    public Game(Board board) {
        this.board = board;

    }

    public void play() {
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to go first? y/n");
        String first = in.nextLine();
        System.out.println("Select your shape X/O");
        String shape = in.nextLine();
        aiplayer = shape.equalsIgnoreCase("X") ? 2 : 1;
        AI ai = new AI();
        ai.init(aiplayer);
        board.print();

        if (first.equalsIgnoreCase("n")) {
            Random random = new Random();
            int randX = random.nextInt(board.getSize());
            int randY = random.nextInt(board.getSize());
            board.setVal(randX, randY, aiplayer);
            board.print();

        }

        boolean secondMove = !first.equalsIgnoreCase("n") && (board.getSize() == 4);
        while (!isGameOver()) {
            System.out.printf("select position for X-Y 1-%d separated by space\n", board.getSize());
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            board.setVal(x, y, ai.getHuman());
            board.print();

            if (secondMove){
                Random random = new Random();
                int x1,y1;
                do{
                    x1 = random.nextInt(4);
                    y1 = random.nextInt(4);
                } while (x1 == x && y1 == x);
                board.setVal(x1,y1,aiplayer);
                secondMove = false;
                board.print();
            }

            else if (!isGameOver()) {
                ai.performMove(board);
                board.print();
            }
        }
        in.close();
    }

    public boolean isGameOver() {
        return board.victory() != Board.NO_VAL;
    }

    public int getAiplayer() {
        return aiplayer;
    }
}
