package entity.base;

import logic.Space;

public interface Collidable {
	public abstract boolean collide(Space other);
}
