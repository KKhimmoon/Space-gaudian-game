package application;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Timer extends Canvas{
	private int currentTime;
	private static AnimationTimer animationTimer;
	private long lastTimeTriggered;
	
	
	public Timer(int s) {
		super(80,40);
		this.currentTime = s;
		this.lastTimeTriggered = -1;
		GraphicsContext gc = this.getGraphicsContext2D();
		setAnimationTimer(new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(currentTime <= 0 ) {
//					Platform.runLater(new Runnable() {
//						
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//							
//						}
//					});
//					try {
//						logic.GameLogic.endgame = FXMLLoader.load(getClass().getResource("EndgameScene.fxml"));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					Endgame.updateYourScore(Endgame.getGc());
					logic.GameLogic.endgame.setVisible(true);
					getAnimationTimer().stop();
					
				}
				logic.GameLogic.run(logic.GameLogic.getGc());
				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
				
				if (now - lastTimeTriggered >= 1000000000)
				{
//					if(currentTime <= 0 ) {
//						logic.GameLogic.modal.setVisible(true);
//						return ;
//					}
					currentTime--;
					drawCurrentTimeString(gc);
					lastTimeTriggered = now;
				}
			}
		});
		getAnimationTimer().start();
	}
	public void drawCurrentTimeString(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		String path = ClassLoader.getSystemResource("OldSchoolAdventures-42j9.ttf").toString();
		gc.setFont(Font.loadFont(path,15));
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.drawImage(new Image(ClassLoader.getSystemResource("clock.png").toString()), 0, 0, 40, 40);
		gc.fillText("" + this.currentTime, this.getWidth()/2, this.getHeight() / 2 +12);
	}
	public static AnimationTimer getAnimationTimer() {
		return animationTimer;
	}
	public static void setAnimationTimer(AnimationTimer animationTimer) {
		Timer.animationTimer = animationTimer;
	}
	

}
