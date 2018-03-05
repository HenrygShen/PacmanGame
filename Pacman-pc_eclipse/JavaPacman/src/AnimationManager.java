import javafx.scene.canvas.GraphicsContext;

public class AnimationManager {

    private Animation animations[];
    private int animationIndex;

    public AnimationManager(Animation[] animations){

        this.animations = animations;
        
    }

    public void playAction(int index){

        if (!animations[animationIndex].isPlaying()){
        	animations[animationIndex].playAnimation();
        }
        else {
        	animations[animationIndex].stopAnimation();
        }
        animationIndex = index;
    }

    public void draw(GraphicsContext graphicsContext,double x,double y){

        if (animations[animationIndex].isPlaying()){
        	animations[animationIndex].draw(graphicsContext,x,y);
        }
    }

    public void update(){

        if (animations[animationIndex].isPlaying()){
        	animations[animationIndex].update();
        }
    }

}
