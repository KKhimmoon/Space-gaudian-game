package gui;

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
	private ImageView bombImg;
	public static GraphicsContext getGc() {
		return gc;
	}

	public static void setGc(GraphicsContext gc) {
		BombPane.gc = gc;
	}

	public BombPane(){
		this.setMinSize(100, 50);
		bombImg = new ImageView(sharedObject.RenderableHolder.bombItem);
		bombImg.setFitHeight(40);
		bombImg.setFitWidth(40);
		this.setPadding(new Insets(0, 0, 20, 0));
		canvas = new Canvas(40,30);
		gc = canvas.getGraphicsContext2D();
		drawCurrentAmount(gc);
		this.getChildren().addAll(bombImg,canvas);
	}
	
	public static void drawCurrentAmount(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(Font.loadFont(sharedObject.RenderableHolder.gameFontPath,15));
		gc.clearRect(0, 0,canvas.getWidth(), canvas.getHeight());
		gc.fillText( ""+ logic.GameLogic.getAmountBomb() , canvas.getWidth() / 2-10 , canvas.getHeight() / 2+5);
	}


    
}
