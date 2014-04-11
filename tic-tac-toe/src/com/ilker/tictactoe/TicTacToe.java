package com.ilker.tictactoe;

import com.badlogic.gdx.Game;
import com.ilker.tictactoe.screens.Splash;

public class TicTacToe extends Game {
	
	public static final String LOG = "Tic Tac Toe";
	
	@Override
	public void create() {
		Assets.load();
		while(!Assets.manager.update()){
			System.out.println(Assets.manager.getProgress() * 100 + "%");
		}
		Assets.manager.finishLoading();
		setScreen(new Splash(this));
	}

	@Override
	public void dispose() {
		Assets.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
