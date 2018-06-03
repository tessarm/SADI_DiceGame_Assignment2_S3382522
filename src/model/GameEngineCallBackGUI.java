package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import view.MainFrame;

public class GameEngineCallBackGUI implements GameEngineCallback {
	public GameEngineCallBackGUI(MainFrame mainFrame) {
		this.mainFrame = mainFrame; //needs a mainframe to call populate
	}								//the 4 callbacks below just populate with variables based off of the rolls
									// thereby allowing the ui to update every time there is a roll
	MainFrame mainFrame;
	

	@Override
	public void intermediateResult(final Player player, final DicePair dicePair, final GameEngine gameEngine) {

		mainFrame.populate(gameEngine, player, dicePair);

	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine) {
		mainFrame.populate(gameEngine, player, result);

	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		Player player = null;
		mainFrame.populate(gameEngine, player, dicePair);

	}		//both house rolls have player null because the house isn't a player, and populate needs a player

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		Player player = null;
		mainFrame.populate(gameEngine, player, result);

	}

}
