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
		this.running = false;

	}
	
	
	@Override
	public void run()  {

		while (this.running) {

			
	    	}
	}
	    

	public void setRunning(boolean running) {

		this.running= running;
		
	}
	
	
}
