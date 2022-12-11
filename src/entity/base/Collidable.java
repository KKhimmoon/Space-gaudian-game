package entity.base;

import logic.Rocket;

public interface Collidable {
	public abstract boolean colide(Rocket other);
}
