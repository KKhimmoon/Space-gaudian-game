package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TimeAndScorePane extends VBox{
	private Canvas canvas;
	private static GraphicsContext gc;

	public TimeAndScorePane() {
		super();
		setCanvas(new Canvas(120,50));
		gc = getCanvas().getGraphicsContext2D();
		this.setPadding(new Insets(10, 0, 0, 0));
		this.setAlignment(Pos.BASELINE_RIGHT);
		this.getChildren().addAll(getCanvas(),new Timer(100));
	}
	
	public static void updateScore(GraphicsContext gc) {
		gc.clearRect(0, 0, 120, 50);
		String path = sharedObject.RenderableHolder.gameFontPath;
		gc.setFont(Font.loadFont(path,12));
		gc.setFill(Color.WHITE);
		gc.fillText("SCORE : " + logic.GameLogic.getScore(),0,25);
	}
	
	public static GraphicsContext getGc() {
		return gc;
	}
	
	public static void setGc(GraphicsContext gc) {
		TimeAndScorePane.gc = gc;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}