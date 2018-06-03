package view;


import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class InputPanel extends JPanel {
	
	public InputPanel(final GameEngine gameEngine, final MainFrame mainFrame,final Player currentPlayer) {
		final DicePair dicePair = null;
		int rows = 6, col = 1;
		JPanel input = new JPanel(new GridLayout(rows, col));
		input.add(new JLabel("Add Player"));
		input.add(new JLabel("Player name:"));
		final JTextField nameInput = new JTextField(15);
		input.add(nameInput);
		input.add(new JLabel("Player Points:"));
		final JTextField pointsInput = new JTextField(15);
		input.add(pointsInput); // fields for player detail input


		
		JButton submit = new JButton("Save Player");
		submit.addActionListener(new ActionListener()
        {
             public void actionPerformed(ActionEvent ac)
             {
         		 final Collection<Player> playersList = gameEngine.getAllPlayers();
            	 int points1 = Integer.parseInt(pointsInput.getText());
            	 String id = Integer.toString(playersList.size());		//sets player id in order, so that
            	 String playerName = nameInput.getText();				//first player added will be 0, second 1, etc
            	 Player tempPlayer = new SimplePlayer(id, playerName, points1);
            	 tempPlayer.placeBet(0);
            	 gameEngine.addPlayer(tempPlayer);
            	 nameInput.setText("");
            	 pointsInput.setText("");
            	 mainFrame.populate(gameEngine, mainFrame.getCurrentPlayer(), dicePair); //update the ui
             }
         });
		
		input.add(submit);
		
		

		

		add(input);
	}
	


}
