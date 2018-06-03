package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class RollPanel extends JPanel {
	public RollPanel(final GameEngine ge, final MainFrame mainFrame,final Player player, DicePair dicePair) {
		String rollResult;
		String currentRoller;
		
		try { rollResult = ":Dice One: " + dicePair.getDice1() + " | | "  + dicePair.getDice2() + " :Dice Two:";
		} catch(NullPointerException e) {			//if there's no rolling going on, show generic info
			rollResult = "No rolls happening";		// if there is rolling, show rolls and who's rolling
		}											// will default to showing house as rolling
													//this jpanel continually gets updated by the callback
		try { currentRoller = "Current player" + player.getPlayerName();
		} catch(NullPointerException e) {
			currentRoller = "House Rolling";
		}
		
		int rows = 2, col = 1;
		JPanel roll = new JPanel(new GridLayout(rows, col));
		roll.add(new JLabel(currentRoller), BorderLayout.NORTH);
		roll.add(new JLabel(rollResult), BorderLayout.CENTER);
		add(roll);
	}
}
