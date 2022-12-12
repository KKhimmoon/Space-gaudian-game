package logic;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BulletItem extends Item{
	private final String img;

	public BulletItem(int posX, int posY, int size) {
		super(posX, posY, size);
		this.img = "bulletitem.png";
		setSpeed(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.bulletItem,getPosX(),getPosY(),getSize(),getSize());
			setPosY(getPosY()+getSpeed());
		}
	}
}
