package game;

import java.io.Serializable;

public class saveObject implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Piece[] pieces;
    int turn;
    boolean gameStarted;
    
    public saveObject() {

        pieces = null;
        this.gameStarted = false;
        this.turn = 1;
    }
    
    public saveObject(Piece[] pieceInput, int turns, boolean gamestarted) {

        pieces = pieceInput;
        this.gameStarted = gamestarted;
        this.turn = turns;
    }
    
    /**
     * @return the pieces
     */
    public Piece[] getPieces() {
        return pieces;
    }

    /**
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * @return the gamestarted
     */
    public boolean getGamestarted() {
        return gameStarted;
    }

}
