import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game {
	
	private static final int TILE_SIZE = 10;
	private Pacman pacman;
	private Ghost ghost;
	private Media chompNoise;
	private MediaPlayer mediaPlayer;
	
	private ArrayList<GameObject> objects;
	
	
	public Game() {
		
		pacman = new Pacman(800,300);
		ghost = new Ghost(300,334);
		objects = new ArrayList<GameObject>();
		chompNoise = new Media(new File("bin\\assets\\sfx\\chompNoise.mp3").toURI().toString());
		
		String line = null;

	
		try {
			
			// Always wrap FileReader in BufferedReader.
			FileReader fileReader = new FileReader("testmap.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int row =0;
			int position = 0;
			while ((line = bufferedReader.readLine()) != null ) {
				position = 0;
				for (int i =0;i< line.length();i++) {
					if (line.charAt(i)==('0')) {
						Rectangle rect = new Rectangle();
						rect.setX(position*TILE_SIZE + 33);
						rect.setY(row*TILE_SIZE + 34);
						rect.setWidth(TILE_SIZE );
						rect.setHeight(TILE_SIZE);
						Wall wall = new Wall(rect);
						objects.add(wall);
						position++;
					}
					else if (line.charAt(i) == 'P') {
						Pellet pellet = new Pellet(position*TILE_SIZE + 33,row*TILE_SIZE + 34);
						objects.add(pellet);
						position++;
					}
					else if (line.charAt(i) == '1' ) {
						position++;
					}
					else {
						
					}
				}
				row++;
			}
			bufferedReader.close();
		} 
		
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file ");
		}

		catch (IOException ex) {
			System.out.println("Error reading file ");
		}
		
	}
	
	public void update() {
		
		pacman.changeMovement();
		checkCollisions();
		pacman.update();
		//pacman.checkforQueuedAction(objects);
		ghost.update();
		
	}
	
	private void checkCollisions() {
		
		boolean crashed = false;
		
		for (GameObject object : objects) {
			if (pacman.checkforQueuedAction() && (object.getType() == GameObject.TYPE.WALL)) {
				pacman.checkQueuedMovement(object);
			}
		}
		
		if((pacman.getCrashCount() == 0) && (pacman.checkforQueuedAction())) {
			pacman.setNewMove();
			pacman.setCrashCount();
			return;
		}
		pacman.setCrashCount();
		
		for (GameObject object : objects) {

			if (pacman.collidedWith(object)) {
				if (object.getType() == GameObject.TYPE.PELLET) {
					playSfx(chompNoise);
					objects.remove(object);
					break;
				}
				else if (object.getType() == GameObject.TYPE.WALL) {
					pacman.setMoving(false);
					pacman.resetPosition();
					crashed = true;
					break;
				}
			}
		}
		
		if (!crashed) {
			pacman.setMoving(true);
		}
	}
	
	

	public Pacman getPacman() {
		
		return this.pacman;
	}
	
	public Ghost getGhost() {
		
		return this.ghost;
	}
	
	public void drawObjects(GraphicsContext graphicsContext) {
		
		for (GameObject object : objects) {
			object.draw(graphicsContext);
		}
	}
	
	public void playSfx(Media sfx) {
		mediaPlayer = new MediaPlayer(sfx);
		mediaPlayer.setVolume(0.3);
		mediaPlayer.play();
	}


}
