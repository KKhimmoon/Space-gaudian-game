package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BigMeteorite extends Enemy {

	public BigMeteorite(int posX, int posY, int size) {
		super(posX, posY, 150);
		setSpeed(2);
		setBlood(15);
		setOwnScore(5);
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.meteorite,getPosX(),getPosY(),getSize(),getSize());
		}
	}
}
	