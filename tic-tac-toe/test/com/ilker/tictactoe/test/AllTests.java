package com.ilker.tictactoe.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ilker.tictactoe.ai.GameStatus;

public class AllTests {
	
	List<Integer> playerMoves = new ArrayList<Integer>();//Arrays.asList(1,7);
	List<Integer> computerMoves = new ArrayList<Integer>();//.asList(2);
	List<Integer> availableMoves = new ArrayList<Integer>();//Arrays.asList(3,4,5,6,8,9);
	
	

	@Test
	public void testWinner(){
		assertEquals(GameStatus.gameCont,GameStatus.getGameWinner(playerMoves, computerMoves, availableMoves));
	}
	
	@Test
	public void testMinimax(){
		playerMoves.add(3);
		playerMoves.add(7);
		computerMoves.add(6);
		availableMoves.add(1); availableMoves.add(2); availableMoves.add(4); availableMoves.add(5); availableMoves.add(8); availableMoves.add(9);
		assertEquals(5, minimax(playerMoves, computerMoves, availableMoves));
	}
	
	int i = 0;
	int score = 0;
	public int minimax(List<Integer> playerMoves, List<Integer> computerMoves, List<Integer> availableMoves){
		System.out.println("again");
		
		List<Integer> compMoves = computerMoves;
		List<Integer> plMoves = playerMoves;
		
		System.out.println("playermoves : " + playerMoves);
		String gameStatus = GameStatus.getGameWinner(playerMoves, computerMoves, availableMoves);
		System.out.println(gameStatus);
		int a = 0;
		if(i != 0){
			a = playerMoves.get(playerMoves.size()-1);
			playerMoves.remove(playerMoves.size()-1);
		}
		
		i++;
		//if(GameStatus.isGameOver(availableMoves)){
			if(GameStatus.gameDraw.equals(gameStatus)){
				System.out.println("gamedraw");
				//return 0;
			}else if(GameStatus.computerWon.equals(gameStatus)){
				System.out.println("comp won");
				
				//return 1;
			}else if(GameStatus.playerWon.equals(gameStatus)){
				System.out.println("playerwon");
				score = a;
				//return -1;
			}else{
				System.out.println("nothing");
				//return 0;
			}
		//}else{
			int bestComputerScore = -1;
			int bestPlayerScore = -1;
			
			
			if(availableMoves.size() > 0){
				int i = 0;
				for(int move : availableMoves){

					plMoves.add(move);
					availableMoves.remove(i);
					
					System.out.println("plMoves: " +plMoves);
					System.out.println("availableMoves: " +availableMoves);
					System.out.println("---------------------------");
					
					//bestPlayerScore = Math.max(minimax(plMoves, computerMoves, availableMoves), bestPlayerScore);
					minimax(plMoves, computerMoves, availableMoves);
					
					
					System.out.println("BEST PLAYER SCORE : " + score);
					
				}
			}
			System.out.println("RETURNED");
			System.out.println(score);
			return score;
		//}
	}
	
}
