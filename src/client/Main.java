package client;

import javax.swing.SwingUtilities;

import controller.GameController;
import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					Player currentPlayer = null;
					//makes a currentplayer which starts blank, but will become a player
					GameEngine gameEngine = new GameEngineImpl();
					// game engine that will be passed around
					MainFrame mainFrame = new MainFrame(gameEngine, currentPlayer);
					//initialise the jframe that holds the game
					GameController gameController = new GameController(mainFrame, gameEngine);
					//ended up not using this, keeping for posterity
				}
			
		});
		
	}
}
