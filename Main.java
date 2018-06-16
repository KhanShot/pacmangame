package ProjectPackman;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import javafx.scene.image.Image;
import java.awt.Desktop;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.FileChooser;
public class Main extends Application {
    private MyBot bot;
    private Food food;
    private Map map;
    private MyPlayer player;
    public static void main(String[] args) {Application.launch(args);}@Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane,450,450);
        primaryStage.setTitle("PacMan");
        gridPane.setPadding(new Insets(20,15,20,15));
        gridPane.setVgap(15);
        gridPane.setHgap(18);
        primaryStage.setScene(scene);
        ImageView imageView = new ImageView();
        primaryStage.show();
        gridPane.setStyle("-fx-background-color: black");
        String url = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\mainLabel.JPG";
        String urlf = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\bub.JPG";
        String urlg = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\start.JPG";
        String ulra = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\controls.JPG";
        String urlb = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\about.JPG";
        //////pacmanmain

        File file = new File(url);
        BufferedImage  image= ImageIO.read(file);
        Image imagef = SwingFXUtils.toFXImage(image,null);
        imageView.setImage(imagef);
        imageView.setFitHeight(120);
        imageView.setFitWidth(330);
        gridPane.add(imageView,3,4);
        File file1 = new File(urlf);
        BufferedImage  image1= ImageIO.read(file1);
        Image img = SwingFXUtils.toFXImage(image1,null);
        ImageView imageView1 = new ImageView();
        imageView1.setImage(img);
        imageView1.setFitWidth(300);
        imageView1.setFitHeight(30);
        gridPane.add(imageView1,3,5);
        //startgame///////////////////////////
        File file2 = new File(urlg);
        BufferedImage bufferedImage = ImageIO.read(file2);
        Image image3 = SwingFXUtils.toFXImage(bufferedImage, null);
        ImageView imageView2 = new ImageView();
        imageView2.setImage(image3);
        Button start = new Button("",imageView2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(100);
        start.setPrefSize(100,20);
        start.setStyle("-fx-background-color: black");
        gridPane.add(start,3,7);
        start.setOnAction(new EventHandler<ActionEvent>() {          //here we begun
            @Override
            public void handle(ActionEvent event) {
                map = new Map("Hello");
                try {
                    map.startMaking();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    bot = new MyBot(map);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    food = new Food(map,bot);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bot.feed(food);
                Pane pane =new Pane();
                pane.getChildren().add(map);
                Scene scene = new Scene(pane,650,650);
                scene.setOnKeyPressed(e -> {
                    switch(e.getCode()){
                        case ENTER :
                            bot.traverse();
                    }
                });
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        //controls button
        File file3 = new File(ulra);
        BufferedImage bufferedImage1 = ImageIO.read(file3);
        Image image4 = SwingFXUtils.toFXImage(bufferedImage1, null);
        ImageView imageView3 = new ImageView();
        imageView3.setImage(image4);
        Button control = new Button("",imageView3);
        imageView3.setFitHeight(30);
        imageView3.setFitWidth(100);
        control.setPrefSize(100,15);
        control.setStyle("-fx-background-color: black");
        gridPane.add(control,3,8);
        control.setOnAction(new EventHandler<ActionEvent>() {@Override
        public void handle(ActionEvent event) {
            Stage stage = new Stage();
            GridPane pane = new GridPane();
            Scene scene1 = new Scene(pane,450,450);
            stage.setScene(scene1);
            stage.setTitle("Control Menu");
            stage.show();
            pane.setStyle("-fx-background-color: black");
            String u = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\mainLabel.JPG";
            File fil = new File(u);
            try {
                BufferedImage buff = ImageIO.read(fil);
                Image qwe = SwingFXUtils.toFXImage(buff,null);
                ImageView asd  = new ImageView();
                asd.setImage(qwe);
                asd.setFitHeight(120);
                asd.setFitWidth(330);
                pane.add(asd,3,4);
            } catch (IOException e) {e.printStackTrace();}
            Label ca = new Label("\tPress Buttons : Up, Down, Left, Right  to control PacMan, " +
                    "\n\t\tjust take it easy, and get enjoy");
            ca.cursorProperty();
            pane.add(ca,3,6);
            String qwt = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\quit.JPG";
            File fa = new File(qwt);
            try {
                BufferedImage sta = ImageIO.read(fa);
                Image qwe = SwingFXUtils.toFXImage(sta,null);
                ImageView asd  = new ImageView();
                asd.setImage(qwe);
                Button ba = new Button("",asd);
                ba.setStyle("-fx-background-color: black");
                asd.setFitHeight(50);
                asd.setFitWidth(50);
                ba.setPrefSize(50,20);
                ba.setOnAction(new EventHandler<ActionEvent>() {@Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
                });
                pane.add(ba,3,10);
            } catch (IOException e) {e.printStackTrace(); }
        }
        });
        //about button
        File file4 = new File(urlb);
        BufferedImage bufferedImage2 = ImageIO.read(file4);
        Image image5 = SwingFXUtils.toFXImage(bufferedImage2, null);
        ImageView imageView4 = new ImageView();
        imageView4.setImage(image5);
        Button about = new Button("",imageView4);
        imageView4.setFitHeight(30);
        imageView4.setFitWidth(100);
        about.setPrefSize(100,15);
        about.setStyle("-fx-background-color: black");
        gridPane.add(about,3,9);
        about.setOnAction(new EventHandler<ActionEvent>() {@Override
        public void handle(ActionEvent event) {
            Stage stage = new Stage();
            GridPane pane = new GridPane();
            Scene scene1 = new Scene(pane, 450,450);
            stage.setTitle("About PacMan");
            stage.setScene(scene1);
            pane.setStyle("-fx-background-color: black");
            stage.show();
            String u = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\mainLabel.JPG";
            File fil = new File(u);
            try {
                BufferedImage buff = ImageIO.read(fil);
                Image qwe = SwingFXUtils.toFXImage(buff,null);
                ImageView asd  = new ImageView();
                asd.setImage(qwe);
                asd.setFitHeight(120);
                asd.setFitWidth(330);
                pane.add(asd,3,4);
            } catch (IOException e) {e.printStackTrace();}
            Label ca = new Label("\tLICENSE taken  by BEKS, made by him; all rights are protected! " +
                    "\n\t\t\tgraphics made in Photoshop 2017 CC");
            ca.cursorProperty();
            pane.add(ca,3,6);
            String qwt = "C:\\Users\\User\\Desktop\\LESSONS\\java development\\PacMan\\MyProject\\src\\ProjectPackman\\images\\quit.JPG";
            File fa = new File(qwt);
            try {
                BufferedImage sta = ImageIO.read(fa);
                Image qwe = SwingFXUtils.toFXImage(sta,null);
                ImageView asd  = new ImageView();
                asd.setImage(qwe);
                Button ba = new Button("",asd);
                ba.setStyle("-fx-background-color: black");
                asd.setFitHeight(50);
                asd.setFitWidth(50);
                ba.setPrefSize(50,20);
                ba.setOnAction(new EventHandler<ActionEvent>(){@Override
                public void handle(ActionEvent event) {stage.close();}
                });
                pane.add(ba,3,10);
            } catch (IOException e) {e.printStackTrace();}
        }
        });
    }
}
