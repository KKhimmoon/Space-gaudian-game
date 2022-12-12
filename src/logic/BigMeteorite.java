package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BigMeteorite extends Enemy {
	private final String img;
	public BigMeteorite(int posX, int posY, int size) {
		super(posX, posY, 120);
		setSpeed(2);
		setBlood(15);
		setOwnscore(5);
		this.img = "35cc4b0f4226a0f1.png";
		
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.meteorite,getPosX(),getPosY(),getSize(),getSize());
			setPosY(getPosY()+ getSpeed());
		}
	}

		
		
}
	