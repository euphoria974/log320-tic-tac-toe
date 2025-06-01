import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        System.out.println("Lancement des tests...\n");

        // testBoard();
        testMinMaxVide();
        testMinMax();
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
}
