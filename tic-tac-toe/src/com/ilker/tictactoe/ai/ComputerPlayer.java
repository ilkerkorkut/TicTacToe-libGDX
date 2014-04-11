package com.ilker.tictactoe.ai;

import com.ilker.tictactoe.Board;

public class ComputerPlayer extends Player {

	@Override
	public int play(Board b) {
		return minimax(b, this, 0).intValue();
	}
	
	public Float score(Board board, Integer depth){
		if(board.getWinner() == this){
			return 1F/depth;
		}
		if(board.getWinner() == null){
			return 0F;
		}
		return -1F/depth;
	}
	
	public Float minimax(Board board, Player player, Integer depth){
		if(board.isGameOver()){
			return score(board, depth);
		}
		
		Integer bestMove = 0;
		Float bestScore = null;
		
		for(int move : board.getValidMoves()){
			
			board.makeMove(move, player);
			Float score = minimax(board, player.getRival(), depth+1);
			board.undoMove(move);
			
			if(bestScore == null ||
			  (player == this && bestScore < score) ||
			  (player != this && bestScore > score)) {
				
				bestScore = score;
				bestMove = move;
			}
		}
		return (depth == 0) ? bestMove : bestScore;
	}

}
