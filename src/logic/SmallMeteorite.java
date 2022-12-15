package logic;

import javafx.animation.RotateTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SmallMeteorite extends Enemy{
	public SmallMeteorite(int posX, int posY, int size) {
		super(posX, posY, 80);
		setSpeed(3);
		setBlood(5);
		setOwnScore(2);
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.meteorite,getPosX(),getPosY(),getSize(),getSize());
		}
	}	
}