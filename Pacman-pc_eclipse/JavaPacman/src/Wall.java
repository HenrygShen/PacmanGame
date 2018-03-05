public class Wall {
	
	private static final int WALL_THICKNESS = 5;
	private Rectangle wallHitBox;
	//private Image sprite;
	private int x;
	private int y;
	private int direction;
	private int length;
	
	public Wall(int x, int y,int direction,int length) {
		
		//this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.length = length;
		wallHitBox = new Rectangle();
		/* Direction = 1 means the wall is vertical
		 * Direction = 2 means the wall is horizontal 
		 */
		if (direction == 1) {
			wallHitBox.setWidth(WALL_THICKNESS);
			wallHitBox.setHeight(length);
		}
		else {
			wallHitBox.setWidth(length);
			wallHitBox.setHeight(WALL_THICKNESS);	
		}
		wallHitBox.setX(x);
		wallHitBox.setY(y);
		
		
		
	}

	
	public int getX() {
		
		return this.x;
	}
	
	public int getY() {
		
		return this.y;
	}
	
	
	public int getHeight() {
		if (direction == 1) {
			return length;
		}
		else {
			return WALL_THICKNESS;
		}
	}
	
	public int getWidth() {
		if (direction == 1) {
			return WALL_THICKNESS;
		}
		else {
			return length;
		}
	}
	
	public Rectangle getHitBox() {
		
		return this.wallHitBox;
	}
	
	
}