package application;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;


public class TimeAndScorePane extends VBox{
	private Text timer;
	
	public TimeAndScorePane() {
		super();
//		this.setStyle("-fx-background-color: #F13030;");
		this.prefHeight(200);
		this.prefWidth(800);
		this.setPadding(new Insets(15, 0, 0, 0));
		this.getChildren().addAll(new Timer(10)); // add score ด้วย
		
	}
	public void setTimer(Timer t) {
		timer.setText(t.toString());
	}
}