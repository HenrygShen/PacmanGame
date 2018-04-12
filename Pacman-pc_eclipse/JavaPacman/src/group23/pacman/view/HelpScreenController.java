package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** Controller class for the HelpScreen view */

public class HelpScreenController {
	
	/* Constant - do not change */
	private final int MAX_BACKGROUND_INDEX = 2;
	
	/* FXML elements in HelpScreen.fxml */
	@FXML
	private ImageView backgroundImage;
	@FXML
	private ImageView helpPanel;
	
	/* Main app copy kept to use when referencing to get its scene. */
	private MainApp mainApp;
	private Scene scene;
	
	/* Variables for showing which help panel will be set */
	private int index;
	private Image help1;
	private Image help2;
	private Image help3;
	private Image helpImages[];
	
	
	/* Constructor */
	public HelpScreenController() {
		
	}
	
	/* Adds key listener to scene */
	public void listenToKeyEvents() {
		
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				
				if (event.getCode() == KeyCode.LEFT) {
					setLeftBackground();
				}
				else if (event.getCode() == KeyCode.RIGHT) {
					setRightBackground();
				}
				else if (event.getCode() == KeyCode.ESCAPE) {
					mainApp.showWelcomeScreen();
				}
			}
		});
	}
	
	
	/* Sets up images and backgrounds for initial view */
	@FXML
	private void initialize() {
		
		/* Set up background of this view */
		Image background = new Image("bg/help_screen_background.png");
		backgroundImage.setImage(background);
		
		/* Prepare tutorial slides */
		help1 = new Image("bg/helpPanelOne.png");
		help2 = new Image("bg/helpPanelTwo.png");
		help3 = new Image("bg/helpPanelThree.png");
		helpImages = new Image[3];
		helpImages[0] = help1;
		helpImages[1] = help2;
		helpImages[2] = help3;
		
		/* Show first slide */
		index = 0;
		helpPanel.setImage(helpImages[index]);
		
		
	}

	
	/* Public setter to reference main application */
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
		this.scene = mainApp.getScene();
		
	}
	
	
	/** BELOW ARE HELPER FUNCTIONS WHICH HELP WITH THE ANIMATION OF THIS VIEW **/
	
	/* Set background functions - 
	 * Help scroll the background to the left or the right
	 */
	private void setLeftBackground() {
		
		index--;
		index = (index < 0) ? MAX_BACKGROUND_INDEX : index;
		helpPanel.setImage(helpImages[index]);
		System.out.println("Index : " + index);
	}
	
	private void setRightBackground() {
		
		index++;
		index = (index > MAX_BACKGROUND_INDEX) ? 0 : index;
		helpPanel.setImage(helpImages[index]);
		System.out.println("Index : " + index);
	}
	
}
