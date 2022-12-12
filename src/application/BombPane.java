package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BombPane extends HBox{
	private static Canvas canvas;
	private static GraphicsContext gc;
	public static GraphicsContext getGc() {
		return gc;
	}

	public static void setGc(GraphicsContext gc) {
		BombPane.gc = gc;
	}

	public BombPane(){
		this.setMinSize(100, 50);
		String bomb_path = ClassLoader.getSystemResource("bomb.png").toString();
		Image bombIMG = new Image(bomb_path);
		ImageView bomb = new ImageView(bombIMG);
		this.setSpacing(10);
		bomb.setFitHeight(40);
		bomb.setFitWidth(40);
		this.setPadding(new Insets(0, 20, 20, 0));
		canvas = new Canvas(40,20);
		gc = canvas.getGraphicsContext2D();
		drawCurrentAmount(gc);
		this.getChildren().addAll(bomb,canvas);
//		this.setStyle("-fx-background-color: #F13030;");
	}
	
	public static void drawCurrentAmount(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		String path = ClassLoader.getSystemResource("OldSchoolAdventures-42j9.ttf").toString();
		gc.setFont(Font.loadFont(path,15));
		gc.clearRect(0, 0,canvas.getWidth(), canvas.getHeight());
		gc.fillText( ""+ logic.GameLogic.getCountBomb() , canvas.getWidth() / 2-10 , canvas.getHeight() / 2+5);
	}


    
}
