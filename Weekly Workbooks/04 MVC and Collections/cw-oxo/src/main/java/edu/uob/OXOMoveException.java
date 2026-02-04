package edu.uob;

import java.io.Serial;

public class OXOMoveException extends Exception {
    @Serial private static final long serialVersionUID = 1;

    public OXOMoveException(String message) {
        super(message);
    }

    public enum RowOrColumn { ROW, COLUMN }

    public static class OutsideCellRangeException extends OXOMoveException {
        @Serial private static final long serialVersionUID = 1;

        public OutsideCellRangeException(RowOrColumn dimension, int pos) {
            super("Position " + pos + " is out of range for " + dimension.name());
        }
    }

    public static class InvalidIdentifierLengthException extends OXOMoveException {
        @Serial private static final long serialVersionUID = 1;

        public InvalidIdentifierLengthException(int length) {
            super("Identifier of size " + length + " is invalid");
        }
    }

    public static class InvalidIdentifierCharacterException extends OXOMoveException {
        @Serial private static final long serialVersionUID = 1;

        public InvalidIdentifierCharacterException(RowOrColumn problemDimension, char character) {
            super(character + " is not a valid character for a " + problemDimension.name());
        }
    }

    public static class CellAlreadyTakenException extends OXOMoveException {
        @Serial private static final long serialVersionUID = 1;

        public CellAlreadyTakenException(int row, int column) {
            super("Cell [" + row + "," + column + "] has already been claimed");
        }
    }
}
