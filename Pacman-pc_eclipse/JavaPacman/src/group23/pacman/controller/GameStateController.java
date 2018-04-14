package group23.pacman.controller;

import group23.pacman.model.Game;
import group23.pacman.view.GameViewController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** The class that handles the state of the game and the player input.
 * Contains a GameViewController object to update graphics if necessary.
 */

public class GameStateController {
	
	/* Scene used to add key listener */
	private Scene scene;
	
	/* Manipulate the game object */
	private Game game;
	
	/* Reference to game view */
	private GameViewController gameViewController;
	
	/* Keep track of pacman's lives */
	private int pacmanLives;
	
	private boolean gameOver;
	
	private boolean scoreBeaten;
	
	
	
	/* Public constructor */
	public GameStateController(GameViewController gameViewController,Game game) {
		
		this.gameViewController = gameViewController;
		this.scene = gameViewController.getScene();
		this.game = game;
		this.pacmanLives = game.getPacman().getLives();
		this.gameOver = false;
		this.scoreBeaten = false;
		
		
	}
		
	
	/* Updates the game state and score */
	public void update() {
		
		/* Updates object coordinates and checks collisions */
		game.update();
		
		/* Check if player has died */
		game.checkState();
		
		/* Check to make sure we're not out of time */
		checkTimer();
		
		/* If Pacman lost a life, show this to the screen */
		if (pacmanLives != game.getPacman().getLives()) {
			
			/* If all lives lost, stop the game */
			if (game.getPacman().getLives() == 0) {
				
				pacmanLives = game.getPacman().getLives();
				gameViewController.showLivesLeft();
				gameViewController.stopGame();
				gameOver = true;
				
				if (game.scoreBeaten()) {
					scoreBeaten = true;
				}
				
			}
			
			/* Otherwise, just show number of lives to the screen and reset the timer */
			else {
				
				gameViewController.showLivesLeft();
				pacmanLives = game.getPacman().getLives();
				gameViewController.getTimer().resetCounter();
				gameViewController.setTimerImage();
				gameViewController.startCountdown();
			}
		}

	}
	
	
	/* Checks if the player is losing on time */
	private void checkTimer() {
		
		/* If player ran out of time, player loses a life */
		if (gameViewController.getTimer().timedOut()) {
		
			/* Lose life and reset timer */
			game.getPacman().loseLife();
			gameViewController.getTimer().resetCounter();
			gameViewController.setTimerImage();
			gameViewController.finalUpdate();
		}
		
	}
	
	
	private void recordScore() {
		
		gameViewController.showTextField();
		game.updateHighScores(gameViewController.getName());
		
		/* DEBUG STATEMENT */
		System.out.println("New high score :" + gameViewController.getName());
		
	}
	
	
	/* Public getter to allow GameViewController(the view class) to reference objects(to draw) */
	public Game getGame() {
		
		return this.game;
	}
	
	
	/* Public getter to determine if user needs to be congratulated via GameViewController */
	public boolean scoreBeaten() {
		
		return this.scoreBeaten;
	}
	
	public boolean gameOver() {
		
		return this.gameOver;
	}
	
	
	/**
	 * KEY LISTENERS FOR DIFFERENT GAME MODES 
	 */
	public void listenSinglePlayer() {
		
		 scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
		    	@Override
		    	public void handle(KeyEvent e) {
			    	if (e.getCode() == KeyCode.UP) {
			    		game.getPacman().queueMovement('U');
			    	}
			    	else if (e.getCode() == KeyCode.DOWN) {
			    		game.getPacman().queueMovement('D');
			    	}
			    	else if (e.getCode() == KeyCode.LEFT) {
			    		game.getPacman().queueMovement('L');
			    	}
			    	else if (e.getCode() == KeyCode.RIGHT) {
			    		game.getPacman().queueMovement('R');
			    	}
			    	else if (e.getCode() == KeyCode.SPACE) {
			    		game.getPacman().whip();
			    	}

			    	else if (e.getCode() == KeyCode.PAGE_DOWN) {
			    		gameViewController.getTimer().endTimer();
			    		gameViewController.setTimerImage();
			    		
			    	}
			    	
			    	else if (e.getCode() == KeyCode.Y) {
			    		if (gameOver) {
			    			recordScore();
			    			gameViewController.showMenu();
							
			    		}
			    	}
					else if (e.getCode() == KeyCode.N) {
			    		if (gameOver) {
			    			gameViewController.showMenu();
			    		}
			    	}
			    	/* Pause button */
			    	else if (e.getCode() == KeyCode.P) {
			    		gameViewController.toggleState();
			    	}
			    	else if (e.getCode() == KeyCode.ESCAPE) {
			    		gameViewController.showMenu();
			    	}
		    	}
		    });
	}
	
	public void listenTwoPlayer() {
		
		 scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
		    	@Override
		    	public void handle(KeyEvent e) {
			    	if (e.getCode() == KeyCode.UP) {
			    		game.getPacman().queueMovement('U');
			    	}
			    	else if (e.getCode() == KeyCode.DOWN) {
			    		game.getPacman().queueMovement('D');
			    	}
			    	else if (e.getCode() == KeyCode.LEFT) {
			    		game.getPacman().queueMovement('L');
			    	}
			    	else if (e.getCode() == KeyCode.RIGHT) {
			    		game.getPacman().queueMovement('R');
			    	}
			    	else if (e.getCode() == KeyCode.SPACE) {
			    		game.getPacman().whip();
			    	}
			    	else if (e.getCode() == KeyCode.W) {
			    		game.getGhost().queueMovement('U');
			    	}
					else if (e.getCode() == KeyCode.A) {
						game.getGhost().queueMovement('L');	    		
								    	}
					else if (e.getCode() == KeyCode.S) {
						game.getGhost().queueMovement('D');
					}
					else if (e.getCode() == KeyCode.D) {
						game.getGhost().queueMovement('R');
					}
					else if (e.getCode() == KeyCode.Y) {
			    		if (gameOver) {
			    			recordScore();
			    			gameViewController.showMenu();
			    		}
			    	}
					else if (e.getCode() == KeyCode.N) {
			    		if (gameOver) {
			    			gameViewController.showMenu();
			    		}
			    	}
			    	else if (e.getCode() == KeyCode.PAGE_DOWN) {
			    		gameViewController.getTimer().endTimer();
			    		gameViewController.setTimerImage();
			    	}
			    	/* Pause button */
			    	else if (e.getCode() == KeyCode.P) {
			    		gameViewController.toggleState();
			    	}
			    	else if (e.getCode() == KeyCode.ESCAPE) {
			    		gameViewController.showMenu();
			    	}
		    	}
		    });
	}
	
	public void listenThreePlayer() {
		
		 scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
		    	@Override
		    	public void handle(KeyEvent e) {
			    	if (e.getCode() == KeyCode.UP) {
			    		game.getPacman().queueMovement('U');
			    	}
			    	else if (e.getCode() == KeyCode.DOWN) {
			    		game.getPacman().queueMovement('D');
			    	}
			    	else if (e.getCode() == KeyCode.LEFT) {
			    		game.getPacman().queueMovement('L');
			    	}
			    	else if (e.getCode() == KeyCode.RIGHT) {
			    		game.getPacman().queueMovement('R');
			    	}
			    	else if (e.getCode() == KeyCode.SPACE) {
			    		game.getPacman().whip();
			    	}
			    	else if (e.getCode() == KeyCode.W) {
			    		game.getGhost().queueMovement('U');
			    	}
					else if (e.getCode() == KeyCode.A) {
						game.getGhost().queueMovement('L');	    		
								    	}
					else if (e.getCode() == KeyCode.S) {
						game.getGhost().queueMovement('D');
					}
					else if (e.getCode() == KeyCode.D) {
						game.getGhost().queueMovement('R');
					}
					else if (e.getCode() == KeyCode.I) {
			    		game.getGhost2().queueMovement('U');
			    	}
					else if (e.getCode() == KeyCode.J) {
						game.getGhost2().queueMovement('L');	    		
								    	}
					else if (e.getCode() == KeyCode.K) {
						game.getGhost2().queueMovement('D');
					}
					else if (e.getCode() == KeyCode.L) {
						game.getGhost2().queueMovement('R');
					}
					else if (e.getCode() == KeyCode.Y) {
			    		if (gameOver) {
			    			recordScore();
			    			gameViewController.showMenu();
			    		}
			    	}
					else if (e.getCode() == KeyCode.N) {
			    		if (gameOver) {
			    			gameViewController.showMenu();
			    		}
			    	}
			    	else if (e.getCode() == KeyCode.PAGE_DOWN) {
			    		gameViewController.getTimer().endTimer();
			    		gameViewController.setTimerImage();
			    	}
			    	/* Pause button */
			    	else if (e.getCode() == KeyCode.P) {
			    		gameViewController.toggleState();
			    	}
			    	else if (e.getCode() == KeyCode.ESCAPE) {
			    		gameViewController.showMenu();
			    	}
		    	}
		    });
	}
	

}
