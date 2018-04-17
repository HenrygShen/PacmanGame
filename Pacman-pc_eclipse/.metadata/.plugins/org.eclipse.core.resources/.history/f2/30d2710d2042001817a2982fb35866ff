package group23.pacman.model;

import group23.pacman.model.GameObject.TYPE;

/** This interface helps differentiate moving character game objects from non moving game objects */

public interface MovingCharacter {
	
	public boolean checkforQueuedAction();
	public void setDirection(char qDirection);
	public void updateDestination();
	public void reset(int x, int y);
	public char getQDirection();
	public char getDirection();
	public double getX();
	public double getY();
	public TYPE getType();
	public void setHasLeftSpawn();
	public boolean getHasLeftSpawn();
	public Rectangle getHitBox();
}
