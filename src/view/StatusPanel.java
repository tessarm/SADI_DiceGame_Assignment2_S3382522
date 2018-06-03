package view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	public StatusPanel(GameEngine ge, Player currentPlayer) {
		int rows = 1, col = 4;
		JPanel status = new JPanel(new GridLayout(rows, col));

		status.add(new JLabel("Selected Player"));				//this JPanel will show the info of the current player
		try{													//try catches to show generic info if no current player exists
			status.add(new JLabel(currentPlayer.getPlayerName()));
		} catch (NullPointerException e) {
			status.add(new JLabel("Player Name"));
		}
		
		try {
			status.add(new JLabel(Integer.toString(currentPlayer.getPoints())));
		} catch (NullPointerException e) {
			status.add(new JLabel("Points"));
		}

		try {
		status.add(new JLabel(Integer.toString(currentPlayer.getBet())));
		} catch(NullPointerException e) {
			status.add(new JLabel("Bet"));
		}
		status.add(new JLabel("Current Roll"));
		add(status);
	}
}
