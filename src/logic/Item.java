package logic;

public class Item extends Rocket{
	private int speed;

	public Item(int posX, int posY, int size) {
		super(posX, posY, size);
		// TODO Auto-generated constructor stub
		setSpeed(2);
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
		if(getPosY() > 600) setDestroyed(true); 
	}
	
}
