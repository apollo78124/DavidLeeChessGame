package game;

import java.io.Serializable;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * 
 * GameLogic.
 * 
 * @author Eunhak Lee A01026056 Set 2B
 * @version Feb 08, 2018
 */
public class GameLogic implements Serializable{

    /**
     * Position object to store clicked position.
     */
    private Position tileClick;

    /**
     * Array of chess pieces that stores position, whether it is alive, and its
     * side.
     */
    private Piece[] pieces;

    /**
     * Group of nodes to store the tile highlights. It will be modified to store all
     * highlights that show the path.
     */
    private Group selection;

    /**
     * Indicate where the user clicked.
     */
    private Text positionIndicator;

    /**
     * Indicate whos turn it is.
     */
    private Text turnIndicator;

    private Text invalidMove;

    /**
     * Whether the game started or not.
     */
    private boolean gameStarted;

    /**
     * If the piece is selected or not.
     */
    private boolean selected;

    /**
     * Node group that displays game component.
     */
    public Group gamePlay;

    /**
     * Piece number that is selected.
     */
    private int selectedPiece = -1;

    /**
     * 1: Black's turn, 0 white's turn.
     */
    private int turn;

    /**
     * Highlight the tile. Color changes according to turn. CYAN for white, blue for
     * black.
     */
    private Line[] selectionGUI;

    /**
     * Displays the intro.
     */
    private Text intro;

    /**
     * Constructs an object of type GameLogic.
     */
    public GameLogic() {
        final int numberOfAllPieces = 32;
        final int introXPosition = 60;
        final int introYPosition = 600;

        pieces = new Piece[numberOfAllPieces];
        final int four = 4;
        selectionGUI = new Line[four];
        gamePlay = new Group();
        tileClick = new Position(-1, -1);
        gameStarted = false;
        turn = 1;
        intro = new Text("When the selection square" + "\nis blue, it is black's turn."
                + "\nWhen it is cyan,\nit is white's turn.");

        intro.setLayoutX(introXPosition);
        intro.setLayoutY(introYPosition);
        gamePlay.getChildren().add(intro);

    }
    
    public GameLogic (Piece[] pieceInput, int turns, boolean gamestarted) {

        final int introXPosition = 60;
        final int introYPosition = 600;

        pieces = pieceInput;
        final int four = 4;
        selectionGUI = new Line[four];
        gamePlay = new Group();
        tileClick = new Position(-1, -1);
        this.gameStarted = gamestarted;
        turn = turns;
        intro = new Text("When the selection square" + "\nis blue, it is black's turn."
                + "\nWhen it is cyan,\nit is white's turn.");

        intro.setLayoutX(introXPosition);
        intro.setLayoutY(introYPosition);
        gamePlay.getChildren().add(intro);

    }

    /**
     * Define the action when the user clicks somewhere. and returns the game
     * components.
     * 
     * @param x
     *            clicked coordinate.
     * @param y
     *            clicked coordinate.
     * @return Group gamecomponent.
     */
    public Group click(double x, double y) {
        
        gamePlay.getChildren().clear();
        Text winner = new Text("");
        tileClick.fromCoordToBoard(x, y);
        if (gameStarted) {
            if (tileClick.getBoardX() > 0 && tileClick.getBoardY() > 0) {
                selection = new Group(selectionGUI(tileClick));
                gamePlay.getChildren().remove(selection);
                gamePlay.getChildren().add(selection);
                if (selected) {
                    movePiece(selectedPiece, tileClick.getBoardX(), tileClick.getBoardY());
                } else {
                    if (selectedPiece(tileClick.getBoardX(), tileClick.getBoardY()) > -1) {
                        selectedPiece = selectedPiece(tileClick.getBoardX(), tileClick.getBoardY());
                        selected = true;
                    }
                }
            }
            
            
            if (!pieces[27].isAlive()) {
                winner = new Text("Winner Winner \nChicken Dinner!\n White wins! ");
                winner.setLayoutX(300);
                winner.setLayoutY(290);
                winner.setFont(Font.font("Courier New", FontWeight.BOLD, 50));
                winner.setFill(Color.BLUE);
                gameStarted = false;
            }
            if (!pieces[12].isAlive()) {
                winner = new Text("Winner Winner \nChicken Dinner!\n Black wins! ");
                winner.setLayoutX(300);
                winner.setLayoutY(290);
                winner.setFont(Font.font("Courier New", FontWeight.BOLD, 50));
                winner.setFill(Color.BLUE);
                gameStarted = false;
            }

        }
        
        gamePlay.getChildren().add(renderPieces());
        gamePlay.getChildren().add(winner);
        return gamePlay;
    }

    /**
     * Group of nodes that hightlight the clicked tile.
     * 
     * @param tileClick
     *            clicked position.
     * @return the highlighter node in the right position.
     */
    public Group selectionGUI(Position tileClick) {

        Group selectionGUIGroup = new Group();
        selectionGUI[0] = new Line(tileClick.getX(), tileClick.getY(), tileClick.getX() + 70, tileClick.getY());
        selectionGUI[0].setStrokeWidth(5);
        selectionGUI[1] = new Line(tileClick.getX(), tileClick.getY() + 70, tileClick.getX() + 70,
                tileClick.getY() + 70);
        selectionGUI[1].setStrokeWidth(5);
        selectionGUI[2] = new Line(tileClick.getX(), tileClick.getY(), tileClick.getX(), tileClick.getY() + 70);
        selectionGUI[2].setStrokeWidth(5);
        selectionGUI[3] = new Line(tileClick.getX() + 70, tileClick.getY(), tileClick.getX() + 70,
                tileClick.getY() + 70);
        selectionGUI[3].setStrokeWidth(5);
        if (turn == 1) {
            selectionGUI[0].setStroke(Color.BLUE);
            selectionGUI[1].setStroke(Color.BLUE);
            selectionGUI[2].setStroke(Color.BLUE);
            selectionGUI[3].setStroke(Color.BLUE);
        } else {
            selectionGUI[0].setStroke(Color.CYAN);
            selectionGUI[1].setStroke(Color.CYAN);
            selectionGUI[2].setStroke(Color.CYAN);
            selectionGUI[3].setStroke(Color.CYAN);
        }
        selectionGUIGroup.getChildren().add(selectionGUI[0]);
        selectionGUIGroup.getChildren().add(selectionGUI[1]);
        selectionGUIGroup.getChildren().add(selectionGUI[2]);
        selectionGUIGroup.getChildren().add(selectionGUI[3]);

        return selectionGUIGroup;
    }
    
    public Group selectionGUI(int boardX, int boardY) {
        Position tileClick = new Position(boardX, boardY);
        Group selectionGUIGroup = new Group();
        Line a, b, c, d;
        a = new Line(tileClick.getX(), tileClick.getY(), tileClick.getX() + 70, tileClick.getY());
        a.setStrokeWidth(5);
        b = new Line(tileClick.getX(), tileClick.getY() + 70, tileClick.getX() + 70,
                tileClick.getY() + 70);
        b.setStrokeWidth(5);
        c = new Line(tileClick.getX(), tileClick.getY(), tileClick.getX(), tileClick.getY() + 70);
        c.setStrokeWidth(5);
        d = new Line(tileClick.getX() + 70, tileClick.getY(), tileClick.getX() + 70,
                tileClick.getY() + 70);
        d.setStrokeWidth(5);
        if (turn == 1) {
            a.setStroke(Color.BLUE);
            b.setStroke(Color.BLUE);
            c.setStroke(Color.BLUE);
            d.setStroke(Color.BLUE);
        } else {
            a.setStroke(Color.CYAN);
            b.setStroke(Color.CYAN);
            c.setStroke(Color.CYAN);
            d.setStroke(Color.CYAN);
        }
        selectionGUIGroup.getChildren().add(a);
        selectionGUIGroup.getChildren().add(b);
        selectionGUIGroup.getChildren().add(c);
        selectionGUIGroup.getChildren().add(d);

        return selectionGUIGroup;
    }

    /**
     * Layout the position of all pieces to default.
     */
    public void setPiecePositionDefault() {

        for (int i = 0; i < 8; i++) {
            pieces[i] = new Piece(0, 2, i + 1, 7, true);
        }

        pieces[8] = new Piece(1, 2, 1, 8, true);
        pieces[9] = new Piece(3, 2, 2, 8, true);
        pieces[10] = new Piece(2, 2, 3, 8, true);
        pieces[11] = new Piece(4, 2, 5, 8, true);
        pieces[12] = new Piece(5, 2, 4, 8, true);
        pieces[13] = new Piece(2, 2, 6, 8, true);
        pieces[14] = new Piece(3, 2, 7, 8, true);
        pieces[15] = new Piece(1, 2, 8, 8, true);

        for (int i = 16; i < 24; i++) {
            pieces[i] = new Piece(0, 1, i - 15, 2, true);
        }

        pieces[24] = new Piece(1, 1, 1, 1, true);
        pieces[25] = new Piece(3, 1, 2, 1, true);
        pieces[26] = new Piece(2, 1, 3, 1, true);
        pieces[27] = new Piece(5, 1, 4, 1, true);
        pieces[28] = new Piece(4, 1, 5, 1, true);
        pieces[29] = new Piece(2, 1, 6, 1, true);
        pieces[30] = new Piece(3, 1, 7, 1, true);
        pieces[31] = new Piece(1, 1, 8, 1, true);

    }

    /**
     * Assign images to every pieces alive.
     * 
     * @return Group of images.
     */
    public Group renderPieces() {
        Group pieceRender = new Group();
        ImageView iv;
        if (gameStarted) {
            if (selected) {
                Text selectedPieceIndicator = new Text("Selected piece: " + selectedPiece);
                selectedPieceIndicator.setLayoutX(70);
                selectedPieceIndicator.setLayoutY(320);
                selectedPieceIndicator.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
                selectedPieceIndicator.setFill(Color.BLACK);
                gamePlay.getChildren().add(selectedPieceIndicator);
            }

            for (int i = 0; i < 32; i++) {
                if (pieces[i].isAlive()) {
                    iv = pieceImage(pieces[i]);
                    pieceRender.getChildren().add(iv);
                }
            }
        }

        positionIndicator = new Text(tileClick.getBoardX() + ", " + tileClick.getBoardY());
        positionIndicator.setLayoutX(130);
        positionIndicator.setLayoutY(270);
        pieceRender.getChildren().add(positionIndicator);

        turnIndicator = new Text("Turn: " + printTurn());
        turnIndicator.setLayoutX(100);
        turnIndicator.setLayoutY(290);
        turnIndicator.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        pieceRender.getChildren().add(turnIndicator);

        pieceRender.getChildren().add(intro);

        return pieceRender;
    }

    public boolean validMove(int pieceID, int destX, int destY) {

        int eaten;
        int ownSide = pieces[pieceID].getSide();
        if (pieces[pieceID].getSide() == 1) {
            eaten = 2;
        } else {
            eaten = 1;
        }

        int boardX = pieces[pieceID].ownPosition.getBoardX();
        int boardY = pieces[pieceID].ownPosition.getBoardY();
        if (pieces[pieceID].type == 0) {
            // pawn
            if (pieces[pieceID].side == 2) {
                if (destX == boardX && (destY == (boardY - 1) ||  
                        (destY == (boardY - 2) 
                            && pieceInThePosition(boardX, boardY -1) < 0
                            && pieces[pieceID].moved == 0)
                        )&& pieceInThePosition(1, destX, destY) < 0) {

                    return true;
                }

                if (pieceInThePosition(1, destX, destY) >= 0 && destY == (boardY - 1)
                        && (destX == boardX + 1 || destX == boardX - 1)) {

                    return true;
                }

            } else {
                if (destX == boardX && 
                        (destY == (boardY + 1) ||  
                        (destY == (boardY + 2) 
                        && pieceInThePosition(2, boardX, boardY +1) < 0 && pieceInThePosition(1, boardX, boardY + 1) < 0
                        && pieces[pieceID].moved == 0)
                        )&& pieceInThePosition(2, destX, destY) < 0) {
                    return true;
                }

                if (pieceInThePosition(2, destX, destY) >= 0 && destY == (boardY + 1)
                        && (destX == boardX + 1 || destX == boardX - 1)) {
                  
                    return true;
                }
            }

        }
        if (pieces[pieceID].type == 1) {
            // rook
            if (clearPath(2, pieceID, destX, destY) && (destX == boardX || destY == boardY))
                return true;
        }
        if (pieces[pieceID].type == 2) {
            // bishop
            if (clearPath(1, pieceID, destX, destY) && (Math.abs(destX - boardX) == Math.abs(destY - boardY)))
                return true;
        }
        if (pieces[pieceID].type == 3) {
            // knight
            if (Math.abs(boardX - destX) == 2 && Math.abs(boardY - destY) == 1)
            return true;
            if (Math.abs(boardX - destX) == 1 && Math.abs(boardY - destY) == 2)
                return true;
        }
        if (pieces[pieceID].type == 4) {
            // queen
            if (clearPath(2, pieceID, destX, destY) && (destX == boardX || destY == boardY))
                return true;
            if (clearPath(1, pieceID, destX, destY) && (Math.abs(destX - boardX) == Math.abs(destY - boardY)))
                return true;
        }
        if (pieces[pieceID].type == 5) {
            // king
            if (Math.abs(boardX - destX) == 1 && Math.abs(boardY - destY) == 1)
                return true;
            if (boardX == destX && Math.abs(boardY - destY) == 1)
                return true;
            if (boardY == destY&& Math.abs(boardX - destX) == 1)
                return true;
        }
        
        invalidMove = new Text("Invalid Move!!!\nTry again.");
        invalidMove.setFont(Font.font("Verdana", 20));
        invalidMove.setFill(Color.RED);
        invalidMove.setLayoutX(90);
        invalidMove.setLayoutY(350);
        gamePlay.getChildren().add(invalidMove);

        return false;
    }
    
    public boolean clearPath(int direction, int pieceID, int destX, int destY) {
        //1 for diagonal 2 for straght
        int x = pieces[pieceID].ownPosition.getBoardX();
        int y = pieces[pieceID].ownPosition.getBoardY();

        boolean diX = x < destX;
        boolean diY = y < destY;
        if (direction == 1) {
            if (!diX && !diY) {
                for (x--, y--;x > destX || y > destY; x--, y--) {
                    if (pieceInThePosition(x, y) >= 0)
                        return false;
                }
            }
            if (!diX && diY) {
                for (x--, y++;x > destX || y < destY; x--, y++) {
                    if (pieceInThePosition(x, y) >= 0)
                        return false;
                }
                
            }
            if (diX && !diY) {
                for (x++, y--;x < destX || y > destY; x++, y--) {
                    if (pieceInThePosition(x, y) >= 0)
                        return false;
                }
            }
            if (diX && diY) {
                for (x++, y++;x < destX || y < destY; x++, y++) {
                    if (pieceInThePosition(x, y) >= 0)
                        return false;
                }
            }
        }
        
        if (direction == 2) {
            if (destX == x) {
                if (diY) {
                    for (++y; y < destY; y++) {
                        if (pieceInThePosition(x, y) >= 0) {
                          
                            return false;
                        }
                    }
                } else {
                    for (--y; y > destY; y--) {
                        if (pieceInThePosition(x, y) >= 0) {
                            return false;
                            }
                    }
                }
            }
            
            if (destY == y) {
                if (diX) {
                    for (++x;x < destX; x++) {
                        if (pieceInThePosition(x, y) >= 0)
                            return false;
                    }
                } else {
                    for (--x;x > destX; x--) {
                        if (pieceInThePosition(x, y) >= 0)
                            
                            return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Assign right image to each pieces.
     * 
     * @param piece
     *            piece object.
     * @return ImageView.
     */
    public ImageView pieceImage(Piece piece) {
        ImageView iv;
        iv = new ImageView(new Image(piece.imageName()));
        iv.setX(piece.getX());
        iv.setY(piece.getY());
        iv.setFitHeight(70);
        iv.setFitWidth(70);
        return iv;
    }

    /**
     * Finds the right piece that is selected.
     * 
     * @param boardX
     *            chessboard Coordinate selected.
     * @param boardY
     *            chessboard Coordinate selected.
     * @return piece number that is selected.
     */
    public int selectedPiece(int boardX, int boardY) {
        int i = 0;
        int j = 16;
        if (turn == 1) {
            i += 16;
            j += 16;
        }
        for (; i < j; i++) {
            if (pieces[i].getBoardX() == boardX && pieces[i].getBoardY() == boardY && pieces[i].isAlive()) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Piece in certain position in certain side.
     * 
     * @param side
     *            1 for black, 2 for white.
     * @param boardX
     *            chessboard coordinate.
     * @param boardY
     *            chessboard coordinate.
     * @return Piece number that is in certain position.
     */
    public int pieceInThePosition(int side, int boardX, int boardY) {

        final int totalNumberOfPieces = 32;
        for (int i = 0; i < totalNumberOfPieces; i++) {
            if (pieces[i].getBoardX() == boardX && pieces[i].getBoardY() == boardY && pieces[i].isAlive()
                    && pieces[i].getSide() == side) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int pieceInThePosition(int boardX, int boardY) {
        final int totalNumberOfPieces = 32;
        for (int i = 0; i < totalNumberOfPieces; i++) {
            if (pieces[i].getBoardX() == boardX && pieces[i].getBoardY() == boardY && pieces[i].isAlive()) {
              
                return i;
            }
        }

        return -1;
    }

    /**
     * Move the pice to another position. Cannot move to where its own pieces are.
     * Kill the piece in the other side when it is eaten.
     * 
     * @param pieceNumber
     *            Piece identifier.
     * @param toBoardX
     *            Destination board coordinate.
     * @param toBoardY
     *            Destination board coordinate.
     */
    public void movePiece(int pieceNumber, int toBoardX, int toBoardY) {
        int eaten;
        int ownSide = pieces[pieceNumber].getSide();
        if (pieces[pieceNumber].getSide() == 1) {
            eaten = 2;
        } else {
            eaten = 1;
        }

        if (pieceInThePosition(ownSide, toBoardX, toBoardY) >= 0) {
            selected = false;
            return;
        } else {
            if (validMove(pieceNumber, toBoardX, toBoardY)) {
                pieces[pieceNumber].setPosition(toBoardX, toBoardY);
                selected = false;
                pieces[pieceNumber].moved++;
                switchTurn();
            } else {
                selected = false;
                return;
            }

        }
        if (pieceInThePosition(eaten, toBoardX, toBoardY) >= 0) {
            pieces[pieceInThePosition(eaten, toBoardX, toBoardY)].setAlive(false);
        }
    }

    /**
     * Switch the turn and change the color of the highlighter.
     */
    public void switchTurn() {
        if (turn == 1) {
            selectionGUI[0].setStroke(Color.CYAN);
            selectionGUI[1].setStroke(Color.CYAN);
            selectionGUI[2].setStroke(Color.CYAN);
            selectionGUI[3].setStroke(Color.CYAN);
            turn = 0;
        } else {
            selectionGUI[0].setStroke(Color.BLUE);
            selectionGUI[1].setStroke(Color.BLUE);
            selectionGUI[2].setStroke(Color.BLUE);
            selectionGUI[3].setStroke(Color.BLUE);
            turn = 1;
        }
    }

    /**
     * Print the turn.
     * 
     * @return Side indicator.
     */
    public String printTurn() {
        if (turn == 0) {
            return "White";
        } else {
            return "Black";
        }
    }

    /**
     * Start the game, align all the pieces to default.
     * 
     * @return gameComponents.
     */
    public Group play() {
        gameStarted = true;
        turn = 1;
        selected = false;
        setPiecePositionDefault();
        Group piece = renderPieces();
        gamePlay.getChildren().add(piece);

        return gamePlay;
    }
    
    public Group play(int i) {
        gamePlay.getChildren().clear();
        selected = false;
        Group piece = renderPieces();
        gamePlay.getChildren().add(piece);

        return gamePlay;
    }

    /**
     * @return the pieces
     */
    public Piece[] getPieces() {
        return pieces;
    }

    /**
     * @return the gameStarted
     */
    public boolean getGameStarted() {
        return gameStarted;
    }

    /**
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }

}
