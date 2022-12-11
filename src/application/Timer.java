package application;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Timer extends Canvas{
	private int currentTime;
	private AnimationTimer animationTimer;
	private long lastTimeTriggered;
	
	
	public Timer(int s) {
		super(800,40);
		this.currentTime = s;
		this.lastTimeTriggered = -1;
		GraphicsContext gc = this.getGraphicsContext2D();
		this.animationTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(currentTime <= 0 ) {
					SpaceInvaders.modal.setVisible(true);
					return ;
				}
				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
				
				if (now - lastTimeTriggered >= 1000000000)
				{
					currentTime--;
					drawCurrentTimeString(gc);
					lastTimeTriggered = now;
				}
			}
		};
		this.animationTimer.start();
	}
	public void drawCurrentTimeString(GraphicsContext gc){
		gc.setFill(Color.YELLOW);
		gc.setFont(new Font(14));
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.fillText("Count Down Timer : " + this.currentTime, this.getWidth() / 2 +250, this.getHeight() / 2);
	}
	

}
