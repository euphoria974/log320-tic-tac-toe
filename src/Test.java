import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("Lancement des tests...\n");

        // testBoard();
        // testMinMaxVide();
        // testAlphaBetaVide();
        // testMinMax();
        // testAlphaBeta();
        testMinMaxRandom();
        testAlphaBetaRandom();
    }

    private static void testBoard() {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(2, 2), Mark.X);
        board.print();
        System.out.println("X : " + board.evaluate(Mark.X));
        System.out.println("O : " + board.evaluate(Mark.O));
        System.out.println();

        board = new Board();
        board.play(new Move(0, 2), Mark.X);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(2, 0), Mark.X);
        board.print();
        System.out.println("X : " + board.evaluate(Mark.X));
        System.out.println("O : " + board.evaluate(Mark.O));
        System.out.println();

        board = new Board();
        board.play(new Move(0, 0), Mark.O);
        board.play(new Move(1, 0), Mark.O);
        board.play(new Move(2, 0), Mark.O);
        board.print();
        System.out.println("X : " + board.evaluate(Mark.X));
        System.out.println("O : " + board.evaluate(Mark.O));
        System.out.println();

        board = new Board();
        board.play(new Move(0, 0), Mark.O);
        board.play(new Move(0, 1), Mark.O);
        board.play(new Move(0, 2), Mark.O);
        board.print();
        System.out.println("X : " + board.evaluate(Mark.X));
        System.out.println("O : " + board.evaluate(Mark.O));
        System.out.println();
    }

    private static void testMinMaxVide() {
        System.out.println("########################################################################################");
        System.out.println("#                                 MINMAX - EMPTY BOARD                                 #");
        System.out.println("########################################################################################");

        Board board = new Board();

        System.out.println("Starting board state : ");
        board.print();
        System.out.println();

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        System.out.println("Maximizing for: X");

        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        for (Move move : moves) {
            System.out.print(move + " ");
        }
        System.out.println();

        System.out.println(cpuPlayer.getNumOfExploredNodes() + " nodes explored.\n");
    }

    private static void testAlphaBetaVide() {
        System.out.println("########################################################################################");
        System.out.println("#                               ALPHA-BETA - EMPTY BOARD                               #");
        System.out.println("########################################################################################");

        Board board = new Board();

        System.out.println("Starting board state : ");
        board.print();
        System.out.println();

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        System.out.println("Maximizing for: X");

        ArrayList<Move> moves = cpuPlayer.getNextMoveAB(board);
        for (Move move : moves) {
            System.out.print(move + " ");
        }
        System.out.println();

        System.out.println(cpuPlayer.getNumOfExploredNodes() + " nodes explored.\n");
    }

    private static void testMinMax() {
        System.out.println("########################################################################################");
        System.out.println("#                                        MINMAX                                        #");
        System.out.println("########################################################################################");

        Board board = new Board();
        board.play(new Move(1, 1), Mark.O);

        System.out.println("Starting board state : ");
        board.print();
        System.out.println();

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        System.out.println("Maximizing for: X");

        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        for (Move move : moves) {
            System.out.print(move + " ");
        }
        System.out.println();

        System.out.println(cpuPlayer.getNumOfExploredNodes() + " nodes explored.\n");
    }

    private static void testAlphaBeta() {
        System.out.println("########################################################################################");
        System.out.println("#                                      ALPHA-BETA                                      #");
        System.out.println("########################################################################################");

        Board board = new Board();
        board.play(new Move(1, 1), Mark.O);

        System.out.println("Starting board state : ");
        board.print();
        System.out.println();

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        System.out.println("Maximizing for: X");

        ArrayList<Move> moves = cpuPlayer.getNextMoveAB(board);
        for (Move move : moves) {
            System.out.print(move + " ");
        }
        System.out.println();

        System.out.println(cpuPlayer.getNumOfExploredNodes() + " nodes explored.\n");
    }

    public static void testMinMaxRandom() {
        System.out.println("########################################################################################");
        System.out.println("#                                  MINMAX - 300 GAMES                                  #");
        System.out.println("########################################################################################");

        CPUPlayer cpuX = new CPUPlayer(Mark.X);
        CPUPlayer cpuO = new CPUPlayer(Mark.O);

        int xWins = 0;
        int oWins = 0;
        int draws = 0;
        int totalGames = 300;

        for (int game = 1; game <= totalGames; game++) {
            Board board = new Board();
            boolean xTurn = game % 2 == 0;

            ArrayList<Integer> numOfExploredNodes = new ArrayList<>();

            while (true) {
                int evalX = board.evaluate(Mark.X);

                if (evalX == 100) {
                    xWins++;
                    break;
                }

                if (evalX == -100) {
                    oWins++;
                    break;
                }

                if (board.getPossibleMoves().isEmpty()) {
                    draws++;
                    break;
                }

                if (xTurn) {
                    List<Move> moves = cpuX.getNextMoveMinMax(board);
                    numOfExploredNodes.add(cpuX.getNumOfExploredNodes());
                    board.play(moves.get(0), Mark.X);
                } else {
                    List<Move> moves = cpuO.getNextMoveMinMax(board);
                    numOfExploredNodes.add(cpuO.getNumOfExploredNodes());
                    board.play(moves.get(0), Mark.O);
                }

                xTurn = !xTurn;
            }

            // print numOfExploredNodes only for the first 5 games
            if (game <= 5) {
                System.out.print("Nodes explored - ");
                for (Integer n : numOfExploredNodes) {
                    System.out.print(n + " - ");
                }
                System.out.println();
            }
        }

        System.out.println("CPU X gagne : " + xWins);
        System.out.println("CPU O gagne : " + oWins);
        System.out.println("Matchs nuls : " + draws);
        System.out.println();
    }

    public static void testAlphaBetaRandom() {
        System.out.println("########################################################################################");
        System.out.println("#                                ALPHA-BETA - 300 GAMES                                #");
        System.out.println("########################################################################################");

        CPUPlayer cpuX = new CPUPlayer(Mark.X);
        CPUPlayer cpuO = new CPUPlayer(Mark.O);

        int xWins = 0;
        int oWins = 0;
        int draws = 0;
        int totalGames = 300;

        for (int game = 1; game <= totalGames; game++) {
            Board board = new Board();
            boolean xTurn = game % 2 == 0;

            ArrayList<Integer> numOfExploredNodes = new ArrayList<>();

            while (true) {
                int evalX = board.evaluate(Mark.X);

                if (evalX == 100) {
                    xWins++;
                    break;
                }

                if (evalX == -100) {
                    oWins++;
                    break;
                }

                if (board.getPossibleMoves().isEmpty()) {
                    draws++;
                    break;
                }

                if (xTurn) {
                    List<Move> moves = cpuX.getNextMoveAB(board);
                    numOfExploredNodes.add(cpuX.getNumOfExploredNodes());
                    board.play(moves.get(0), Mark.X);
                } else {
                    List<Move> moves = cpuO.getNextMoveAB(board);
                    numOfExploredNodes.add(cpuO.getNumOfExploredNodes());
                    board.play(moves.get(0), Mark.O);
                }

                xTurn = !xTurn;
            }

            // print numOfExploredNodes only for the first 5 games
            if (game <= 5) {
                System.out.print("Nodes explored - ");
                for (Integer n : numOfExploredNodes) {
                    System.out.print(n + " - ");
                }
                System.out.println();
            }
        }

        System.out.println("CPU X gagne : " + xWins);
        System.out.println("CPU O gagne : " + oWins);
        System.out.println("Matchs nuls : " + draws);
        System.out.println();
    }
}
