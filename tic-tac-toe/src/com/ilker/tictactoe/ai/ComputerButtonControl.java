package com.ilker.tictactoe.ai;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.ilker.tictactoe.Assets;
import com.ilker.tictactoe.Board;
import com.ilker.tictactoe.TicTacToe;
import com.ilker.tictactoe.screens.ResultScreen;

public class ComputerButtonControl {

	public static void clickButton(int index,HashMap<Integer,TextButton> buttonsMap,Board board){
		
		TextureAtlas atlas = Assets.manager.get(Assets.atlasCell);
		Skin skin = new Skin(Gdx.files.internal("ui/gameSkin.json"),atlas);
		BitmapFont blackFont = Assets.manager.get(Assets.fontBlack);
		
		TextButtonStyle buttonStyle2 = new TextButtonStyle();
		buttonStyle2.up = skin.getDrawable("empty");
		buttonStyle2.checked = skin.getDrawable("o");
		buttonStyle2.pressedOffsetX = 1;
		buttonStyle2.pressedOffsetY = -1;
		buttonStyle2.font = blackFont;
		
		TextButton button = buttonsMap.get(index);
		button.setStyle(buttonStyle2);
		button.setChecked(true);
		button.setDisabled(true);
		if(board.isGameOver()){
			Gdx.app.log(TicTacToe.LOG, "Game Over");
			if(board.getWinner() != null){
				Gdx.app.log(TicTacToe.LOG, "Computer WON");
				((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Computer WON"));
			}else{
				Gdx.app.log(TicTacToe.LOG, "Game Draw");
				((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
			}
		}
		
	}

}
