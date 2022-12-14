package logic;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BulletItem extends Item{
	
	public BulletItem(int posX, int posY, int size) {
		super(posX, posY, size);
		setSpeed(1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.bulletItem,getPosX(),getPosY(),getSize(),getSize());
		}
	}
}
