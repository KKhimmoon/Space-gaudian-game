package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import screenDrawing.MainGameScreen;

public class Timer extends Canvas{
	private int currentTime;
	private long lastTimeTriggered;
	public static AnimationTimer animationTimer;
	public static AnimationTimer mainGameSound;
	
	public Timer(int s) {
		super(80,40);
		setCurrentTime(s);
		setLastTimeTriggered(0);
		GraphicsContext gc = this.getGraphicsContext2D();
		setAnimationTimer(new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(getCurrentTime() < 0 ) {
					EndGameController.updateYourScore(EndGameController.getGc());
					screenDrawing.MainGameScreen.getInstance().getEndGameScene().setVisible(true);
					getAnimationTimer().stop();
				}
				
				MainGameScreen.getInstance().run();
				
				if (now - getLastTimeTriggered() >= 1000000000)
				{
					drawCurrentTimeString(gc);
					setCurrentTime(getCurrentTime()-1);
					setLastTimeTriggered(now);
				}
			}
		});
		getAnimationTimer().start();
		playMainGameSound();
	}

	public void playMainGameSound() {
		setMainGameSound(new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				if(!sharedObject.RenderableHolder.mainGameSound.isPlaying()) {
					sharedObject.RenderableHolder.mainGameSound.play();
				}
			}
		});
		getMainGameSound().start();
	}
	
	public void drawCurrentTimeString(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		String path = sharedObject.RenderableHolder.gameFontPath;
		gc.setFont(Font.loadFont(path,15));
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.drawImage(sharedObject.RenderableHolder.clock, 0, 0, 40, 40);
		gc.fillText("" + getCurrentTime(), this.getWidth()/2, this.getHeight() / 2 +12);
	}
	
	public static AnimationTimer getAnimationTimer() {
		return animationTimer;
	}
	
	public static void setAnimationTimer(AnimationTimer animationTimer) {
		Timer.animationTimer = animationTimer;
	}
	
	public static AnimationTimer getMainGameSound() {
		return mainGameSound;
	}

	public static void setMainGameSound(AnimationTimer mainGameSound) {
		Timer.mainGameSound = mainGameSound;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public long getLastTimeTriggered() {
		return lastTimeTriggered;
	}

	public void setLastTimeTriggered(long lastTimeTriggered) {
		this.lastTimeTriggered = lastTimeTriggered;
	}
	

}
