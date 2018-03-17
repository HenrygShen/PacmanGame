package group23.pacman.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends GameObject{
	
	private Rectangle wallHitBox;
	private Image mapBlock;
	private double x;
	private double y;
	public Wall(Rectangle rectangle) {
		
		this.wallHitBox = rectangle;
		this.mapBlock = new Image("assets/mapBlock2.png",10,10,false,false);
		this.type = GameObject.TYPE.WALL;
		this.x = wallHitBox.getX();
		this.y = wallHitBox.getY();

	}

	
	public double getX() {
		
		return this.wallHitBox.getX();
	}
	
	public double getY() {
		
		return this.wallHitBox.getY();
	}
	
	
	public Rectangle getHitBox() {
		 
		return this.wallHitBox;
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		graphicsContext.drawImage(mapBlock,x,y);

	}
	
	
}