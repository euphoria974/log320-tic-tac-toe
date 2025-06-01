import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer {

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    private Mark maxPlayer;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu) {
        this.maxPlayer = cpu;
    }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;

        ArrayList<Move> moves = new ArrayList<>();
        int maxScore = Integer.MIN_VALUE;
        Mark minPlayer = maxPlayer == Mark.X ? Mark.O : Mark.X;

        for (Move move : board.getPossibleMoves()) {
            board.play(move, maxPlayer);
            int score = minMax(board, minPlayer); // calculate score for each move
            if (score > maxScore) {
                maxScore = score;
                moves.clear();
                moves.add(move);
            } else if (score == maxScore) {
                moves.add(move);
            }
            board.play(move, Mark.EMPTY); // cancel move (backtracking)
        }

        return moves;
    }

    private int minMax(Board board, Mark player) {
        int evaluation = board.evaluate(maxPlayer);
        if (evaluation != 0 || board.isFull()) { // if the game has a winner or the board is full
            return evaluation;
        }

        ++numExploredNodes;

        int score;
        Mark nextPlayer = player == Mark.X ? Mark.O : Mark.X;
        if (player == maxPlayer) { // maximizing
            score = Integer.MIN_VALUE;
            for (Move move : board.getPossibleMoves()) {
                board.play(move, player);
                int s = minMax(board, nextPlayer); //  recursively calculate best score from this position
                score = Math.max(score, s);
                board.play(move, Mark.EMPTY); // cancel move (backtracking)
            }
        } else { // minimizing
            score = Integer.MAX_VALUE;
            for (Move move : board.getPossibleMoves()) {
                board.play(move, player);
                int s = minMax(board, nextPlayer); //  recursively calculate best score from this position
                score = Math.min(score, s);
                board.play(move, Mark.EMPTY); // cancel move (backtracking)
            }
        }

        return score;
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;

        ArrayList<Move> moves = new ArrayList<>();
        int maxScore = Integer.MIN_VALUE;
        Mark minPlayer = maxPlayer == Mark.X ? Mark.O : Mark.X;

        for (Move move : board.getPossibleMoves()) {
            board.play(move, maxPlayer);
            // calculate score foreach move
            int score = minMaxAlphaBeta(board, minPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);
            // System.out.println("Score for move " + move + " : " + score);
            if (score > maxScore) {
                maxScore = score;
                moves.clear();
                moves.add(move);
            } else if (score == maxScore) {
                moves.add(move);
            }
            board.play(move, Mark.EMPTY); // cancel move (backtracking)
        }

        return moves;
    }

    private int minMaxAlphaBeta(Board board, Mark player, int alpha, int beta) {
        int evaluation = board.evaluate(maxPlayer);
        if (evaluation != 0 || board.isFull()) { // if the game has a winner or the board is full
            return evaluation;
        }

        ++numExploredNodes;

        int score;
        Mark nextPlayer = player == Mark.X ? Mark.O : Mark.X;
        if (player == maxPlayer) { // maximizing
            score = Integer.MIN_VALUE;
            for (Move move : board.getPossibleMoves()) {
                board.play(move, player);
                // recursively calculate best score from this position
                int s = minMaxAlphaBeta(board, nextPlayer, alpha, beta);
                score = Math.max(score, s);
                board.play(move, Mark.EMPTY); // cancel move (backtracking)
                if (score >= beta) {
                    break;
                }
                alpha = Math.max(score, alpha);
            }
        } else { // minimizing
            score = Integer.MAX_VALUE;
            for (Move move : board.getPossibleMoves()) {
                board.play(move, player);
                // recursively calculate best score from this position
                int s = minMaxAlphaBeta(board, nextPlayer, alpha, beta);
                score = Math.min(score, s);
                board.play(move, Mark.EMPTY); // cancel move (backtracking)
                if (score <= alpha) { // alpha-beta pruning
                    break;
                }
                beta = Math.min(score, beta);
            }
        }

        return score;
    }
}
