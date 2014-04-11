package com.ilker.tictactoe.ai;

import com.ilker.tictactoe.Board;

public abstract class Player {
	private Player rival;
	
	public Player getRival() {
		return rival;
	}
	public void setRival(Player rival) {
		this.rival = rival;
	}
	
	public abstract int play(Board b);

}