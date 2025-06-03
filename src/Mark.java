enum Mark
{
    X("X"),
    O("O"),
    EMPTY(" ");

    private final String stringValue;

    Mark(String stringValue)
    {
        this.stringValue = stringValue;
    }

    @Override
    public String toString()
    {
        return this.stringValue;
    }
}

