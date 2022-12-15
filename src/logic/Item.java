package logic;

public class Item extends Space{
	private int speed;

	public Item(int posX, int posY, int size) {
		super(posX, posY, size);
		// TODO Auto-generated constructor stub
		setSpeed(speed);
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void update() {
		super.update();
		if(!isDestroyed()) {
			setPosY(getPosY()+ getSpeed());
		}
		if(getPosY() > GameLogic.HEIGHT) setDestroyed(true); 
	}
}
