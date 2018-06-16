package ProjectPackman;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Food {
    private Map map;
    private Pane foodPane;
    private Player player;
    private Circle circle;
    private Position foodPosition;
    private Label seconds;
    private int numOfCircles = 10;
    private int time;
    private int points;
    private int size;
    private int[][] cells;
    public Position getFoodPosition() {
        return foodPosition;
    }
    public int getNumOfCircles() {
        return numOfCircles;
    }
    public Food(Map var1, Player var2) throws FileNotFoundException {
        this.map = var1;
        this.foodPane = new Pane();
        this.map.getChildren().add(this.foodPane);
        this.player = var2;
        this.size = this.map.getSize();
        this.cells = this.map.getMap();

        Thread var3 = new Thread(() -> {
            while(this.numOfCircles > 0) {
                try {
                    this.createFood();
                    if (map.getMap()[this.getFoodPosition().getY()][this.getFoodPosition().getX()] == 1){
                        continue;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    this.foodPane.getChildren().addAll(new Node[]{this.circle, this.seconds});
                });
                for(this.time = 20; this.time > 0; --this.time) {
                    Platform.runLater(() -> {
                        this.seconds.setText("" + this.time);
                    });
                    if (this.player.getPosition().equals(this.foodPosition)) {
                        this.points += this.time;
                        break;
                    }
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException v3) {
                    }
                }
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException v2) {
                }
                Platform.runLater(() -> {
                    this.foodPane.getChildren().clear();
                });
                --this.numOfCircles;
            }
            Platform.runLater(this::scores);
            System.out.println(this.getPoints());
        });
        var3.start();
    }
    public int getPoints(){
        return this.points;}
    private void createFood() throws FileNotFoundException {
        Random var1 = new Random();
        double var4 = (double)this.map.getUnit();
        int var2;
        int var3;
        do {
            do {
                var2 = var1.nextInt(this.size);
                var3 = var1.nextInt(this.size);
            } while(this.player.getPosition().equals(new Position(var2, var3)));
        } while(this.map.getMap()[var2][var3] != 0);
        this.circle = new Circle((double)var2 * var4 + var4 / 2.0D, (double)var3 * var4 + var4 / 2.0D, var4 / 4.0D);
        this.circle.setFill(Color.GREEN);
        this.foodPosition = new Position(var2, var3);
        this.seconds = new Label("5");
        this.seconds.setTranslateX((double)var2 * var4);
        this.seconds.setTranslateY((double)var3 * var4);
    }
    private void scores(){
        Stage stage = new Stage();
        Pane pane = new Pane();
        Scene scene = new Scene(pane,300,300);
        Label la = new Label("\t\t\t\t\tYour score is: "+ getPoints());
        pane.getChildren().add(la);
        Button ba = new Button("OK");
        pane.getChildren().add(ba);
        ba.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {System.exit(1); }});
        stage.setScene(scene);
        stage.show();
    }
}


