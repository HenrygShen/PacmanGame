import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;;

public class GameThread extends Thread {
	
	private Stage stage;
	private Scene scene;
	private GameScene gameScene;
	private GraphicsContext graphicsContext;
	private Image sprite;
	private boolean running;

	int value;

	public GameThread(GameScene gameScene,Stage stage,GraphicsContext graphicsContext,Scene scene,Image sprite) {
		
		/* Set these variables to reference */
		this.gameScene = gameScene;
		this.graphicsContext = graphicsContext;
		this.sprite = sprite;
		this.stage = stage;
		value = 50;	
		this.running = false;

	}
	
	
	@Override
	public void run()  {

		while (this.running) {

			graphicsContext.clearRect(0, 0, 1440, 900);

	    	value++;
	    	if (value>=1400) {
	    		value = 100;
	    	}

	    	graphicsContext.drawImage( sprite, value, value );
	    	stage.setScene(scene);
	    	
	    	}
	}
	    

	public void setRunning(boolean running) {

		this.running= running;
		
	}
	
	
}