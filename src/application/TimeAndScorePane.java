package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class TimeAndScorePane extends VBox{
	private Canvas canvas;
	private static GraphicsContext gc;
	
	public static GraphicsContext getGc() {
		return gc;
	}
	public static void setGc(GraphicsContext gc) {
		TimeAndScorePane.gc = gc;
	}
	public TimeAndScorePane() {
		super();
//		this.setStyle("-fx-background-color: #F13030;");
		this.setSpacing(0);
		this.canvas = new Canvas(120,50);
		gc = this.canvas.getGraphicsContext2D();
		this.setPadding(new Insets(10, 0, 0, 0));
		this.setAlignment(Pos.BASELINE_RIGHT);
		this.getChildren().addAll(this.canvas,new Timer(100)); // add score ด้วย
	}
	
	public static void updateScore(GraphicsContext gc) {
		gc.clearRect(0, 0, 120, 50);
		String path = ClassLoader.getSystemResource("OldSchoolAdventures-42j9.ttf").toString();
		gc.setFont(Font.loadFont(path,12));
		gc.setFill(Color.WHITE);
		gc.fillText("SCORE : " + logic.GameLogic.getScore(),0,25);
	}
}