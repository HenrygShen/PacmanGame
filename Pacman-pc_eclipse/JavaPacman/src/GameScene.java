import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage; 
import javafx.scene.image.*;

public class GameScene {
	
	
	Stage mainStage;
	
	private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private Game game;
    
    int value = 50;

	public GameScene(Stage mainStage) {
		
		this.mainStage = mainStage;
        
		root = new Group();
		scene = new Scene(root);
	    canvas = new Canvas( 1440, 900 );
	    ImageView iv = new ImageView(new Image("background-main.png"));
	    root.getChildren().add(iv);
	    root.getChildren().add(canvas);
	    
	    gc = canvas.getGraphicsContext2D();

	    mainStage.setScene(scene);
	    mainStage.show();

	}
	
	public void setGameMode(int gameType) {
		
		if (gameType == 1) {
			game = new Game();
		}
		
		
	}
	
	
	public void start() {
		
		 new AnimationTimer() {
			 	
		        public void handle(long currentNanoTime)
		        {
		        	
		        	gc.clearRect(0, 0, 1440, 900);

		        	game.update();

		            gc.drawImage(game.getPacman().getImage() , game.getPacman().getX(), game.getPacman().getY() );

		        }
		    }.start();
		
	}
	


	
	
	
}
