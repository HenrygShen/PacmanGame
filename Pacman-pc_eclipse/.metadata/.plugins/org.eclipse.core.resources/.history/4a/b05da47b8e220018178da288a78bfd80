import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pacman extends GameObject{
	
	
	private static final int SPRITE_HEIGHT = 30;
	private static final int SPRITE_WIDTH = 30;
	private static final int SPEED = 3;
	private int vector;
	private AnimationManager animationManager;
	private boolean moving;
	
	public Pacman(int x,int y) {
		


		/* Set up the frame animation for the main character */
		Image leftC = new Image("leftClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image leftO = new Image("leftOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightC = new Image("rightClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightO = new Image("rightOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upC = new Image("upClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upO = new Image("upOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downC = new Image("downClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downO = new Image("downOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		
		Image[] leftMove = new Image[2];
		leftMove[0] = leftC;
		leftMove[1] = leftO;
		
		Image[] rightMove = new Image[2];
		rightMove[0] = rightC;
		rightMove[1] = rightO;
		
		Image[] upMove = new Image[2];
		upMove[0] = upC;
		upMove[1] = upO;
		
		Image[] downMove = new Image[2];
		downMove[0] = downC;
		downMove[1] = downO;
		
		Animation leftAnimation = new Animation(leftMove,0.3f);
		Animation rightAnimation = new Animation(rightMove,0.3f);
		Animation upAnimation = new Animation(upMove,0.3f);
		Animation downAnimation = new Animation(downMove,0.3f);
		
		Animation[] movementAnimations = new Animation[4];
		movementAnimations[0] = leftAnimation;
		movementAnimations[1] = rightAnimation;
		movementAnimations[2] = upAnimation;
		movementAnimations[3] = downAnimation;
		
		animationManager = new AnimationManager(movementAnimations);
		

		/* Sets up the main character's hit-box */
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT);
		hitBox.setWidth(SPRITE_WIDTH);
		hitBox.setX(x);
		hitBox.setY(y);
		this.vector = 0;
		
		moving = true;
		
	
	}
	
	public void update() {	
		
		animationManager.update();
		changeMovement();
		playAnimation();
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext,this.hitBox.getX(),this.hitBox.getY());
	}
	
	public boolean outOfBounds() {
		if (hitBox.getX() >= 1368-50 && getDirection()== 4) {
			return true;
		}
		else if (hitBox.getX() <=0 && getDirection()== 3) {
			return true;
		}
		else if (hitBox.getY() >=768-50 && getDirection()==2) {
			return true;
		}
		else if (hitBox.getY()  <=0 && getDirection()==1) {
			return true;
		}
		else return false;
	}
	
    
    public void setDirection(int vector) {

    	this.vector = vector;	
    }
    
    public int getDirection() {
    	
    	return this.vector;
    }
    
    public boolean collidedWith(GameObject object) {
    	
    	
    	Rectangle hitBox = object.getHitBox();
    	
    	return this.hitBox.intersects(hitBox);
    }
    
    public boolean collidedWithWall(Rectangle rect) {
    	
    	return this.hitBox.intersects(rect);
    }
    
    public void setMoving(boolean movement) {
    	
    	moving = movement;
    }
    
    public void changeMovement() {
    	
    	if (moving == true) {
    		/* UP */
    		if (this.vector == 1) {
				if (!outOfBounds()) {
					this.hitBox.setY((int)hitBox.getY() - SPEED);
				}
			}
			/* DOWN */
			else if (this.vector == 2) {
				if (!outOfBounds()) {
					this.hitBox.setY((int)hitBox.getY() + SPEED);
				}
			}
			/* LEFT */
			else if (this.vector == 3) {
				if (!outOfBounds()) {
					this.hitBox.setX((int)hitBox.getX() - SPEED);
				}
			}
			/* RIGHT */
			else if (this.vector == 4) {
				if (!outOfBounds()) {
					this.hitBox.setX((int)hitBox.getX() + SPEED);
				}
			}
    	}
    }
    
    public void playAnimation() {
    	if (this.vector == 0) {
			animationManager.playAction(1);
		}
		/* UP */
		if (this.vector == 1) {
			animationManager.playAction(2);
		}
		/* DOWN */
		else if (this.vector == 2) {
			animationManager.playAction(3);
		}
		/* LEFT */
		else if (this.vector == 3) {
			animationManager.playAction(0);
		}
		/* RIGHT */
		else if (this.vector == 4) {
			animationManager.playAction(1);
		}
    }
    
   public void resetPosition() {
		/* UP */
		if (this.vector == 1) {
			this.hitBox.setY((int)hitBox.getY() + SPEED);
		}
		/* DOWN */
		else if (this.vector == 2) {
			this.hitBox.setY((int)hitBox.getY() - SPEED);
		}
		/* LEFT */
		else if (this.vector == 3) {
			this.hitBox.setX((int)hitBox.getX() + SPEED);
		}
		/* RIGHT */
		else if (this.vector == 4) {
			this.hitBox.setX((int)hitBox.getX() - SPEED);
		}
    }
}
