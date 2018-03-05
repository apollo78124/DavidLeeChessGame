package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Rendering of the board display. 
 * Board.
 * 
 * @author Eunhak Lee A01026056 Set 2B
 * @version Feb 08, 2018
 */
public class Board {
    
    /**
     * Contans the board component. 
     */
    private Group root;
    
    /**
     * Returns the board. 
     * @return board node. 
     * @throws Exception node. 
     */
    public Group returnBoard() throws Exception {
        // TODO Auto-generated method stub
        root = new Group();
        final int adjustToTheBoard = 70;
        
        for (int i = 1; i < 9; i++) {
            for (int j = 3; j < 11; j++) {
                
                Rectangle chessRectangle = new Rectangle(
                        j * adjustToTheBoard + adjustToTheBoard,
                        i * adjustToTheBoard + adjustToTheBoard,
                        adjustToTheBoard, adjustToTheBoard);
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        chessRectangle.setFill(Color.BLACK);
                    } else {
                        chessRectangle.setFill(Color.WHITE);
                    } 
                } else {
                    if (j % 2 == 0) {
                        chessRectangle.setFill(Color.WHITE);
                    } else {
                        chessRectangle.setFill(Color.BLACK);
                    } 
                }
                root.getChildren().add(chessRectangle);
            }
            
        }
     
        return root;
    }

    
}
