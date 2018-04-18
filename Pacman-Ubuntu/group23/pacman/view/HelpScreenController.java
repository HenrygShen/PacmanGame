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
	private final int MAX_BACKGROUND_INDEX = 8;
	
	/* FXML elements in HelpScreen.fxml */
	@FXML
	private ImageView backgroundImage;
	@FXML
	private ImageView helpPanel;
	@FXML
	private ImageView helpTextPanel;
	
	/* Main app copy kept to use when referencing to get its scene. */
	private MainApp mainApp;
	private Scene scene;
	
	/* Variables for showing which help panel will be set */
	private int index;
	private Image help1;
	private Image help2;
	private Image help3;
	private Image help4;
	private Image help5;
	private Image help6;
	private Image help7;
	private Image help8;
	private Image help9;
	private Image helpText1;
	private Image helpText2;
	private Image helpText3;
	private Image helpText4;
	private Image helpText5;
	private Image helpText6;
	private Image helpText7;
	private Image helpText8;
	private Image helpText9;
	private Image helpImages[];
	private Image helpTextImages[];
	
	
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
		Image background = new Image("bg/backgrounds-helpScreen/help_screen_background.png");
		backgroundImage.setImage(background);
		
		/* Prepare tutorial slides */
		help1 = new Image("bg/backgrounds-helpScreen/helpPanel1.png");
		help2 = new Image("bg/backgrounds-helpScreen/helpPanel2.png");
		help3 = new Image("bg/backgrounds-helpScreen/helpPanel3.png");
		help4 = new Image("bg/backgrounds-helpScreen/helpPanel4.png");
		help5 = new Image("bg/backgrounds-helpScreen/helpPanel5.png");
		help6 = new Image("bg/backgrounds-helpScreen/helpPanel6.png");
		help7 = new Image("bg/backgrounds-helpScreen/helpPanel7.png");
		help8 = new Image("bg/backgrounds-helpScreen/helpPanel8.png");
		help9 = new Image("bg/backgrounds-helpScreen/helpPanel9.png");
		helpText1 = new Image("bg/backgrounds-helpScreen/helptext1.png");
		helpText2 = new Image("bg/backgrounds-helpScreen/helptext2.png");
		helpText3 = new Image("bg/backgrounds-helpScreen/helptext3.png");
		helpText4 = new Image("bg/backgrounds-helpScreen/helptext4.png");
		helpText5 = new Image("bg/backgrounds-helpScreen/helptext5.png");
		helpText6 = new Image("bg/backgrounds-helpScreen/helptext6.png");
		helpText7 = new Image("bg/backgrounds-helpScreen/helptext7.png");
		helpText8 = new Image("bg/backgrounds-helpScreen/helptext8.png");
		helpText9 = new Image("bg/backgrounds-helpScreen/helptext9.png");
		helpImages = new Image[9];
		helpTextImages = new Image[9];
		helpImages[0] = help1;
		helpImages[1] = help2;
		helpImages[2] = help3;
		helpImages[3] = help4;
		helpImages[4] = help5;
		helpImages[5] = help6;
		helpImages[6] = help7;
		helpImages[7] = help8;
		helpImages[8] = help9;
		helpTextImages[0] = helpText1;
		helpTextImages[1] = helpText2;
		helpTextImages[2] = helpText3;
		helpTextImages[3] = helpText4;
		helpTextImages[4] = helpText5;
		helpTextImages[5] = helpText6;
		helpTextImages[6] = helpText7;
		helpTextImages[7] = helpText8;
		helpTextImages[8] = helpText9;
		
		/* Show first slide */
		index = 0;
		helpPanel.setImage(helpImages[index]);
		helpTextPanel.setImage(helpTextImages[index]);
		
		
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
		index = (index < 0) ? 0 : index;
		helpPanel.setImage(helpImages[index]);
		helpTextPanel.setImage(helpTextImages[index]);
	}
	
	private void setRightBackground() {
		
		index++;
		index = (index > MAX_BACKGROUND_INDEX) ? MAX_BACKGROUND_INDEX : index;
		helpPanel.setImage(helpImages[index]);
		helpTextPanel.setImage(helpTextImages[index]);
	}
	
}

