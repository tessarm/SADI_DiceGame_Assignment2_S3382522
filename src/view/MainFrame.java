package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.GameEngineCallBackGUI;
import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;


@SuppressWarnings("serial")

public class MainFrame extends JFrame {
	private Player currentPlayer;

	public MainFrame(GameEngine ge,Player currentPlayer) {
		DicePair dicePair = null;
		ge.addGameEngineCallback(new GameEngineCallBackGUI(this));
		ge.addGameEngineCallback(new GameEngineCallbackImpl());
		//adds callbacks, ui and logs
		
		currentPlayer = currentPlayer;
		populate(ge, currentPlayer, dicePair); // generate the ui

		
		pack();	
		setBounds(100, 100, 640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);



	}

public void populate(GameEngine ge,Player currentPlayer, DicePair dicePair ) {
	this.currentPlayer = currentPlayer;
	
	add(new RollPanel(ge, this ,currentPlayer, dicePair), BorderLayout.CENTER);
	add(new InputPanel(ge, this ,currentPlayer), BorderLayout.WEST);
	add(new StatusPanel(ge, currentPlayer), BorderLayout.SOUTH);
	add(new ToolBar(ge, this ,currentPlayer, dicePair), BorderLayout.NORTH);
	add(new BetPanel(ge,this ,currentPlayer), BorderLayout.EAST);
	revalidate();
	// the method that runs the whole thing
	// calling this refreshes the ui, enables things to update
	// this also necessitates the adding of a bunch of random variable in classes that have no business needing them
	

	
	
	}

public Player getCurrentPlayer(){ //methods that make things cleaner/possible
	return currentPlayer;
}

public Player setBet(int bet){
	currentPlayer.placeBet(bet);
	return currentPlayer;
}

}
