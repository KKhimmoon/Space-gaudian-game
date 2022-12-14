package logic;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BombItem extends Item{

	public BombItem(int posX, int posY, int size) {
		super(posX, posY, size);
		setSpeed(2);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(!isExploding()) {
			gc.drawImage(sharedObject.RenderableHolder.bombItem,getPosX(),getPosY(),getSize(),getSize());
		}
	}
}
