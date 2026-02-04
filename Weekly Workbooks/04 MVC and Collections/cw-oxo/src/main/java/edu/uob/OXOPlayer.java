package edu.uob;

import java.io.Serial;
import java.io.Serializable;

public class OXOPlayer implements Serializable {
    @Serial private static final long serialVersionUID = 1;

    private char letter;

    public OXOPlayer(char playingLetter) {
        letter = playingLetter;
    }

    public char getPlayingLetter() {
        return letter;
    }

    public void setPlayingLetter(char letter) {
        this.letter = letter;
    }
}
