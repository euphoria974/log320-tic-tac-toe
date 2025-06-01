import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait être le cas)
class Board {
    public final int SIZE = 3;
    // board[row][col]
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        // initialise le board avec des cases vides
        board = new Mark[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau, à la position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark) {
        board[m.getRow()][m.getCol()] = mark;
    }

    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (board[i][j] == Mark.EMPTY || board[i][j] == null)
                    moves.add(new Move(i, j));
            }
        }
        return moves;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (board[i][j] == Mark.EMPTY || board[i][j] == null)
                    return false;
            }
        }
        return true;
    }

    // retourne 100 pour une victoire
    // -100 pour une défaite
    // 0 pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark) {
        // TODO trouver un moyen plus efficace d'évaluer

        // vérifie les lignes
        for (int i = 0; i < SIZE; ++i) {
            Mark curMark = board[i][0];
            if (curMark == Mark.EMPTY)
                continue;

            boolean lineFound = true;
            for (int j = 1; j < SIZE; ++j) {
                if (board[i][j] != curMark) {
                    lineFound = false;
                    break;
                }
            }
            if (lineFound)
                return curMark == mark ? 100 : -100;
        }

        // vérifie les colonnes
        for (int j = 0; j < SIZE; ++j) {
            Mark curMark = board[0][j];
            if (curMark == Mark.EMPTY)
                continue;

            boolean lineFound = true;
            for (int i = 1; i < SIZE; ++i) {
                if (board[i][j] != curMark) {
                    lineFound = false;
                    break;
                }
            }
            if (lineFound)
                return curMark == mark ? 100 : -100;
        }

        // vérifie les diagonales
        Mark curMark = board[0][0];
        if (curMark != Mark.EMPTY) {
            boolean lineFound = true;
            for (int i = 1; i < SIZE; ++i) {
                if (board[i][i] != curMark) {
                    lineFound = false;
                    break;
                }
            }
            if (lineFound)
                return curMark == mark ? 100 : -100;
        }

        curMark = board[0][SIZE - 1];
        if (curMark != Mark.EMPTY) {
            boolean lineFound = true;
            for (int i = 1; i < SIZE; ++i) {
                if (board[i][SIZE - i - 1] != curMark) {
                    lineFound = false;
                    break;
                }
            }
            if (lineFound)
                return curMark == mark ? 100 : -100;
        }

        // si aucune ligne n'a été trouvée
        return 0; // partie nulle
    }

    public void print() {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                System.out.print(String.format(" %s ", board[i][j]));
                if (j < SIZE - 1)
                    System.out.print("|");
            }
            if (i < SIZE - 1)
                System.out.println("\n-----------");
        }
        System.out.print("\n");
    }
}
