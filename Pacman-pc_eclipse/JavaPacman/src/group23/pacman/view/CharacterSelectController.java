package group23.pacman.view;

import java.io.File;
import group23.pacman.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/** This class allows the player(s) 2 (and 3) to select their preferred character sprite */
public class CharacterSelectController {
	
	/* View elements in CharacterSelect.fxml */
	@FXML
	private ImageView background;
	@FXML
	private ImageView ghost1;
	@FXML
	private ImageView ghost2;
	@FXML
	private ImageView ghost3;
	@FXML
	private ImageView ghost4;
	@FXML
	private ImageView player_banner;
	
	/* Constants - do not change */
	private static final int MAX_GHOSTS = 4;
	private static final int SPRITE_HEIGHT = 150;
	private static final int SPRITE_WIDTH = 150;
	
	/* Main app copy kept to use when referencing to show other views */ 
	private MainApp mainApp;
	
	
	/* Media variables for sound effects */
	private Media buttonPress;
	private MediaPlayer mediaPlayer;
	
	
	/* Keep tracks of chosen ghost */
	private int ghostIndex;
	
	
	/* Number of players - Game mode */
	private int numPlayers;
	
	
	/* Variable is used when there are 3 players, helps differentiate between the first and second character selections */
	private boolean firstPick;
	
	
	public CharacterSelectController() {
		
		
	}
	
	@FXML
	private void handleButton(KeyEvent event) {
		
		/* ENTER key confirms character selection */
		if (event.getCode() == KeyCode.ENTER) {
			
			/* Two players */
			if (numPlayers == 2) {
				
				mainApp.setPlayer2(ghostIndex);
				mainApp.showLevelSelect();
			}
			
			/* Three players */
			else if (numPlayers == 3) {
				
				/* Now allow third player to select if they haven't already */
				if (firstPick) {
					firstPick = false;
					mainApp.setPlayer2(ghostIndex);
					ghostIndex = 1;
					highlightGhost();
					player_banner.setImage(new Image("assets/Elements-CharSel/player_three_banner.png"));

				}
				else {
					mainApp.setPlayer3(ghostIndex);
					mainApp.showLevelSelect();
				}
			}
			
		}
		
		/* LEFT and RIGHT keys scroll through choose-able sprites */
		else if (event.getCode() == KeyCode.LEFT) {
			
			ghostIndex--;
			
			if (ghostIndex < 1) {
				ghostIndex = 1;
			}
			else {
				playSfx(buttonPress);
				highlightGhost();
			}
		}
		
		else if (event.getCode() == KeyCode.RIGHT) {
			
			ghostIndex++;
			
			if (ghostIndex > MAX_GHOSTS) {
				ghostIndex = MAX_GHOSTS;
			}
			else {
				playSfx(buttonPress);
				highlightGhost();
			}
		}
		
	}
	
	
	@FXML
	private void initialize() {
		
		/* Initialize the default view */
		background.setImage(new Image("bg/blackbg.png"));
		ghost1.setImage(new Image("assets/Elements-CharSel/ghost1-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		player_banner.setImage(new Image("assets/Elements-CharSel/player_two_banner.png"));
		
		/* Set up sound effect button presses */
		buttonPress = new Media(new File("bin/assets/sfx/menuSelect.mp3").toURI().toString());
		
		/* Set variable to determine which sprite is chosen for which character */
		firstPick = true;
		ghostIndex = 1;
	}
	
	
	/* Helper function for highlighting the currently selected sprite - allows user to know what they're choosing */
	private void highlightGhost() {
		
		if (ghostIndex == 1) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		}
		else if (ghostIndex == 2) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		}
		else if (ghostIndex == 3) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));	
		}
		else if (ghostIndex == 4) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		}
	}
	
	/* Plays sound effect for navigating this view */
	public void playSfx(Media sfx) {
		mediaPlayer = new MediaPlayer(sfx);
		mediaPlayer.setVolume(0.3);
		mediaPlayer.play();
	}
	
	
	/* PUBLIC SETTERS */
	public void setPlayers(int players) {
		
		this.numPlayers = players;
	}
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
}
