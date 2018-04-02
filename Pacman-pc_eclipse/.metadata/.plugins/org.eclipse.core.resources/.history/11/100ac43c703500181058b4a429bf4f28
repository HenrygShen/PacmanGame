package group23.pacman.controller;

import group23.pacman.model.Game;
import group23.pacman.view.GameViewController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameStateController {
	
	/* Scene used to add key listener */
	private Scene scene;
	
	private Game game;
	
	private GameViewController gameViewController;
	
	public GameStateController(GameViewController gameViewController,Game game) {
		
		this.gameViewController = gameViewController;
		this.scene = gameViewController.getScene();
		this.game = game;
		
	}
	
	public void listenToKeyEvents() {
		
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
			    	/* Pause button */
			    	else if (e.getCode() == KeyCode.P) {
			    		gameViewController.toggleState();
			    	}
			    	else if (e.getCode() == KeyCode.PAGE_DOWN) {
			    		gameViewController.getTimer().endTimer();
			    		gameViewController.setTimerImage();
			    	}
			    	else if (e.getCode() == KeyCode.ESCAPE) {
			    		Platform.exit();
			    	}
		    	}
		    });
	}
	
	/* Update the game state and score */
	public void update() {

		game.update();
		gameViewController.updateLives();
		checkTimer();
	}
	
	private void checkTimer() {
		
		if (gameViewController.getTimer().timedOut()) {
			game.getPacman().died();
			gameViewController.toggleState();
			if (game.getPacman().getLives() > 0) {
				gameViewController.getTimer().resetCounter();
				gameViewController.setTimerImage();
				//gameViewController.startGame();
				gameViewController.startCountdown();
			}
		}
		
	}
	
	public boolean notZeroLives() {
		
		return (game.getPacman().getLives() > 0);
	}
	
	
	/* Public getter to allow GameViewController(the view class) to reference objects(to draw) */
	public Game getGame() {
		
		return this.game;
	}

}
