package game;

import java.io.Serializable;

public class Piece implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4419807073409996423L;
    int type;
    // 0 pawn 1 rook 2 bishop 
    //3 knight 4 queen 5 king 
    int side;
    //1 black 2 white
    Position ownPosition;
    boolean alive;
    int moved = 0;
    //true = alive
    
    /**
     * Constructs an object of type Piece.
     * @param type
     * @param side
     * @param boardX
     * @param boardY
     */
    public Piece(int type, int side, int boardX, int boardY, boolean alive) {
        ownPosition = new Position(boardX, boardY);
        this.type = type;
        this.side = side;
        this.alive = alive;
    }
    
    public void setPosition(int boardX, int boardY) {
        ownPosition.fromBoardToX(boardX);
        ownPosition.fromBoardToY(boardY);
    }
    
    /**
     * Assign right image file name to this piece. 
     * @return imageName
     */
    public String imageName() {
        String imageName = "file:./";
        if (type == 0) {
            imageName += "pawn_";
        }
        if (type == 1) {
            imageName += "rook_";
        }
        if (type == 2) {
            imageName += "bishop_";
        }
        if (type == 3) {
            imageName += "knight_";
        }
        if (type == 4) {
            imageName += "queen_";
        }
        if (type == 5) {
            imageName += "king_";
        }

        if (side == 1) {
            imageName += "black.png";
        }
        if (side == 2) {
            imageName += "white.png";
        }
        
        return imageName;
    }
    
    
    public double getX() {
        return ownPosition.getX();
    }
    
    public double getY() {
        return ownPosition.getY();
    }
    
    public int getBoardX() {
        return ownPosition.getBoardX();
    }
    
    public int getBoardY() {
        return ownPosition.getBoardY();
    }
    
    /**
     * 
     * @param kill
     */
    public void alive(boolean kill) {
        alive = kill;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type for this Piece.
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the side
     */
    public int getSide() {
        return side;
    }

    /**
     * Sets the side for this Piece.
     * @param side the side to set
     */
    public void setSide(int side) {
        this.side = side;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the alive for this Piece.
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
