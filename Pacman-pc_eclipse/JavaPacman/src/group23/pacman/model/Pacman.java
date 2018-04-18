package group23.pacman.model;


import group23.pacman.view.Animation;
import group23.pacman.view.AnimationManager;

import java.io.File;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/** This class handles the Pacman object, the main character of the game. **/
public class Pacman extends GameObject implements MovingCharacter {
	
	public enum STATE {
		POWER_UP,
		DEAD,
		ALIVE,
		DEATH_ANIMATION
	}
	
	
	/* Constants - do not change */
	private final int SPRITE_HEIGHT = 30;
	private final int SPRITE_WIDTH = 30;
	private final int OFFSET = 10;
	private final int SPEED = 2;
	private final int NUM_LIVES = 3;

	
	/* Direction to move and planned direction */
	private char vector;
	private char queuedDirection;
	
	/* Media variables for sound effects */
	private Media whipSound;
	private Media chompNoise;
	private MediaPlayer mediaPlayerWhip;
	private MediaPlayer mediaPlayerChomp;

	/* Handles the animations */
	private AnimationManager animationManager;
	
	/* Pacman's state */
	private STATE state;
	
	/* Condition for moving in the ghost spawn point */
	private boolean hasLeftSpawn;
	
	/* Pacman's Whip */
	private Whip whip;
	
	/* Pacman's remaining lives */
	private int lives;
	
	private int blinksPlayed;
	
	
	
	public Pacman(int x,int y,Board board) {

		setUpAnimations();
		
		type = GameObject.TYPE.PACMAN;
		state = STATE.ALIVE;
		
		/* Set up sound effect for Pacman using a whip */
		whipSound = new Media(new File("bin/assets/sfx/whipSound.mp3").toURI().toString());
		mediaPlayerWhip = new MediaPlayer(whipSound);
		mediaPlayerWhip.setVolume(0.3);
		
		/* Set up sound effect for Pacman eating the pellet */
		chompNoise = new Media(new File("bin/assets/sfx/chompNoise.wav").toURI().toString());
		mediaPlayerChomp = new MediaPlayer(chompNoise);
		mediaPlayerChomp.setVolume(0.3);
		

		/* Sets up the main character's hit-box */
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT - OFFSET);
		hitBox.setWidth(SPRITE_WIDTH - OFFSET);
		hitBox.setX(x + OFFSET/2);
		hitBox.setY(y + OFFSET/2);
		
		blinksPlayed = 0;
		hasLeftSpawn = true;
		
		/* Set up main character's position */
		this.x = x;
		this.y = y;

		lives = NUM_LIVES;
		
		/* Creates whip object that Pacman will use */
		this.whip = new Whip(this);
		
		/* Character does not initially move*/
		this.vector = 'S';
		this.queuedDirection = 'S';
	
	}

	
	public void whip() {
		
		/* Can only consume charge after whip has finished previous animation */
		if (!whip.inAnimation()) {
			if (whip.getCharges() > 0) {
				this.state = STATE.POWER_UP;
				playSfx(0);
				whip.useCharge();
			}
		}
	}

	
	public void update() {	
		
		/* Update whip state only if in whipping state */
		if (state == STATE.POWER_UP) {
			whip.update(this.x,this.y);
		}
		
		animationManager.update();
		
		if (this.state == STATE.DEATH_ANIMATION) {
			if (animationManager.getFrameIndex() == 1) {
				blinksPlayed++;
			}
		}
		if (blinksPlayed == 50) {
			blinksPlayed = 0;
			this.state = STATE.DEAD;
			lives--;
		}
		playAnimation();
	}

	
	/* Checks for collisions */
    public boolean collidedWith(GameObject object) {
    	
    	
    	Rectangle hitBox = object.getHitBox();
    	
    	
    	if (object.getType() == GameObject.TYPE.PELLET || object.getType() == GameObject.TYPE.SPECIAL_PELLET) {
    		if (this.hitBox.intersects(hitBox)) {
    			playSfx(1);
    		}
    	}
    	
    	
    	return this.hitBox.intersects(hitBox);
    }
    
    
    /* Method called when time runs out or Pacman runs into a ghost while not in the powered up state */
    public void playDeathAnim() {
    	
    	this.whip.endAnim();
    	this.state = STATE.DEATH_ANIMATION;
    	
    }

    
    /* End Pacman whipping state */
    public void endWhipAnim() {
    	
    	this.state = STATE.ALIVE;
    }
    
    
    /* Determines if the current direction is the same as the queued direction */
    public boolean checkforQueuedAction() {
    		
	    return (queuedDirection != vector);
    }
    
    
    /* Determines if the current direction is the opposite direction of the queued direction */
    public boolean oppositeDirection() {
    	
    	switch (vector) {
    		case 'S':
    			return true;
			case 'U':
				if (queuedDirection == 'D') {
					return true;
				}
			case 'D':
				if (queuedDirection == 'U') {
					return true;
				}
			case 'L':
				if (queuedDirection == 'R') {
					return true;
				}
			case 'R':
				if (queuedDirection == 'L') {
					return true;
				}
    	}
    	return false;
    }
        
    
    /* Updates Pacman's x,y coordinates depending on it's direction */
    public void updateDestination() {
    
    	if (this.state != STATE.DEATH_ANIMATION) {
	    	if (this.vector == 'U') {
				this.hitBox.setY((int)hitBox.getY() - SPEED);
				this.y = y - SPEED;
			}
			else if (this.vector == 'D') {
				this.hitBox.setY((int)hitBox.getY() + SPEED);
				this.y = y + SPEED;
			}
			else if (this.vector == 'L') {
				this.hitBox.setX((int)hitBox.getX() - SPEED);
				this.x = x - SPEED;
			}
			else if (this.vector == 'R') {
				this.hitBox.setX((int)hitBox.getX() + SPEED);
				this.x = x + SPEED;
			}
    	}
    }
    
    
    /* Plays Pacman's sound effects */
	private void playSfx(int type) {
		/* Type 0 = Whip sound */
		if (type == 0){
			mediaPlayerWhip.setStartTime(Duration.ZERO);
			mediaPlayerWhip.seek(Duration.ZERO);
			mediaPlayerWhip.play();
		}
		/* Type 1 = Chomp noise*/
		else if (type == 1){
			mediaPlayerChomp.setStartTime(Duration.ZERO);
			mediaPlayerChomp.seek(Duration.ZERO);
			mediaPlayerChomp.play();
		}
	}
	
	
    /* Resets Pacman's position when Pacman dies and still has lives left. */
	public void reset(int x, int y) {
		
		this.hitBox.setX(x + OFFSET/2);
		this.hitBox.setY(y + OFFSET/2);
		this.x = x;
		this.y = y;
		this.hasLeftSpawn = true;
		setDirection('S');
		queueMovement('S');
		animationManager.playAction(1);
		setState(Pacman.STATE.ALIVE);
	}
	
	
    /* Changes character animation depending on the direction it's currently facing */
    public void playAnimation() {
    	
    	if (this.vector == 'S') {
			animationManager.playAction(1);
		}
		if (this.vector == 'U') {
			animationManager.playAction(2);
		}
		else if (this.vector == 'D') {
			animationManager.playAction(3);
		}
		else if (this.vector == 'L') {
			animationManager.playAction(0);
		}
		else if (this.vector == 'R') {
			animationManager.playAction(1);
		}
		
		if (this.state == STATE.DEATH_ANIMATION) {
			switch (this.vector) {
				case 'L' :
					animationManager.playAction(4);
					break;
				case 'R' :
					animationManager.playAction(5);
					break;
				case 'U' :
					animationManager.playAction(6);
					break;
				case 'D' :
					animationManager.playAction(7);
					break;
				case 'S' :
					animationManager.playAction(5);
					break;
			}
			
		}
    }
    
    
    /* Public draw method */
	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext,this.x,this.y);
		
	}
    
	
	/* Set up the frame animation for the main character */
	private void setUpAnimations() {

		Image leftC = new Image("assets/Pacman/leftClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image leftO = new Image("assets/Pacman/leftOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightC = new Image("assets/Pacman/rightClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightO = new Image("assets/Pacman/rightOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upC = new Image("assets/Pacman/upClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upO = new Image("assets/Pacman/upOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downC = new Image("assets/Pacman/downClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downO = new Image("assets/Pacman/downOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		
		Image blink = new Image("assets/misc/empty.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		
		
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
		
		Image[] leftDeath = new Image[2];
		leftDeath[0] = leftO;
		leftDeath[1] = blink;
		
		Image[] rightDeath = new Image[2];
		rightDeath[0] = rightO;
		rightDeath[1] = blink;
		
		Image[] upDeath = new Image[2];
		upDeath[0] = upO;
		upDeath[1] = blink;
		
		Image[] downDeath = new Image[2];
		downDeath[0] = downO;
		downDeath[1] = blink;
		
		
		
		Animation leftAnimation = new Animation(leftMove,0.3f);
		Animation rightAnimation = new Animation(rightMove,0.3f);
		Animation upAnimation = new Animation(upMove,0.3f);
		Animation downAnimation = new Animation(downMove,0.3f);
		
		Animation leftDeathAnimation = new Animation(leftDeath,0.1f);
		Animation rightDeathAnimation = new Animation(rightDeath,0.1f);
		Animation upDeathAnimation = new Animation(upDeath,0.1f);
		Animation downDeathAnimation = new Animation(downDeath,0.1f);
		
		Animation[] movementAnimations = new Animation[8];
		movementAnimations[0] = leftAnimation;
		movementAnimations[1] = rightAnimation;
		movementAnimations[2] = upAnimation;
		movementAnimations[3] = downAnimation;
		movementAnimations[4] = leftDeathAnimation;
		movementAnimations[5] = rightDeathAnimation;
		movementAnimations[6] = upDeathAnimation;
		movementAnimations[7] = downDeathAnimation;
		
		animationManager = new AnimationManager(movementAnimations);
		
	}
	
	/* PUBLIC GETTERS AND SETTERS BELOW */
	 public char getDirection() {
		 
		 return this.vector;
	 }
	    
	 public char getQDirection() {
		 
		return this.queuedDirection;
	}
	    
	 public STATE getState() {
		 
		 return this.state;
	 }

	 public int getLives() {
		 
		 return this.lives;
	 }
	 
	 public void setLives(int lives) {
		 
		 this.lives = lives;
	 }
	    
	 public double getX() {
		 
		 return this.x;
	 }
	    
	 public double getY() {
		 
		 return this.y;
	 }
	 
	 public Whip getWhip() {
		 
		 return this.whip;
	 }
	 
	 public boolean getHasLeftSpawn() {
		 
		 return this.hasLeftSpawn;
	 }
	    
	 public Rectangle getHitBox() {
		 
		 return this.hitBox;
	 }
	 
	 public void setState(STATE state) {
		 
		 this.state = state;
	 }
	    
	 public void setHasLeftSpawn() {
		 
		 this.hasLeftSpawn = true;
	 }
	    
	 public void queueMovement(char queuedDirection) {
		 if (this.state != STATE.DEATH_ANIMATION) {
			 this.queuedDirection = queuedDirection;
		 }
	 }

	 public void setDirection(char vector) {
		 
		 this.vector = vector;	
	 }
}
