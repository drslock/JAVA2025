package edu.uob;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serial;
import javax.swing.JPanel;

public class OXOView extends JPanel {
    @Serial private static final long serialVersionUID = 1;
    private static int MARGIN = 50;

    private OXOModel model;

    public OXOView(OXOModel mod) {
        model = mod;
    }

    @Override
    protected void paintComponent(Graphics g) {
        float fontSizeByWidth = (getWidth()-MARGIN*2)/model.getNumberOfColumns();
        float fontSizeByHeight = (getHeight()-MARGIN*2)/model.getNumberOfRows();
        float fontSize = Math.min(fontSizeByWidth,fontSizeByHeight);
        if (fontSize>20) fontSize = 20;

        g.setFont(new Font("Monospaced", Font.PLAIN, (int)fontSize));

        // Clear the whole board
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        float horiSpacing = (float)(getWidth()-MARGIN*2.0) / (float)model.getNumberOfColumns();
        float vertSpacing = (float)(getHeight()-MARGIN*2.0) / (float)model.getNumberOfRows();

        // Draw horizontal lines
        g.setColor(Color.BLACK);
        for (int i = 0; i < model.getNumberOfRows() - 1; i++) {
            g.drawLine(MARGIN, (int)(MARGIN+vertSpacing*(i+1)), getWidth()-MARGIN, (int)(MARGIN+vertSpacing*(i+1)));
        }
        // Draw vertical lines
        for (int i = 0; i < model.getNumberOfColumns() - 1; i++) {
            g.drawLine((int)(MARGIN+horiSpacing*(i+1)), MARGIN, (int)(MARGIN+horiSpacing*(i+1)), getHeight()-MARGIN);
        }

        // Draw the row labels
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < model.getNumberOfRows(); i++) {
            g.drawString("" + (char)('a'+i), MARGIN/2, (int)(MARGIN-2+(fontSize/2.0f)+vertSpacing*(i+0.5)));
        }

        // Draw the column labels
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < model.getNumberOfColumns(); i++) {
            g.drawString("" + (char)('1'+i), (int)(MARGIN+2-(fontSize/2.0f)+horiSpacing*(i+0.5)), (int)((MARGIN/2)+(fontSize/2.0f)));
        }

        // Draw the board state
        g.setColor(Color.BLACK);
        for (int colNumber = 0; colNumber < model.getNumberOfColumns(); colNumber++) {
            for (int rowNumber = 0; rowNumber < model.getNumberOfRows(); rowNumber++) {
                int xpos = (int) (((float) MARGIN) + 2.0 - (fontSize / 2.0) + (horiSpacing * (((float)colNumber) + 0.5f)));
                int ypos = (int) (((float) MARGIN) - 2.0 + (fontSize / 2.0) + (vertSpacing * (((float)rowNumber) + 0.5f)));
                OXOPlayer cellOwner = model.getCellOwner(rowNumber, colNumber);
                if (cellOwner != null) g.drawString("" + cellOwner.getPlayingLetter(), xpos, ypos);
            }
        }
        String message;
        if (model.getWinner() != null) message = "Player " + model.getWinner().getPlayingLetter() + " is the winner !";
        else if (model.isGameDrawn()) message = "Stalemate - game is a draw !";
        else message = "Player " + model.getPlayerByNumber(model.getCurrentPlayerNumber()).getPlayingLetter() + "'s turn";

        // Draw the message near the bottom of the screen
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString(message, 7, getHeight() - 10);
    }
}
