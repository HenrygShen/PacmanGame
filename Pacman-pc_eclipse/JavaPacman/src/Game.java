import javafx.scene.image.Image;

public class Game {
	
	
	private Pacman pacman;
	
	
	public Game() {
		
		Image mainCharacter = new Image("pacman.png");
		pacman = new Pacman(mainCharacter,200,200);
		
	}
	
	public void update() {
		pacman.update();
		
	}
	
	public Pacman getPacman() {
		return this.pacman;
	}
}
