package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BigMeteorite extends Enemy {
	private int speed;
	private final String img;
	public BigMeteorite(int posX, int posY, int size) {
		super(posX, posY, size);
		setSpeed(2);
		this.img = "35cc4b0f4226a0f1.png";
		
		// TODO Auto-generated constructor stub
	}
	public void draw(GraphicsContext gc) {
		if(!isExploding()) {
			String image_path = ClassLoader.getSystemResource(this.img).toString();
			Image image = new Image(image_path);
			gc.drawImage(image,getPosX(),getPosY(),getSize(),getSize());
			setPosY(getPosY()+ getSpeed());
		}
	}
	public int getSpeed() {
			return speed;
	}
	public void setSpeed(int speed) {
			this.speed = speed;
	}

		
		
}
	