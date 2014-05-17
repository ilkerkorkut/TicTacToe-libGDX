package com.ilker.tictactoe.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ilker.tictactoe.Board;
import com.ilker.tictactoe.ai.ComputerPlayer;
import com.ilker.tictactoe.ai.HumanPlayer;
import com.ilker.tictactoe.ai.Player;


public class Tests {
	
	/**
	 * Tic Tac Toe Board
	 * 		|0|1|2|
	 * 		|3|4|5|
	 * 		|6|7|8|
	 */

	Player humanPlayer = new HumanPlayer();
	Player computerPlayer = new ComputerPlayer();
	Board board = new Board();
	
	@Test
	public void testWinner() {
		board.setOpponents(humanPlayer, computerPlayer);
		board.makeMove(0, humanPlayer);
		board.makeMove(4, humanPlayer);
		board.makeMove(8, humanPlayer);
		computerPlayer.play(board);
		assertNotNull(board.getWinner());
	}

	@Test
	public void testBoard() {
		board.setOpponents(humanPlayer, computerPlayer);
		board.makeMove(0, humanPlayer);
		assertTrue(board.getValidMoves().size() < 9);
	}

}
