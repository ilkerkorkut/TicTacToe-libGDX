package com.ilker.tictactoe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ilker.tictactoe.TicTacToe;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "tic-tac-toe";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new TicTacToe(), cfg);
	}
}
