import java.util.ArrayList;
import java.util.List;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{
    private final Mark MARK;
    private final Mark ENEMY_MARK;

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu)
    {
        MARK = cpu;
        ENEMY_MARK = cpu == Mark.X ? Mark.O : Mark.X;
    }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move move : board.getPossibleMoves())
        {
            numExploredNodes++;
            board.play(move, MARK);

            int score = minimax(board, false);

            board.undo(move);

            if (score > bestScore)
            {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            }
            else if (score == bestScore)
            {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    private int minimax(Board board, boolean isMax)
    {
        List<Move> possibleMoves = board.getPossibleMoves();

        int score = board.evaluate(MARK);

        if (score != 0 || possibleMoves.isEmpty()) {
            return score;
        }

        numExploredNodes++;

        if (isMax)
        {
            int maxScore = Integer.MIN_VALUE;

            for (Move move : possibleMoves)
            {
                board.play(move, MARK);
                int value = minimax(board, false);
                board.undo(move);

                maxScore = Math.max(maxScore, value);
            }

            return maxScore;
        }
        else
        {
            int minScore = Integer.MAX_VALUE;

            for (Move move : possibleMoves)
            {
                board.play(move, ENEMY_MARK);
                int value = minimax(board, true);
                board.undo(move);

                minScore = Math.min(minScore, value);
            }

            return minScore;
        }
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board)
    {
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move move : board.getPossibleMoves())
        {
            numExploredNodes++;
            board.play(move, MARK);

            int score = alphaBeta(board, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

            board.undo(move);

            if (score > bestScore)
            {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            }
            else if (score == bestScore)
            {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    private int alphaBeta(Board board, boolean isMax, int alpha, int beta)
    {
        List<Move> possibleMoves = board.getPossibleMoves();

        int score = board.evaluate(MARK);

        if (score != 0 || possibleMoves.isEmpty()) {
            return score;
        }

        numExploredNodes++;

        if (isMax)
        {
            int maxScore = Integer.MIN_VALUE;

            for (Move move : possibleMoves)
            {
                board.play(move, MARK);
                int value = alphaBeta(board, false, alpha, beta);
                board.undo(move);

                maxScore = Math.max(maxScore, value);

                if (maxScore >= beta)
                {
                    break;
                }

                alpha = Math.max(alpha, maxScore);
            }

            return maxScore;
        }
        else
        {
            int minScore = Integer.MAX_VALUE;

            for (Move move : possibleMoves)
            {
                board.play(move, ENEMY_MARK);
                int value = alphaBeta(board, true, alpha, beta);
                board.undo(move);

                minScore = Math.min(minScore, value);

                if (minScore <= alpha)
                {
                    break;
                }

                beta = Math.min(beta, minScore);
            }

            return minScore;
        }
    }
}
