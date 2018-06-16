package ProjectPackman;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


import java.io.FileNotFoundException;
public class MyPlayer implements Player {
    private Circle ball;
    private Map map;
    private Position position;
    private int[][] list;
    private int vel = 50;
    boolean b;
    public MyPlayer(Map map) throws FileNotFoundException {
        list = map.getMap();
        this.map=map;
        int u = map.getUnit();
        position = map.getStart();
        ball = new Circle(position.getX() * u + u/2,position.getY() * u + u / 2,20);
        ball.setFill(Color.RED);
        ball.setStroke(Color.GRAY);
        map.getChildren().add(ball);
    }
    @Override
    public void moveRight() {
        try {
            if (list[position.getY()][position.getX() + 1] == 0) {
                ball.setCenterX(ball.getCenterX() + vel);
                int k = list[position.getY()][position.getX() + 1];
                list[position.getY()][position.getX() + 1] = list[position.getY()][position.getX()];
                list[position.getY()][position.getX()] = k;
                position.setX(position.getX() + 1);
            }
        }catch (ArrayIndexOutOfBoundsException ex){
        }
    }
    @Override
    public void moveLeft(){
        try {
            if (list[position.getY()][position.getX() - 1] == 0) {
                ball.setCenterX(ball.getCenterX() - vel);
                int k = list[position.getY()][position.getX() - 1];
                list[position.getY()][position.getX() - 1] = list[position.getY()][position.getX()];
                list[position.getY()][position.getX()] = k;
                position.setX(position.getX() - 1);
            }
        }catch (ArrayIndexOutOfBoundsException ex){
        }
    }
    @Override
    public void moveUp(){
        try {
            if (list[position.getY() - 1][position.getX()] == 0) {
                ball.setCenterY(ball.getCenterY() - vel);
                int k = list[position.getY() - 1][position.getX()];
                list[position.getY() - 1][position.getX()] = list[position.getY()][position.getX()];
                list[position.getY()][position.getX()] = k;
                position.setY(position.getY() - 1);
            }
        }catch (ArrayIndexOutOfBoundsException ex){
        }
    }
    @Override
    public void moveDown() {
        try {
            if (list[position.getY() + 1][position.getX()] == 0) {
                ball.setCenterY(ball.getCenterY() + vel);
                int k = list[position.getY()+1][position.getX()];
                list[position.getY()+1][position.getX()] = list[position.getY()][position.getX()];
                list[position.getY()][position.getX()] = k;
                position.setY(position.getY() + 1);
            }
        }catch (ArrayIndexOutOfBoundsException ex){
        }
    }
    @Override
    public Circle getBall() {
        return ball;
    }
    @Override
    public Position getPosition() {
        return position;
    }
}