package com.ilker.tictactoe.ai;

import com.badlogic.gdx.Gdx;
import com.ilker.tictactoe.Board;
import com.ilker.tictactoe.TicTacToe;

public class ComputerPlayer extends Player {

	@Override
	public int play(Board b) {
		//Gdx.app.log(TicTacToe.COMPUTER_LOG, String.valueOf(minimax(b, this, 0).intValue()));
		//return minimax(b, this, 0).intValue();
		//Gdx.app.log(TicTacToe.COMPUTER_LOG, String.valueOf(alphaBeta(b, this, 0,-1,1).intValue()));
		return alphaBeta(b, this, 0,-1F, 1F,true).intValue();
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
			
			if(bestScore == null || (player == this && bestScore < score) || (player != this && bestScore > score)) {
				bestScore = score;
				bestMove = move;
			}
		}
		return (depth == 0) ? bestMove : bestScore;
	}

	public Float alphaBeta(Board board, Player player, Integer depth, Float alpha, Float beta, boolean maximizing){
		if(board.isGameOver()){
			return score(board, depth);
		}
		
		Integer bestMove = 0;
		Float bestScore = null;
		if(maximizing){
			for(int move : board.getValidMoves()){
				board.makeMove(move, player);
				//Gdx.app.log(TicTacToe.COMPUTER_LOG+" MOVE", String.valueOf(move));	// Uncomment for logging
				Float score = alphaBeta(board, player.getRival(), depth+1,alpha,beta,false);
				if(score > alpha){
					alpha = score;
				}
				board.undoMove(move);
				
				if(bestScore == null || (player == this && bestScore < score) || (player != this && bestScore > score)) {
					//Gdx.app.log(TicTacToe.COMPUTER_LOG+" Calculating Best Move", String.valueOf(move));	// Uncomment for logging
					//Gdx.app.log(TicTacToe.COMPUTER_LOG+" Calculating Best SCORE", String.valueOf(score));	// Uncomment for logging
					bestScore = score;
					bestMove = move;
				}
			}
		}else{
			for(int move : board.getValidMoves()){
				board.makeMove(move, player);
				//Gdx.app.log(TicTacToe.COMPUTER_LOG+" MOVE", String.valueOf(move));	// Uncomment for logging
				Float score = alphaBeta(board, player.getRival(), depth+1,alpha,beta,true);
				board.undoMove(move);
				if(score < beta){
					beta = score;
				}
				if(bestScore == null || (player == this && bestScore < score) || (player != this && bestScore > score)) {
					//Gdx.app.log(TicTacToe.COMPUTER_LOG+" Calculating Best Move", String.valueOf(move));	// Uncomment for logging
					//Gdx.app.log(TicTacToe.COMPUTER_LOG+" Calculating Best SCORE", String.valueOf(score));	// Uncomment for logging
					bestScore = score;
					bestMove = move;
				}
			}
		}
		//Gdx.app.log(TicTacToe.COMPUTER_LOG+" Calculated Best Move", String.valueOf(bestMove));	// Uncomment for logging
		return (depth == 0) ? bestMove : bestScore;
	}
}
