package gui;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BombPane extends HBox{
	private static Canvas canvas;
	private static GraphicsContext gc;
	private ImageView bombImg;

	public BombPane(){
		this.setMinSize(100, 50);
		setBombImg(new ImageView(sharedObject.RenderableHolder.bombItem));
		getBombImg().setFitHeight(40);
		getBombImg().setFitWidth(40);
		this.setPadding(new Insets(0, 0, 20, 0));
		setCanvas(new Canvas(40,30)); 
		setGc(getCanvas().getGraphicsContext2D());
		drawCurrentAmount(getGc());
		this.getChildren().addAll(getBombImg(),getCanvas());
	}
	
	public static void drawCurrentAmount(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(Font.loadFont(sharedObject.RenderableHolder.gameFontPath,15));
		gc.clearRect(0, 0,canvas.getWidth(), canvas.getHeight());
		gc.fillText( ""+ logic.GameLogic.getAmountBomb() , canvas.getWidth() / 2-10 , canvas.getHeight() / 2+5);
	}
	
	public static Canvas getCanvas() {
		return canvas;
	}

	public static void setCanvas(Canvas canvas) {
		BombPane.canvas = canvas;
	}

	public ImageView getBombImg() {
		return bombImg;
	}

	public void setBombImg(ImageView bombImg) {
		this.bombImg = bombImg;
	}

	public static GraphicsContext getGc() {
		return gc;
	}

	public static void setGc(GraphicsContext gc) {
		BombPane.gc = gc;
	}

    
}
