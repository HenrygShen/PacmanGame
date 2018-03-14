import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
	
	
	enum TYPE{
		PELLET,
		WALL,
		GHOST
	}
	
	
	protected TYPE type;
	protected Rectangle hitBox;
	
	
    protected Rectangle getHitBox() {
    	
    	return this.hitBox;
    }

    protected double getX() {
    	
    	return this.hitBox.getX();
    }
    
    protected double getY() {
    	
    	return this.hitBox.getY();
    }
    
    protected void setInvisible() {
    	
    }
    
    protected void draw(GraphicsContext graphicsContext) {
    	
    }
    
    protected TYPE getType() {
    	
    	return this.type;
    }
}