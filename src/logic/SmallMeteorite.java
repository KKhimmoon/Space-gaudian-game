package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SmallMeteorite extends Enemy{
	private final String img;
	public SmallMeteorite(int posX, int posY, int size) {
		super(posX, posY, 6);
		this.img = "35cc4b0f4226a0f1.png";
		setSpeed(5);
		setBlood(5);
		
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
	
		
}