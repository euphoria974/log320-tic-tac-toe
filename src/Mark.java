enum Mark {
    X, O, EMPTY;

    @Override
    public String toString() {
        switch (this) {
        case X:
            return "X";
        case O:
            return "O";
        default:
            return " ";
        }
    }
}
