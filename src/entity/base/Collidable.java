package entity.base;

import logic.Rocket;

public interface Collidable {
	public abstract boolean collide(Rocket other);
}
