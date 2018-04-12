package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
	The controller class for the welcome screen view.
 */
public class WelcomeScreenController {
	
	private static final int BUTTON_WIDTH = 300;
	private static final int BUTTON_HEIGHT = 50;
	
	private MainApp mainApp;
	
	private int buttonIndex;
	private int numPlayers;
	private boolean playSelected;
	
	@FXML 
	private ImageView playBtnImage;
	
	@FXML
	private Button button;
	
	@FXML 
	private ImageView tutorialBtnImage;
	
	@FXML
	private ImageView singlePlayerImage;
	@FXML
	private ImageView twoPlayerImage;
	@FXML
	private ImageView threePlayerImage;
	
	
	@FXML
	private ImageView exitBtnImage;
	
	
	@FXML
	private ImageView background;
	
	
	public WelcomeScreenController() {
		
		
	}
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleButton(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			if (playSelected) {
				
				mainApp.setPlayers(numPlayers);
				if (numPlayers == 1) {
					mainApp.showLevelSelect();
				}
				else {
					mainApp.showCharacterSelect();
				}
			}
			else if (buttonIndex == 0) {
				
				playSelected = true;
				numPlayers = 1;
				
				
				Image singlePlayer = new Image("assets/buttons/singlePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
				singlePlayerImage.setImage(singlePlayer);
				
				Image twoPlayer = new Image("assets/buttons/twoPlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
				twoPlayerImage.setImage(twoPlayer);
				
				Image threePlayer = new Image("assets/buttons/ThreePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
				threePlayerImage.setImage(threePlayer);
				
				highlightPlayers(numPlayers);
				}
			else if (buttonIndex == 1) {
				mainApp.showHelp();
			}
			else if (buttonIndex == 2) {
				Platform.exit();
			}
		}
		
		/* When scrolling */
		else if (event.getCode() == KeyCode.UP) {
			if (playSelected) {
				numPlayers--;
				numPlayers = (numPlayers < 1) ? 1 : numPlayers;
				highlightPlayers(numPlayers);
			}
		
			else {
				buttonIndex--;
				buttonIndex = (buttonIndex < 0 ) ? 0 : buttonIndex;
				highlightButton(buttonIndex);
			}
			System.out.println("Button Index " + buttonIndex);
			System.out.println("Number of players " + numPlayers + "\n");
		}
		
		else if (event.getCode() == KeyCode.DOWN) {
			if (playSelected) {
				numPlayers++;
				numPlayers = (numPlayers > 3) ? 3 : numPlayers;
				highlightPlayers(numPlayers);
			}
		
			else {
				buttonIndex++;
				buttonIndex = (buttonIndex > 2 ) ? 2 : buttonIndex;
				highlightButton(buttonIndex);
			}
			System.out.println("Button Index " + buttonIndex);
			System.out.println("Number of players " + numPlayers + "\n");
		}
		else if (event.getCode() == KeyCode.ESCAPE) {
			if (playSelected) {
				singlePlayerImage.setImage(new Image("assets/misc/empty.png"));
				twoPlayerImage.setImage(new Image("assets/misc/empty.png"));
				threePlayerImage.setImage(new Image("assets/misc/empty.png"));
				playSelected = false;
				numPlayers = 1;
			}
		}

	}
	

	private void highlightPlayers(int players) {
		
		if (players == 1) {
			singlePlayerImage.setImage(new Image("assets/buttons/singlePlayer-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/twoPlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/threePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (players == 2) {
			singlePlayerImage.setImage(new Image("assets/buttons/singlePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/twoPlayer-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/threePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (players == 3) {
			singlePlayerImage.setImage(new Image("assets/buttons/singlePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/twoPlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/threePlayer-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
	}
	
	
	private void highlightButton(int button) {
		
		if (button == 0) {
			playBtnImage.setImage(new Image("assets/buttons/button-play-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (button == 1) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (button == 2) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}

	}
	
	@FXML
	private void initialize() {
		
		Image mainMenuBackground = new Image("bg/background-main.png");
		background.setImage(mainMenuBackground);

		Image playImage = new Image("assets/buttons/button-play-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
		playBtnImage.setImage(playImage);
		
		Image tutorialImage = new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
		tutorialBtnImage.setImage(tutorialImage);
		
		
		Image exitImage = new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
		exitBtnImage.setImage(exitImage);
		
		
		playSelected = false;
		numPlayers = 1;
		buttonIndex= 0;
	}

}
