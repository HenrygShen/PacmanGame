package group23.pacman.model;
import javafx.scene.canvas.GraphicsContext;

/** Game objects are any drawn objects that the characters will have interactions with */

public abstract class GameObject {
	
	
	public enum TYPE{
		
		PELLET,
		SPECIAL_PELLET,
		WALL,
		GHOST,
		PACMAN
	}
	
	protected int x;
	protected int y;
	protected TYPE type;
	protected Rectangle hitBox;
	
	
    protected Rectangle getHitBox() {
    	
    	return this.hitBox;
    }

    public double getX() {
    	
    	return this.x;
    }
    
    public double getY() {
    	
    	return this.y;
    }
    
    public void draw(GraphicsContext graphicsContext) {
    	

    }
    
    public TYPE getType() {
    	
    	return this.type;
    }

}
