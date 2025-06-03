import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private final Mark[][] BOARD = new Mark[3][3];

    // Ne pas changer la signature de cette méthode
    public Board()
    {
        clear();
    }

    public ArrayList<Move> getPossibleMoves()
    {
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (BOARD[i][j] == Mark.EMPTY)
                {
                    possibleMoves.add(new Move(i, j));
                }
            }
        }

        return possibleMoves;
    }

    public void clear()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                BOARD[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move move, Mark mark)
    {
        if (mark == Mark.EMPTY || BOARD[move.getRow()][move.getCol()] != Mark.EMPTY)
        {
            throw new IllegalArgumentException("Invalid move");
        }

        BOARD[move.getRow()][move.getCol()] = mark;
    }

    public void undo(Move move)
    {
        BOARD[move.getRow()][move.getCol()] = Mark.EMPTY;
    }

    public void print()
    {
        System.out.println(BOARD[0][0] + "|" + BOARD[0][1] + "|" + BOARD[0][2]);
        System.out.println("-+-+-");
        System.out.println(BOARD[1][0] + "|" + BOARD[1][1] + "|" + BOARD[1][2]);
        System.out.println("-+-+-");
        System.out.println(BOARD[2][0] + "|" + BOARD[2][1] + "|" + BOARD[2][2]);
        System.out.println("~~~~~");
    }

    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark)
    {
        if (checkVictory(mark))
        {
            return 100;
        }

        Mark enemyMark = mark == Mark.X ? Mark.O : Mark.X;
        if (checkVictory(enemyMark))
        {
            return -100;
        }

        return 0;
    }

    private boolean checkVictory(Mark mark)
    {
        for (int i = 0; i < 3; i++)
        {
            if (BOARD[i][0] == mark && BOARD[i][1] == mark && BOARD[i][2] == mark) return true;
        }

        for (int i = 0; i < 3; i++)
        {
            if (BOARD[0][i] == mark && BOARD[1][i] == mark && BOARD[2][i] == mark) return true;
        }

        if (BOARD[0][0] == mark && BOARD[1][1] == mark && BOARD[2][2] == mark) return true;
        if (BOARD[0][2] == mark && BOARD[1][1] == mark && BOARD[2][0] == mark) return true;

        return false;
    }
}
