import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class Game {
	
	private static final int TILE_SIZE = 10;
	private Pacman pacman;
	private Ghost ghost;
	
	private ArrayList<Pellet> pellets;
	private ArrayList<Wall> walls;
	private ArrayList<GameObject> objects;
	
	
	public Game() {
		
		pacman = new Pacman(30,300);
		ghost = new Ghost(300,334);
		pellets = new ArrayList<Pellet>();
		objects = new ArrayList<GameObject>();
		
		String line = null;

	    walls = new ArrayList<Wall>();
		
	    
		
		try {
			
			// Always wrap FileReader in BufferedReader.
			FileReader fileReader = new FileReader("map1.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int row =0;
			while ((line = bufferedReader.readLine()) != null ) {
				for (int i =0;i< line.length();i++) {
					if (line.charAt(i)==('w')) {
						Rectangle rect = new Rectangle();
						rect.setX(i*TILE_SIZE );
						rect.setY(row*TILE_SIZE );
						rect.setWidth(TILE_SIZE);
						rect.setHeight(TILE_SIZE);
						Wall wall = new Wall(rect);
						walls.add(wall);
						objects.add(wall);
					}
					else if (line.charAt(i) == 'p') {
						Pellet pellet = new Pellet(i*TILE_SIZE,row*TILE_SIZE);
						pellets.add(pellet);
						objects.add(pellet);
					}
				}
				row++;
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
		
	}
	
	public void update() {

		pacman.update();
		checkCollisions();
	}
	
	private void checkCollisions() {
		
		int count = 0;
		
		for (GameObject object : objects) {
		
			if (pacman.collidedWith(object)) {
				if (object.getType() == GameObject.TYPE.PELLET) {
					object.setInvisible();
				}
				else if (object.getType() == GameObject.TYPE.WALL) {
					pacman.setMoving(false);
					pacman.resetPosition();
					count++;
				}
			}

		}
		
		if (count == 0) {
			pacman.setMoving(true);
		}
	}
	
	

	public Pacman getPacman() {
		
		return this.pacman;
	}
	
	public Ghost getGhost() {
		
		return this.ghost;
	}
	
	public void drawWalls(GraphicsContext graphicsContext) {
		
		for (Wall w : walls) {
			w.draw(graphicsContext);
		}
	}
	
	public void drawPellets(GraphicsContext graphicsContext) {
		
		for (Pellet p : pellets) {
			p.draw(graphicsContext);
		}
	}

}
