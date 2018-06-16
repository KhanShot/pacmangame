package ProjectPackman;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.io.FileNotFoundException;
public class MyBot implements BotPlayer {
    private Map map;
    private int size;
    private int unit;
    private Circle ball;
    private int[][] list;
    private Position position;
    private Food food;
    public MyBot(Map map) throws FileNotFoundException {
        this.map = map;
        list = map.getMap();
        size = map.getSize();
        unit = map.getUnit();
        position = map.getStart();
        ball = new Circle(position.getX() * unit + unit/2,position.getY() * unit + unit / 2,unit/2);
        ball.setFill(Color.RED);
        ball.setStroke(Color.GRAY);
        map.getChildren().add(ball);
    }
    @Override
    public void feed(Food f) {
        this.food = f;
    }
    @Override
    public void traverse() {
        new Thread(()->{
            while (!this.position.equals(this.food.getFoodPosition())){
                while (this.position.getX()<size-1 && !this.position.equals(this.food.getFoodPosition())){
                    moveLeft();
                }
                if (!this.position.equals(this.food.getFoodPosition())) {
                    moveUp();
                }
                while (this.position.getX()>=0 && !this.position.equals(this.food.getFoodPosition())){
                    moveRight();
                }
                if (!this.position.equals(this.food.getFoodPosition())) {
                    moveDown2();
                }
            }
        }).start();
    }
    @Override
    public void eat() {
        new Thread(()->{
            int count = food.getNumOfCircles();
            while (count!=0) {
                while (!this.position.equals(food.getFoodPosition())) {
                    while (position.getY() > food.getFoodPosition().getY()) {
                            moveUp();
                    }
                    while (position.getY() < food.getFoodPosition().getY()) {
                            moveDown();
                    }
                    while (position.getX() > food.getFoodPosition().getX()) {
                            moveLeft();
                    }
                    while (position.getX() < food.getFoodPosition().getX()) {
                            moveRight();
                    }
                }
                if (this.position.equals(food.getFoodPosition())){
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
            }
        }).start();
    }
    @Override
    public void find() {
        new Thread(()->{
            int count = food.getNumOfCircles();
            while (count!=0) {
                while (!this.position.equals(food.getFoodPosition())) {
                    while (position.getY() > food.getFoodPosition().getY()) {
                        try {
                            if (list[position.getY() - 1][position.getX()] != 1) {
                                moveUp();
                            }
                            else  if (list[position.getY()][position.getX() + 1] != 1) {
                                moveRight();
                            }
                            else if (list[position.getY()][position.getX() - 1] != 1) {
                                moveLeft();
                            }

                        }catch (ArrayIndexOutOfBoundsException e){
                        }
                    }
                    while (position.getY() < food.getFoodPosition().getY()) {
                        try {
                            if (list[position.getY() + 1][position.getX()] != 1) {
                                moveDown();
                            }
                            else if (list[position.getY()][position.getX() + 1] != 1) {
                                moveRight();
                            }
                            else if (list[position.getY()][position.getX() - 1] != 1) {
                                moveLeft();
                            }

                        }catch (ArrayIndexOutOfBoundsException e){
                        }
                    }
                    while (position.getX() > food.getFoodPosition().getX()) {
                        try {
                            if (list[position.getY()][position.getX() - 1] != 1) {
                                moveLeft();
                            } else if (list[position.getY() - 1][position.getX()] != 1) {
                                moveUp();
                            } else if (list[position.getY() + 1][position.getX()] != 1) {
                                moveDown();
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                        }
                    }
                    while (position.getX() < food.getFoodPosition().getX()) {
                        try {
                            if (list[position.getY()][position.getX() + 1] != 1) {
                                moveRight();
                            } else if (list[position.getY() - 1][position.getX()] != 1) {
                                moveUp();
                            } else if (list[position.getY() + 1][position.getX()] != 1) {
                                moveDown();
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                        }
                    }
                }
                if (this.position.equals(food.getFoodPosition())){
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
            }
        }).start();
    }
    @Override
    public void moveRight() {
        try {
            ball.setCenterX(ball.getCenterX() + unit);
            position.setX(position.getX() + 1);
            Thread.sleep(200);
        }catch (ArrayIndexOutOfBoundsException ignored){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void moveLeft() {
        try {
            ball.setCenterX(ball.getCenterX() - unit);
            position.setX(position.getX() - 1);
            Thread.sleep(200);

        }catch (ArrayIndexOutOfBoundsException ex){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void moveUp() {
        try {
            ball.setCenterY(ball.getCenterY() - unit);
            position.setY(position.getY() - 1);
            Thread.sleep(200);
        }catch (ArrayIndexOutOfBoundsException ex){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void moveDown() {
        try {
            ball.setCenterY(ball.getCenterY() + unit);
            position.setY(position.getY() + 1);
            Thread.sleep(200);

        }catch (ArrayIndexOutOfBoundsException ex){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void moveDown2() {
        try {
            ball.setCenterY(ball.getCenterY() + unit);
            position.setY(position.getY() + 1);
            Thread.sleep(200);

        }catch (ArrayIndexOutOfBoundsException ex){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Circle getBall() {
        return null;
    }
    @Override
    public Position getPosition() {
        return this.position;
    }
    public int[][] newMap(Position food2) {
        int y = 0, ky = 0;
        int x = 0, kx = 0;
        list[food2.getY()][food2.getX()] = 3;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j] == 2) {
                    x = j;
                    y = i;
                } else if (list[i][j] == 3) {
                    kx = j;
                    ky = i;
                }
            }
        }
        if (y<=ky && x <=kx) {
            for (int i = y; i <= ky; i++) {
                for (int j = x; j <= kx; j++) {
                    if (j <= kx && i == ky || j == x && i <= ky)
                        list[i][j] = 5;
                }
            }
        }
        else if (x>kx){
            for (int i = y; i <= ky; i++) {
                for (int j = x; j >= kx; j--) {
                    if (j >= kx && i == ky || j == x && i <= ky)
                        list[i][j] = 5;
                }
            }
        }else if (y > ky){
            for (int i = y; i >= ky; i--) {
                for (int j = x; j <= kx; j++) {
                    if (j <= kx && i == ky || j == x && i >= ky)
                        list[i][j] = 5;
                }
            }
        }
        for (int i = y; i >= ky; i--) {
            for (int j = x; j >= kx; j--) {
                if (j >= kx && i == ky || j == x && i >= ky)
                    list[i][j] = 5;
            }
        }
        list[y][x] = 2; list[ky][kx] = 3;
        return list;
    }
    public void printL(int[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
    }
}
