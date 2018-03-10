import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pacman extends GameObject{
	
	
	private static final int SPRITE_HEIGHT = 30;
	private static final int SPRITE_WIDTH = 30;
	private static final int SPEED = 3
			;
	private int vector;
	private AnimationManager animationManager;
	private boolean moving;
	private int crashCount;
	
	private int queuedDirection;
	
	//private Controls controller;
	private Rectangle theoreticalHitBox;
	public Pacman(int x,int y) {
		


		/* Set up the frame animation for the main character */
		Image leftC = new Image("assets\\Pacman\\leftClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image leftO = new Image("assets\\Pacman\\leftOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightC = new Image("assets\\Pacman\\rightClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightO = new Image("assets\\Pacman\\rightOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upC = new Image("assets\\Pacman\\upClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upO = new Image("assets\\Pacman\\upOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downC = new Image("assets\\Pacman\\downClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downO = new Image("assets\\Pacman\\downOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		
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
		theoreticalHitBox = new Rectangle();
		theoreticalHitBox.setHeight(SPRITE_HEIGHT);
		theoreticalHitBox.setWidth(SPRITE_WIDTH);
		theoreticalHitBox.setX(x);
		theoreticalHitBox.setY(y);
		
		this.vector = 0;
		
		moving = true;
		crashCount = 0;
		
	
	}
	
	public void update() {	
		
		animationManager.update();
		//changeMovement();
		playAnimation();
	}
	
	public void queueMovement(int queuedDirection) {
		
		this.queuedDirection = queuedDirection;
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext,this.hitBox.getX(),this.hitBox.getY());
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
    
    public void setMoving(boolean movement) {
    	
    	moving = movement;
    }
    
    public boolean checkforQueuedAction() {
    	
    	if (queuedDirection != vector) {
    		return true;
    	}
    	return false;
    }
    
    public void checkQueuedMovement(GameObject object) {
    	
    	if (this.theoreticalHitBox.intersects(object.getHitBox())) {
    		//CHANGE LATER
    		crashCount++;
    	}
    }
    
    public int getCrashCount() {
    	return this.crashCount;
    }
    
    public void setCrashCount() {
    	this.crashCount = 0;
    }
    
    public void setNewMove() {
    	this.hitBox.setY((int)theoreticalHitBox.getY());
    	this.hitBox.setX((int)theoreticalHitBox.getX());
    	setDirection(queuedDirection);
    }
    
    public void changeMovement() {
    	
    	this.theoreticalHitBox.setY((int)hitBox.getY());
    	this.theoreticalHitBox.setX((int)hitBox.getX());
    		
    		switch (queuedDirection) {
    			/* UP */
    			case 1:
    				this.theoreticalHitBox.setY((int)theoreticalHitBox.getY() - 3 );
    			/* DOWN */
    			case 2:
    				this.theoreticalHitBox.setY((int)theoreticalHitBox.getY() + 3);
    			/* LEFT */
    			case 3:
    				this.theoreticalHitBox.setX((int)theoreticalHitBox.getX() - 3 );
    			/* RIGHT */
    			case 4:
    				this.theoreticalHitBox.setX((int)theoreticalHitBox.getX() + 3);
    		}
    	
    	if (moving == true) {
    		
    		/* UP */
    		if (this.vector == 1) {
				this.hitBox.setY((int)hitBox.getY() - SPEED);
			}
			/* DOWN */
			else if (this.vector == 2) {
				this.hitBox.setY((int)hitBox.getY() + SPEED);
			}
			/* LEFT */
			else if (this.vector == 3) {
				this.hitBox.setX((int)hitBox.getX() - SPEED);
			}
			/* RIGHT */
			else if (this.vector == 4) {
				this.hitBox.setX((int)hitBox.getX() + SPEED);
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
