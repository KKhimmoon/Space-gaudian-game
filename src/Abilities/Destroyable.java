package Abilities;

public interface Destroyable {
	public boolean isValid(); //check if this object is valid (invalid when blood <= 0)
	public void updateBlood();//update blood of this object in realtime and update score if this blood <= 0 and update this to invalid
}
