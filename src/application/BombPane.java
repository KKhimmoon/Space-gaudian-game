package application;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BombPane extends HBox{
	private int amount = 50;
	private Canvas canvas;
	public BombPane(){
		this.setMaxSize(800, 600);
		String bomb_path = ClassLoader.getSystemResource("bomb.png").toString();
		Image bombIMG = new Image(bomb_path);
		ImageView bomb = new ImageView(bombIMG);
		this.setSpacing(10);
		bomb.setFitHeight(40);
		bomb.setFitWidth(40);
		this.setPadding(new Insets(0, 20, 20, 0));
		this.canvas = new Canvas(40,20);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawCurrentAmount(gc);
		this.getChildren().addAll(bomb,this.canvas);
//		this.setStyle("-fx-background-color: #F13030;");
	}
	
	public void drawCurrentAmount(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(new Font(20));
		gc.clearRect(0, 0,this.canvas.getWidth(), this.canvas.getHeight());
		gc.fillText( ""+ this.amount , this.canvas.getWidth() / 2-10 , this.canvas.getHeight() / 2+5);
	}


    
}
