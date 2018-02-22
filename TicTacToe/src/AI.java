import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AI {
    public static final int TIE_VAL = -1;
    public static final int NO_VAL = 0;
    public static final int X_VAL = 1;
    public static final int O_VAL = 2;

    private int human;
    private int aiPlayer;

    public void init(int aiPlayer) {
        this.aiPlayer = aiPlayer;
        if (aiPlayer == Board.X_VAL) {
            human = Board.O_VAL;
        } else {
            human = Board.X_VAL;
        }
    }

    public void performMove(Board board) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        AIMove bestMove = miniMaxAlphaBeta(board, aiPlayer, alpha, beta, 1);
        board.setVal(bestMove.x, bestMove.y, aiPlayer);
    }

    public int getAiPlayer() {
        return aiPlayer;
    }

    public int getHuman() {
        return human;
    }

    private AIMove miniMaxAlphaBeta(Board board, int player, int alpha, int beta, int depth) {
        int value = board.victory();

        if (value == aiPlayer) {
            AIMove temp = new AIMove(1000 / depth);
            return temp;
        } else if (value == human) {
            AIMove temp = new AIMove(-1000 * depth);
            return temp;
        } else if (value == TIE_VAL) {
            AIMove temp = new AIMove(0);
            return temp;
        }

        List<AIMove> moves = new ArrayList<>();

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {

                if (board.getVal(x, y) == NO_VAL) {
                    AIMove move = new AIMove(x, y);
                    board.setVal(x, y, player);

                    if (player == aiPlayer) {
                        move.score = miniMaxAlphaBeta(board, human, alpha, beta, depth + 1).score;
                        if (move.score > alpha) {
                            alpha = move.score;
                        }
                    } else {
                        move.score = miniMaxAlphaBeta(board, aiPlayer, alpha, beta, depth + 1).score;
                        if (move.score < beta) {
                            beta = move.score;
                        }
                    }

//                    if (depth == 1) {
//                        System.out.printf("Score for x:%d   y:%d   is %d\n", x + 1, y + 1, move.score);
//                    }

                    moves.add(move);
                    board.setVal(x, y, Board.NO_VAL);
                    if (beta <= alpha) {
                        break;
                    }

                }
            }
        }
        Collections.shuffle(moves);
        int bestMove = 0;
        if (player == aiPlayer) {
            int bestScore = -1000000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score > bestScore) {
                    bestMove = i;
                    bestScore = moves.get(bestMove).score;
                }
            }
        } else {
            int bestScore = 1000000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score < bestScore) {
                    bestMove = i;
                    bestScore = moves.get(bestMove).score;
                }
            }
        }
        return moves.get(bestMove);
    }

}
