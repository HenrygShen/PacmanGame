package group23.pacman.model;
import javafx.scene.canvas.GraphicsContext;

/**
	Game objects are any drawn objects that the characters will have interactions with on the game view */

public abstract class GameObject {
	
	
	public enum TYPE{
		
		PELLET,
		SPECIAL_PELLET,
		WALL,
		GHOST,
		PACMAN
	}
	
	
	protected TYPE type;
	protected Rectangle hitBox;
	
	
    protected Rectangle getHitBox() {
    	
    	return this.hitBox;
    }

    public double getX() {
    	
    	return this.hitBox.getX();
    }
    
    public double getY() {
    	
    	return this.hitBox.getY();
    }
    
    public void draw(GraphicsContext graphicsContext) {
    	

    }
    
    public TYPE getType() {
    	
    	return this.type;
    }

}
