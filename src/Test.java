public class Test {
    public static void main(String[] args) {
        System.out.println("Lancement des tests...\n");

        testBoard();
    }

    private static void testBoard() {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(1, 1), Mark.X);
        board.play(new Move(2, 2), Mark.X);
        board.printBoard();
        System.out.println("X : " + board.evaluate(Mark.X));
        System.out.println("O : " + board.evaluate(Mark.O));
        System.out.println();

        board = new Board();
        board.play(new Move(0, 0), Mark.O);
        board.play(new Move(1, 0), Mark.O);
        board.play(new Move(2, 0), Mark.O);
        board.printBoard();
        System.out.println("X : " + board.evaluate(Mark.X));
        System.out.println("O : " + board.evaluate(Mark.O));
        System.out.println();

        board = new Board();
        board.play(new Move(0, 0), Mark.O);
        board.play(new Move(0, 1), Mark.O);
        board.play(new Move(0, 2), Mark.O);
        board.printBoard();
        System.out.println("X : " + board.evaluate(Mark.X));
        System.out.println("O : " + board.evaluate(Mark.O));
        System.out.println();
    }
}
