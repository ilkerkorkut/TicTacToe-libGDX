package com.ilker.tictactoe.ai;

import java.util.Arrays;
import java.util.List;

public class GameStatus {
	
	private static List<List<Integer>> possibleWins = Arrays.asList(
			Arrays.asList(1,2,3), // Horizontal
			Arrays.asList(4,5,6),
			Arrays.asList(7,8,9),
			Arrays.asList(1,4,7), // Vertical
			Arrays.asList(2,5,8),
			Arrays.asList(3,6,9),
			Arrays.asList(1,5,9), // Cross
			Arrays.asList(3,5,7)
	);
	
	public static String playerWon = "Player WON";
	public static String computerWon = "Computer WON";
	public static String gameDraw = "Game DRAW";
	public static String gameCont = "Game Continues";

	public static boolean isGameOver(List<Integer> availableMoves){
		if(availableMoves.size() == 0){
			return true;
		}
		return false;
	}
	
	public static String getGameWinner(List<Integer> playerMoves, List<Integer> computerMoves, List<Integer> availableMoves){
		
		for(List<Integer> possibleWin : possibleWins){
			int p = 0;
			for(int player : playerMoves){
				if(possibleWin.contains(player)){
					p++;
				}
			}
			int c = 0;
			for(int computer : computerMoves){
				if(possibleWin.contains(computer)){
					c++;
				}
			}
			if(p == 3){
				return playerWon;
			}
			if(c == 3){
				return computerWon;
			}
		}
		if(availableMoves.size() == 0){
			return gameDraw;
		}else{
			return  gameCont;
		}
		
	}
	
}
