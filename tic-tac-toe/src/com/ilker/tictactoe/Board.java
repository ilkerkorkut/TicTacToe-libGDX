package com.ilker.tictactoe;

import java.util.ArrayList;
import java.util.List;

import com.ilker.tictactoe.ai.Player;

public class Board {
	
	private final Player EMPTY_POSITION = null;
	private final Integer[][] POSSIBLE_WINS = {
		{0,1,2},{3,4,5},
		{6,7,8},{0,3,6},
		{1,4,7},{2,5,8},
		{0,4,8},{2,4,6}
	};
	private Player[] board;
	private Player crosses = null;
	private Integer[] winnings = null;
	
	public Player getCrosses() {
		return crosses;
	}
	
	public void setOpponents(Player p1, Player p2){
		p1.setRival(p2);
		p2.setRival(p1);
	}

	public void setCrosses(Player crosses) {
		this.crosses = crosses;
	}
	
	public Board(){
		board = new Player[9];
	}

	public Player getWinner(){
		for(Integer[] win : POSSIBLE_WINS){
			if(board[win[0]] != null){
				if(board[win[0]] == board[win[1]] && board[win[0]] == board[win[2]]){
					this.setWinnings(win);
					return board[win[0]];
				}
			}
		}
		return null;
	}
	
	public Integer[] getWinnings() {
		return winnings;
	}

	private void setWinnings(Integer[] winnings) {
		this.winnings = winnings;
	}

	public List<Integer> getValidMoves(){
		List<Integer> moves = new ArrayList<Integer>();
		
		for(int i = 0; i < board.length; i++){
			if(board[i] == EMPTY_POSITION){
				moves.add(i);
			}
		}
		return moves;
	}
	
	public Boolean isGameOver(){
		return this.getWinner() != null || this.getValidMoves().isEmpty();
	}
	
	public Boolean isMoveValid(Integer move){
		return board[move] == EMPTY_POSITION;
	}
	
	public void makeMove(Integer move, Player player){
		if(this.isMoveValid(move)){
			board[move] = player;
		}
	}
	
	public void undoMove(Integer move){
		board[move] = EMPTY_POSITION;
	}
}