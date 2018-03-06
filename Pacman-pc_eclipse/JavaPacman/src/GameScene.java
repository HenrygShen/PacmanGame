import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage; 
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameScene {
	
	
	Stage mainStage;
	
	private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Game game;
    private ArrayList<Rectangle> walls;

	public GameScene(Stage mainStage) {
		
		this.mainStage = mainStage;
        
		root = new Group();
		scene = new Scene(root);
	    canvas = new Canvas( 1366, 768 );
	    ImageView iv = new ImageView(new Image("mapOne.png"));
	    root.getChildren().add(iv);
	    root.getChildren().add(canvas);
	    graphicsContext = canvas.getGraphicsContext2D();
	    
	    String line = null;
	    String[] array;
	    Rectangle rect = new Rectangle();
	    walls = new ArrayList<Rectangle>();
	    
		try {
			
			// Always wrap FileReader in BufferedReader.
			FileReader fileReader = new FileReader("map.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null ) {
				array = line.split(",");
				rect.setX(Integer.parseInt(array[0]));
				rect.setY(Integer.parseInt(array[1]));
				rect.setWidth(Integer.parseInt(array[2]));
				rect.setHeight(Integer.parseInt(array[3]));
				walls.add(rect);
			}

			// Always close files.
			bufferedReader.close();
		} 
		
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '");
		}

		catch (IOException ex) {
			System.out.println("Error reading file '");
		}
	

	    scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
	    	@Override
	    	public void handle(KeyEvent e) {
	    	/* switch to switch statements later */
	    	
		    	if (e.getCode() == KeyCode.UP) {
		    		game.getPacman().setDirection(1);
		    	}
		    	else if (e.getCode() == KeyCode.DOWN) {
		    		game.getPacman().setDirection(2);
		    	}
		    	else if (e.getCode() == KeyCode.LEFT) {
		    		game.getPacman().setDirection(3);
		    	}
		    	else if (e.getCode() == KeyCode.RIGHT) {
		    		game.getPacman().setDirection(4);
		    	}
	    	}
	    });
	    
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
			 	
		        public void handle(long time) {
		        	
		        	graphicsContext.clearRect(0, 0, 1366, 768);
		        	game.update();
		        	draw(graphicsContext);

		        }
		    }.start();
		
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		game.getPacman().draw(graphicsContext);
		game.getGhost().draw(graphicsContext);
		game.drawPellets(graphicsContext);
	}
	


	
	
	
}
