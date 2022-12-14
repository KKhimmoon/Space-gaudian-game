package gui;

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
import screendrawing.MainGameScreen;

public class Timer extends Canvas{
	private int currentTime;
	public static AnimationTimer animationTimer;
	public static AnimationTimer mainGameSound;
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
					EndGameController.updateYourScore(EndGameController.getGc());
					screendrawing.MainGameScreen.getInstance().endGame.setVisible(true);
					getAnimationTimer().stop();
					
				}
				MainGameScreen.getInstance().run();
				
				lastTimeTriggered = (lastTimeTriggered < 0 ? now : lastTimeTriggered);
				
				if (now - lastTimeTriggered >= 1000000000)
				{
					currentTime--;
					drawCurrentTimeString(gc);
					lastTimeTriggered = now;
				}
			}
		});
		getAnimationTimer().start();
		playMainGameSound();
	}
	
	public static AnimationTimer getMainGameSound() {
		return mainGameSound;
	}

	public static void setMainGameSound(AnimationTimer mainGameSound) {
		Timer.mainGameSound = mainGameSound;
	}

	public void playMainGameSound() {
		mainGameSound = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if(!sharedObject.RenderableHolder.mainGameSound.isPlaying()) {
					sharedObject.RenderableHolder.mainGameSound.play();
				}
			}
		};
		mainGameSound.start();
	}
	
	public void drawCurrentTimeString(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		String path = sharedObject.RenderableHolder.gameFontPath;
		gc.setFont(Font.loadFont(path,15));
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.drawImage(sharedObject.RenderableHolder.clock, 0, 0, 40, 40);
		gc.fillText("" + this.currentTime, this.getWidth()/2, this.getHeight() / 2 +12);
	}
	public static AnimationTimer getAnimationTimer() {
		return animationTimer;
	}
	public static void setAnimationTimer(AnimationTimer animationTimer) {
		Timer.animationTimer = animationTimer;
	}
	

}
