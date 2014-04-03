package com.ilker.tictactoe.ai;

import java.util.Arrays;
import java.util.List;

public class GameStatus {
	
	private List<List<Integer>> possibleWins = Arrays.asList(
			Arrays.asList(1,2,3), // Horizontal
			Arrays.asList(4,5,6),
			Arrays.asList(7,8,9),
			Arrays.asList(1,4,7), // Vertical
			Arrays.asList(2,5,8),
			Arrays.asList(3,6,9),
			Arrays.asList(1,5,9), // Cross
			Arrays.asList(3,5,7)
	);

	public boolean isGameOver(List<Integer> availableMoves){
		if(availableMoves.size() == 0){
			return true;
		}
		return false;
	}
	
	public boolean isGameDraw(List<Integer> playerMoves, List<Integer> computerMoves){
		
		return false;
	}
	
	public String getGameWinner(List<Integer> playerMoves, List<Integer> computerMoves){
		
		return null;
	}
	
}
