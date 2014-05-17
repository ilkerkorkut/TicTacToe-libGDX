package com.ilker.tictactoe.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ilker.tictactoe.Assets;
import com.ilker.tictactoe.Board;
import com.ilker.tictactoe.TicTacToe;
import com.ilker.tictactoe.ai.ComputerButtonControl;
import com.ilker.tictactoe.ai.ComputerPlayer;
import com.ilker.tictactoe.ai.HumanPlayer;
import com.ilker.tictactoe.ai.Player;

public class GameScreen implements Screen{

	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private BitmapFont blackFont;
	private Label heading;
	private SpriteBatch batch;
	private Texture background;
	private Sprite sprite;
	private TicTacToe game;
	
	private static Player humanPlayer;
	private static Player computerPlayer;
	private static Board board;
	
	public GameScreen(TicTacToe game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = Assets.manager.get(Assets.atlasCell);
		skin = new Skin(Gdx.files.internal("ui/gameSkin.json"),atlas);
		blackFont = Assets.manager.get(Assets.fontBlack);
		
		heading = new Label("PLAYING GAME",skin);
		batch = new SpriteBatch();
		background = Assets.manager.get(Assets.background);
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(background);
		sprite.setColor(1, 1, 1, 1);
		sprite.setX(Gdx.graphics.getWidth() / 2 - (sprite.getWidth() / 2));
		sprite.setY(Gdx.graphics.getHeight() / 2 - (sprite.getHeight() / 2));
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.getDrawable("empty");
		buttonStyle.checked = skin.getDrawable("x");
		buttonStyle.pressedOffsetX = 1;
		buttonStyle.pressedOffsetY = -1;
		buttonStyle.font = blackFont;
		
		final TextButtonStyle buttonStyle2 = new TextButtonStyle();
		buttonStyle2.up = skin.getDrawable("empty");
		buttonStyle2.checked = skin.getDrawable("o");
		buttonStyle2.pressedOffsetX = 1;
		buttonStyle2.pressedOffsetY = -1;
		buttonStyle2.font = blackFont;
		
		final TextButton button1 = new TextButton(" ",buttonStyle);
		final TextButton button2 = new TextButton(" ",buttonStyle);
		final TextButton button3 = new TextButton(" ",buttonStyle);
		final TextButton button4 = new TextButton(" ",buttonStyle);
		final TextButton button5 = new TextButton(" ",buttonStyle);
		final TextButton button6 = new TextButton(" ",buttonStyle);
		final TextButton button7 = new TextButton(" ",buttonStyle);
		final TextButton button8 = new TextButton(" ",buttonStyle);
		final TextButton button9 = new TextButton(" ",buttonStyle);
		
		button1.setName("1");button2.setName("2");button3.setName("3");
		button4.setName("4");button5.setName("5");button6.setName("6");
		button7.setName("7");button8.setName("8");button9.setName("9");
		
		final List<TextButton> buttons = new ArrayList<TextButton>();
		buttons.add(button1);buttons.add(button2);buttons.add(button3);
		buttons.add(button4);buttons.add(button5);buttons.add(button6);
		buttons.add(button7);buttons.add(button8);buttons.add(button9);
		
		final HashMap<Integer,TextButton> buttonsMap = new HashMap<Integer,TextButton>();
		buttonsMap.put(0, button1);buttonsMap.put(1, button2);buttonsMap.put(2, button3);
		buttonsMap.put(3, button4);buttonsMap.put(4, button5);buttonsMap.put(5, button6);
		buttonsMap.put(6, button7);buttonsMap.put(7, button8);buttonsMap.put(8, button9);
		
		
		humanPlayer = new HumanPlayer();
		computerPlayer = new ComputerPlayer();
		board = new Board();
		board.setOpponents(humanPlayer, computerPlayer);
		
		button1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button1.isDisabled()){
					board.makeMove(0, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button1.setDisabled(true);
			}
		});

		button2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button2.isDisabled()){
					board.makeMove(1, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button2.setDisabled(true);
			}
		});
		
		button3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button3.isDisabled()){
					board.makeMove(2, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
							
						}
					}
				}
				button3.setDisabled(true);
			}
		});
		
		button4.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button4.isDisabled()){
					board.makeMove(3, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button4.setDisabled(true);
			}
		});
		
		button5.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button5.isDisabled()){
					board.makeMove(4, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button5.setDisabled(true);
			}
		});
		
		button6.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button6.isDisabled()){
					board.makeMove(5, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button6.setDisabled(true);
			}
		});
		
		button7.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button7.isDisabled()){
					board.makeMove(6, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button7.setDisabled(true);
			}
		});
		
		button8.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button8.isDisabled()){
					board.makeMove(7, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button8.setDisabled(true);
			}
		});
		
		button9.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!button9.isDisabled()){
					board.makeMove(8, humanPlayer);
					if(!board.isGameOver()){
						int computerMove = computerPlayer.play(board);
						board.makeMove(computerMove, computerPlayer);
						ComputerButtonControl.clickButton(computerMove, buttonsMap,board);
					}
					else{
						if(board.getWinner() == null){
							Gdx.app.log(TicTacToe.LOG, "Game DRAW");
							((Game) Gdx.app.getApplicationListener()).setScreen(new ResultScreen("Game DRAW"));
						}
					}
				}
				button9.setDisabled(true);
			}
		});
		
		table = new Table(skin);
		
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		table.align(Align.center).align(Align.top).padTop(100);
		
		table.add(heading).right().padBottom(100).row();
		table.add(button1).right();
		table.add(button2);
		table.add(button3).row();
		table.add(button4).right();
		table.add(button5);
		table.add(button6).row();
		table.add(button7).right();
		table.add(button8);
		table.add(button9);
		
		stage.addActor(table);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
