package com.ilker.tictactoe.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.ilker.tictactoe.Assets;
import com.ilker.tictactoe.screens.GameScreen;
import com.ilker.tictactoe.screens.MainMenu;
import com.ilker.tictactoe.screens.ResultScreen;

public class ComputerAI {
	
	private static int INFINITY = Integer.MAX_VALUE;
	private GameStatus gameStatus;
	
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
	
	public void play(List<Integer> playerMoves,HashMap<Integer,TextButton> buttonsMap){
		Collections.sort(playerMoves);
		
		decideMove(playerMoves,buttonsMap);
		checkWinner(playerMoves);
	}
	
	public void decideMove(List<Integer> playerMoves, HashMap<Integer,TextButton> buttonsMap){
		
		List<Integer> availableMoves = new ArrayList<Integer>();
		List<Integer> computerMoves = new ArrayList<Integer>();
		
		TextureAtlas atlas = Assets.manager.get(Assets.atlasCell);
		Skin skin = new Skin(Gdx.files.internal("ui/gameSkin.json"),atlas);
		BitmapFont blackFont = Assets.manager.get(Assets.fontBlack);
		
		TextButtonStyle buttonStyle2 = new TextButtonStyle();
		buttonStyle2.up = skin.getDrawable("empty");
		buttonStyle2.checked = skin.getDrawable("o");
		buttonStyle2.pressedOffsetX = 1;
		buttonStyle2.pressedOffsetY = -1;
		buttonStyle2.font = blackFont;
		
		
		
		for(Entry<Integer,TextButton> e : buttonsMap.entrySet()){
			// Getting available areas
			if(!e.getValue().isChecked()){
				availableMoves.add(e.getKey());
			}
		}
		
		int bestMove = -1;
        int bestMoveScore = -INFINITY;
		
		int minimaxResult = minimax(playerMoves, computerMoves, availableMoves,32);
		
		TextButton button = buttonsMap.get(minimaxResult);
		if(!button.isChecked()){
			
		}
		button.setStyle(buttonStyle2);
		button.setChecked(true);
		button.setDisabled(true);
		buttonsMap.put(6, button);
		computerMoves.add(6);

		for(int i = 0 ; i < availableMoves.size(); i++){
			if(minimaxResult == availableMoves.get(i)){
				availableMoves.remove(i);
			}
		}
		
		System.out.println("playerMoves: " + playerMoves);
		System.out.println("ComputerMoves: "+computerMoves);
		System.out.println("availableMoves: "+availableMoves);
	}

	public int minimax(List<Integer> playerMoves, List<Integer> computerMoves, List<Integer> availableMoves, int depth){
		
		if (gameStatus.isGameOver(availableMoves) || depth <= 0){
			
		}else{
			int bestPlayerScore = -INFINITY;
			int bestOpponentScore = -INFINITY;
			
			for(int move : availableMoves){
				computerMoves.add(move);
				
				bestOpponentScore = Math.max(minimax(playerMoves, computerMoves, availableMoves, depth - 1), bestOpponentScore);
				
				bestPlayerScore = -bestOpponentScore;
				
			}
			
			System.out.println(bestPlayerScore > 0 ? bestPlayerScore - depth : bestPlayerScore + depth);
			return bestPlayerScore > 0 ? bestPlayerScore - depth : bestPlayerScore + depth;
		}
		
		return 0;
	}

	public int checkWinner(List<Integer> playerMoves){
		for(List<Integer> possibleWin : this.possibleWins){
			int i = 0;
			for(int pm : playerMoves){
				for(int pw : possibleWin){
					if(pm == pw){
						i++;
					}
					if(i == 3){
						((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("You WON"));
						break;
					}
				}
			}
		}
		return 0;
	}
	
}
