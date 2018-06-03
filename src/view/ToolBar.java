package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class ToolBar extends JToolBar {
	public ToolBar(final GameEngine ge, final MainFrame mainFrame,final Player currentPlayer, final DicePair dicePair) {
		DicePair dicePair2 = null; //dicepair to pass to mainFrame.populate
		JToolBar toolbar = new JToolBar(); //toolbar that holds all the buttons
		JButton showPlayer = new JButton("Show Players");		//button that displays all the relevant info
		showPlayer.addActionListener(new ActionListener()		// for players in the game
        { public void actionPerformed(ActionEvent ab)
             {	List<Player> currentPlayers = new ArrayList<Player>();
         		Collection<Player> playersList = ge.getAllPlayers();
         		int j = 0;
         		for (Player player : playersList){ 		// grab the collection of players from the game enggine
         			  Player type = player;				// loop through it, make a list of players to open a 
         			  String ID = Integer.toString(j);	// dialog box that will show them all
         			  currentPlayers.add((player));
         			  j++;
         			}
            	 JOptionPane.showMessageDialog(null,
            	new JScrollPane(new JList(playersList.toArray())));
             }
         });
		
		
		JButton selectPlayer = new JButton("Select Player");
		selectPlayer.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent ae)
             {	String currentID = JOptionPane.showInputDialog(null, "Enter player ID of the player you wish to select");
//asks the user to select the id of a player to select as the current focused player
             //player id's can be seen from the above show players function
            	mainFrame.populate(ge, ge.getPlayer(currentID), dicePair);
             }
         });
		JButton rollHouse = new JButton("Roll House");
		rollHouse.addActionListener(new ActionListener()
        { public void actionPerformed(ActionEvent af)
        {	
       	 new Thread()
			 {						//the listener for rolling.
			 @Override				//Rolling house rolls all players
			 public void run()		// players by default have a bet of 0, so they will be rolled
			 {						// but nothing will be added or deducted to their points

 			Collection<Player> playersList = ge.getAllPlayers();
 			final int initialDelay = 500; final int finalDelay = 2500; final int delayIncrement = 500;
 			for (Player player : playersList){
 			  final Player type = player;
 			  	// loops through the list of players added to the client
 			  // rolls them
 				ge.rollPlayer(type, initialDelay, finalDelay, delayIncrement);
 			}

				ge.rollHouse( initialDelay, finalDelay, delayIncrement);
				// finally rolls house, which calls game engine to settle bets
				// and then finally set all bets to 0

         		Collection<Player> listPlayers = ge.getAllPlayers();
            	 JOptionPane.showMessageDialog(null,
            	new JScrollPane(new JList(listPlayers.toArray())));
				//shows updated player information
				
				
				
 			}}.start();

     }
        
           
         });
		
		
		
		
		
		
		
		
		
		String[] boxHeadings = new String[] {"Show Players", "Select Player"};
		JComboBox<String> headingList = new JComboBox<>(boxHeadings);
		// Trying to meet requirements?
		
		// adds everything to the toolbar, then adds the toolbar to the view
		toolbar.add(headingList);
		toolbar.add(showPlayer);
		toolbar.add(selectPlayer);
		toolbar.add(rollHouse);
		add(toolbar);
	}
		


}


