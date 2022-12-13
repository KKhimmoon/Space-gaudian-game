package logic;

import javafx.animation.RotateTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SmallMeteorite extends Enemy{
	public SmallMeteorite(int posX, int posY, int size) {
		super(posX, posY, 80);
		setSpeed(3);
		setBlood(5);
		setOwnscore(2);
		
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.meteorite,getPosX(),getPosY(),getSize(),getSize());
//			RotateTransition rotate = new RotateTransition();
//	        rotate.setNode(gc);
//	        rotate.setDuration(Duration.millis(1200));
//	        rotate.setCycleCount(TranslateTransition.INDEFINITE);
//	        rotate.setInterpolator(Interpolator.LINEAR);
//	        rotate.setByAngle(360);
//	        rotate.setAxis(Rotate.Z_AXIS);
//	        rotate.play();
			setPosY(getPosY()+ getSpeed());
		}
	}
	
	
		
}