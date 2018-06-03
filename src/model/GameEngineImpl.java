package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine {

	List<Player> playersList = new ArrayList<Player>();
	List<GameEngineCallback> callBackList = new ArrayList<GameEngineCallback>();
	
	@Override
	public boolean placeBet(Player player, int bet) {
		if(player.getPoints() >= bet) {
		player.placeBet(bet);
		return true;}
		// checks to make sure the bet is valid
		return false;
	}
	
	public static int NUM_FACES = 6;

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {

		int delay = initialDelay, faceOne, faceTwo;
		Random random = new Random(); 
		while(delay < finalDelay) {
			try {
				Thread.sleep(delay); //delay implementation
			} catch (InterruptedException e) {
			}
			faceOne = random.nextInt(NUM_FACES) + 1;				
			faceTwo = random.nextInt(NUM_FACES) + 1;
			
			DicePair tempDice = new DicePairImpl(faceOne, faceTwo, NUM_FACES);
			for(GameEngineCallback callBackList: callBackList) {
				callBackList.intermediateResult(player, tempDice, this);
			}
//			callBack.intermediateResult(player, tempDice, this);
			delay = delay + delayIncrement;
		}
		faceOne = random.nextInt(NUM_FACES) + 1;				
		faceTwo = random.nextInt(NUM_FACES) + 1;
		DicePair tempDice = new DicePairImpl(faceOne, faceTwo, NUM_FACES);
		player.setRollResult(tempDice);
		for(GameEngineCallback callBackList: callBackList) {
			callBackList.result(player, tempDice, this);
		}
		//callBack.result(player, tempDice, this);
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {

		int faceOne, faceTwo, totalDice, firstDice, secondDice, houseDice, delay = initialDelay;
		Random random = new Random(); 
		while(delay < finalDelay) {
			try {
				Thread.sleep(delay); //delay implementation
			} catch (InterruptedException e) {
			}
			faceOne = random.nextInt(NUM_FACES) + 1;				
			faceTwo = random.nextInt(NUM_FACES) + 1;
			DicePair tempDice = new DicePairImpl(faceOne, faceTwo, NUM_FACES);
			for(GameEngineCallback callBackList: callBackList) {
				callBackList.intermediateHouseResult(tempDice, this);
			}
//			callBack.intermediateHouseResult(tempDice, this);
			delay = delay + delayIncrement;
		}
		faceOne = random.nextInt(NUM_FACES) + 1;				
		faceTwo = random.nextInt(NUM_FACES) + 1;
		totalDice = faceOne + faceTwo;
		DicePair tempDice = new DicePairImpl(faceOne, faceTwo, NUM_FACES);
		for(GameEngineCallback callBackList: callBackList) {
			callBackList.houseResult(tempDice, this);
		}
		//callBack.houseResult(tempDice, this);
		
		for(int i=0; i < playersList.size();i++) { //for loop with nested if's to check if the player beat the house or not, and adjusts the players points
			Player tempPlayer = playersList.get(i);
			DicePair compareDice = tempPlayer.getRollResult();
			firstDice = compareDice.getDice1();
			secondDice = compareDice.getDice2();
			houseDice = firstDice + secondDice;
			if(tempPlayer.getBet() > 0) {
				if(houseDice > 1) {
					if(totalDice > houseDice) {
						playersList.get(i).setPoints(playersList.get(i).getBet() + playersList.get(i).getPoints());
						playersList.get(i).placeBet(0);

					}
					else if(totalDice < houseDice) {
						playersList.get(i).setPoints(playersList.get(i).getPoints() - playersList.get(i).getBet());
						playersList.get(i).placeBet(0);
					}
				}
			
			}
		}
	}

	@Override
	public void addPlayer(Player player) {
		playersList.add(player);


	}
	
	@Override
	public boolean removePlayer(Player player) {
		if(playersList.contains(player)) {
			playersList.remove(player);
			return true;
		}
		return false;
	}

	@Override
	public Player getPlayer(String id) {
		for(int i=0; i < playersList.size();i++) {
			Player tempPlayer = playersList.get(i);

			if(id.equals(tempPlayer.getPlayerId())){
				return tempPlayer;
			}
		}
		return null;
	}



	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callBackList.add(gameEngineCallback);

	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if(callBackList.contains(gameEngineCallback)) {
			callBackList.remove(gameEngineCallback);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// creates an unmodifiable copy of the player list, as per the marking rubric
		List<Player> playersListCopy = new ArrayList<Player>();
		playersListCopy = Collections.unmodifiableList(playersList);
		return playersListCopy;
	}

}
