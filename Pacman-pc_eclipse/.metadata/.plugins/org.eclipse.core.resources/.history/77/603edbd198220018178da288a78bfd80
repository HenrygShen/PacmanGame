import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pellet extends GameObject {
	
	private static final int SPRITE_HEIGHT = 10;
	private static final int SPRITE_WIDTH = 10;
	
	
	private Image image;
	private boolean shouldDraw;
	int x;
	int y;
	
	
	public Pellet(int x,int y) {
		
		image = new Image("tempPellet.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		
		
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT);
		hitBox.setWidth(SPRITE_WIDTH);
		hitBox.setX(x);
		hitBox.setY(y);
		this.x = x;
		this.y = y;
		this.type = GameObject.TYPE.PELLET;
		shouldDraw = true;
		
		
	}
	
	public void draw(GraphicsContext graphicsContext) {
		if (shouldDraw) {
			graphicsContext.drawImage(image, x,y);
		}
	}
	
	public void setInvisible() {
		this.shouldDraw = false;
	}

}
