package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class BetPanel extends JPanel {

	public BetPanel(final GameEngine ge, final MainFrame mainFrame,final Player currentPlayer) {
		final DicePair dicePair = null;
		int rows = 5, col = 1;
		JPanel bet = new JPanel(new GridLayout(rows, col));
		bet.add(new JLabel("Add Bet"));
		try{
			bet.add(new JLabel("Player Name: " + currentPlayer.getPlayerName()));
		} catch (NullPointerException e) {
			bet.add(new JLabel("Player Name"));
		}				// add some labels
						// if a player has been selected, shows their info
		try{			// if not, generic blank info
			bet.add(new JLabel("Player Points: " + currentPlayer.getPoints()));
		} catch (NullPointerException e) {
			bet.add(new JLabel("Player Points"));
		}
		
		try{
			bet.add(new JLabel("Player Bet: " + currentPlayer.getBet()));
		} catch (NullPointerException e) {
			bet.add(new JLabel("Player Bet"));
		}
		
		
		JButton submit = new JButton("Save Player's bet");
		submit.addActionListener(new ActionListener()		//pressing the button opens a 
        {												    //dialog box that will ask the user
             public void actionPerformed(ActionEvent ae)	// for the amount to bet on the current player
             {												// then sets the bet for the current player
         		
            	String currentBet = JOptionPane.showInputDialog(null, "Enter the amount you would like to bet");
            	mainFrame.populate(ge, mainFrame.setBet(Integer.parseInt(currentBet)), dicePair);

             }
         });	
		bet.add(submit);
		
		add(bet);
	
}
}