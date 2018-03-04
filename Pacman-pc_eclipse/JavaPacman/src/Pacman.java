import javafx.scene.image.Image;

public class Pacman implements GameObject{
	
	
	private static final int SPRITE_HEIGHT = 100;
	private static final int SPRITE_WIDTH = 100;
	private Rectangle hitBox;
	private double x;
	private double y;
	private Image sprite;
	
	public Pacman(Image sprite,int x,int y) {
		
		this.sprite = sprite;
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT);
		hitBox.setWidth(SPRITE_WIDTH);
		hitBox.setX(x);
		hitBox.setY(y);
		this.x = x;
		this.y = y;
		
	
	}
	
	public void update() {
		
		this.x += 10;
		this.y += 10;
		if (this.y == 900) {
			this.x = 100;
			this.y = 100;
		}
	}
	
    public Rectangle getHitBox(){
    	return this.hitBox;
    }

    public double getX() {
    	
    	return this.x;
    }
    
    public double getY() {
    	
    	return this.y;
    }
    
    public Image getImage() {
    	
    	return this.sprite;
    }
}
