package group23.pacman.model;

import group23.pacman.view.Animation;
import group23.pacman.view.AnimationManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Ghost extends GameObject {
	
	private static final int SPRITE_HEIGHT = 30;
	private static final int SPRITE_WIDTH = 30;
	private AnimationManager animationManager;
	
	
	public Ghost(int x,int y) {
		
		
		Image ghostOpen = new Image("assets/Ghost/tempGhostOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image ghostClosed = new Image("assets/Ghost/tempGhostClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image[] frames = new Image[2];
		frames[0] = ghostOpen;
		frames[1] = ghostClosed;
		
		
		Animation animation = new Animation(frames,0.3f);
		Animation[] animations = new Animation[1];
		animations[0] = animation;
		
		animationManager = new AnimationManager(animations);
		
		hitBox = new Rectangle();
		this.hitBox.setX(x);
		this.hitBox.setY(y);
		this.hitBox.setHeight(SPRITE_HEIGHT);
		this.hitBox.setWidth(SPRITE_WIDTH);
		this.type = GameObject.TYPE.GHOST;
		
	}
	
	public void update() {
		
		animationManager.update();
		animationManager.playAction(0);
		
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext,this.getHitBox().getX(),this.hitBox.getY());
	}
	
	
	
}
