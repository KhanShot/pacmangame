package ProjectPackman;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Map extends Pane {
    private int unit = 50;
    private int size;
    private int[][] map;
    private Position start;
    public void setMap(int[][] map) {this.map = map;}
    public Map(String s){}
    public int getUnit(){return unit;}
    public int getSize(){return size;}
    public Position getStart(){return start; }
    private static Rectangle makeSquare(Color color, int unit, int x, int y){
        Rectangle rectangle = new Rectangle(x,y,unit,unit);
        rectangle.setFill(color);
        rectangle.setStroke(Color.WHITE);
        return rectangle;
    }
    public int[][] getMap() throws FileNotFoundException {
        File file1 = new File("file");
        Scanner in = new Scanner(file1);
        size = in.nextInt();
        map = new int[size][size];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map.length; j++){
                map[i][j] = in.nextInt();
            }
        }return map;
    }
    public void startMaking() throws FileNotFoundException {
        int[][] list = getMap();
        int x;
        int y = 0;
        for (int i = 0; i < list.length; i++){
            x = 0;
            for (int j = 0; j < list[i].length;j++){
                if (list[i][j]==1){
                    Rectangle rectangle = makeSquare(Color.DARKBLUE,unit,x,y);
                    getChildren().add(rectangle);
                }else {getChildren().add(makeSquare(Color.BLACK,unit,x,y)); }
                if (list[i][j]==2){start = new Position(j, i); }
                x+=unit;
            }
            y+=unit;
        }
    }
}