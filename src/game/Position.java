package game;

import java.io.Serializable;

/**
 * 
 * Position of anything. 
 * It converts the board coordinates to actual coordinate. 
 * 
 * @author Eunhak Lee
 * @version Feb 08, 2018
 */
public class Position implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 213728112540791369L;

    /**
     * Actual coordinate. 
     */
    public double x;
    
    /**
     * Actual coordinate. 
     */
    public double y;
    
    /**
     * Board coordinate. 
     */
    public int boardX;
    
    /**
     * Board coordinate. 
     */
    public int boardY;
    
    /**
     * Constructs an object of type Position.
     * @param x board coordinate. 
     * @param y board coordinate. 
     */
    public Position(int x, int y) {
        boardX = x;
        boardY = y;
        fromBoardToCoord(x, y);
    }
    
    /**
     * From actual coordinate to the board coordinate. 
     * @param x actual coordinate. 
     * @param y actual coordinate. 
     * @return boolean. 
     */
    public boolean fromCoordToBoard(double x, double y) {
        if (x >= 280 
                && x < 840 
                && y >= 140 
                && y < 700) {
            fromXtoBoard(x);
            fromYtoBoard(y);
            return true;
        } else {
            boardX = -1;
            boardY = -1;
            x = -1;
            y = -1;
            return false;
        }
        
    }
    
    /**
     * From board coordinate to actual coordinate. 
     * @param x board coordinate. 
     * @param y board coordinae. 
     * @return boolean. 
     */
    public boolean fromBoardToCoord(int x, int y) {
        
        if (x > 0 && x < 9 
                && y > 0 && y < 9) {
            fromBoardToX(x);
            fromBoardToY(y);
            return true;
        } else {
            boardX = -1;
            boardY = -1;
            x = -1;
            y = -1;
            return false;
        }
    }
    
    /**
     * From actual coordinate to board coordinate. 
     * @param x actual coordinate. 
     */
    public void fromXtoBoard(double x) {
        
        if (x >= 280 && x < 350) {
            boardX = 1;
            this.x = 280;
            return;
        }
        if (x >= 350 && x < 420) {
            boardX = 2;
            this.x = 350;
            return;
        }
        if (x >= 420 && x < 490) {
            boardX = 3;
            this.x = 420;
            return;
        }
        if (x >= 490 && x < 560) {
            boardX = 4;
            this.x = 490;
            return;
        }
        if (x >= 560 && x < 630) {
            boardX = 5;
            this.x = 560;
            return;
        }
        if (x >= 630 && x < 700) {
            boardX = 6;
            this.x = 630;
            return;
        }
        if (x >= 700 && x < 770) {
            boardX = 7;
            this.x = 700;
            return;
        }
        if (x >= 770 && x < 840) {
            boardX = 8;
            this.x = 770;
            return;
        }

    }
    
    /**
     * From actual coordinate to board coordinate.  
     * @param y 
     */
    public void fromYtoBoard(double y) {
        
        if (y >= 140 && y < 210) {
            boardY = 1;
            this.y = 140;
            return;
        }
        if (y >= 210 && y < 280) {
            boardY = 2;
            this.y = 210;
            return;
        }
        if (y >= 280 && y < 350) {
            boardY = 3;
            this.y = 280;
            return;
        }
        if (y >= 350 && y < 420) {
            boardY = 4;
            this.y = 350;
            return;
        }
        if (y >= 420 && y < 490) {
            boardY = 5;
            this.y = 420;
            return;
        }
        if (y >= 490 && y < 560) {
            boardY = 6;
            this.y = 490;
            return;
        }
        if (y >= 560 && y < 630) {
            boardY = 7;
            this.y = 560;
            return;
        }
        if (y >= 630 && y < 700) {
            boardY = 8;
            this.y = 630;
            return;
        }
    }
    
    /**
     * From board coordinate to 
     * @param x
     */
    public void fromBoardToX(int x) {
        boardX = x;
        this.x = (x + 2) * 70 + 70;
    }

    public void fromBoardToY(int y) {
        boardY = y;
        this.y = y * 70 + 70;
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x for this Position.
     * 
     * @param x
     *            the x to set
     */
    public void setX(double x) {
        this.x = x;
        fromXtoBoard(x);
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y for this Position.
     * 
     * @param y
     *            the y to set
     */
    public void setY(double y) {
        this.y = y;
        fromYtoBoard(y);
    }

    /**
     * @return the horizontalX
     */
    public int getBoardX() {
        return boardX;
    }

    /**
     * Sets the horizontalX for this Position.
     * 
     * @param horizontalX
     *            the horizontalX to set
     */
    public void setBoardX(int horizontalX) {
        this.boardX = horizontalX;
        fromBoardToX(horizontalX);
    }

    /**
     * @return the verticalY
     */
    public int getBoardY() {
        return boardY;
    }

    /**
     * Sets the verticalY for this Position.
     * 
     * @param verticalY
     *            the verticalY to set
     */
    public void setBoardY(int verticalY) {
        this.boardY = verticalY;
        fromBoardToY(verticalY);
    }
}
