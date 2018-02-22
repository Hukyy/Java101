
public class Main {
    public static void main(String[] args) {
        Board board = new Board();


        Game game = new Game(board);
        game.play();

        int whoWon = board.victory();

        if (whoWon == game.getAiplayer()){
            System.out.println("You lost!");
        } else if (whoWon == Board.TIE_VAL) {
            System.out.println("Draw!");
        } else {
            System.out.println("You won!");
        }
    }

}
