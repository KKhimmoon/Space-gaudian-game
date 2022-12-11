package logic;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BulletItem extends Item{
	private final String img;

	public BulletItem(int posX, int posY, int size) {
		super(posX, posY, size);
		
		this.img = "shield.img";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(isExploding()) {
//			gc.drawImage(SpaceInvaders.EXPLOSION_IMG, getExplosionStep() % SpaceInvaders.EXPLOSION_COL * SpaceInvaders.EXPLOSION_W,
//					(getExplosionStep()/SpaceInvaders.EXPLOSION_ROWS)*SpaceInvaders.EXPLOSION_H +1,SpaceInvaders.EXPLOSION_W,
//					SpaceInvaders.EXPLOSION_H,getPosX(),getPosY(),getSize(),getSize());
		}else {
			String image_path = ClassLoader.getSystemResource(this.img).toString();
			Image image = new Image(image_path);
			gc.drawImage(image,getPosX(),getPosY(),getSize(),getSize());
			setPosY(getPosY()+5);
		}
	}
}
