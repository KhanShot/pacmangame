package ProjectPackman;

import javafx.scene.shape.Circle;

public interface Player {
    public void moveRight();
    public void moveLeft();
    public void moveUp();
    public void moveDown();
    public Circle getBall();
    public Position getPosition();
}
