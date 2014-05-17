package com.ilker.tictactoe.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ilker.tictactoe.Assets;
import com.ilker.tictactoe.TicTacToe;

public class MainMenu implements Screen{

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonPlay;
	private BitmapFont blackFont;
	private Label heading;
	private SpriteBatch batch;
	private Texture background;
	private Sprite sprite;
	private TicTacToe game;
	
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
		
	}

	@Override
	public void show() {
		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		
		atlas = Assets.manager.get(Assets.atlasButton);
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		blackFont = Assets.manager.get(Assets.fontBlack);
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("play");
		textButtonStyle.down = skin.getDrawable("play2");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = blackFont;
		textButtonStyle.font.scale(1);
		
		batch = new SpriteBatch();
		background = Assets.manager.get(Assets.background);
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(background);
		sprite.setColor(1, 1, 1, 1);
		sprite.setX(Gdx.graphics.getWidth() / 2 - (sprite.getWidth() / 2));
		sprite.setY(Gdx.graphics.getHeight() / 2 - (sprite.getHeight() / 2));
		
		heading = new Label("TIC TAC TOE",skin);
		
		ButtonStyle buttonStyleX = new ButtonStyle();
		buttonStyleX.up = skin.getDrawable("select_x");
		buttonStyleX.pressedOffsetX = 1;
		buttonStyleX.pressedOffsetY = -1;
		
		ButtonStyle buttonStyleO = new ButtonStyle();
		buttonStyleO.up = skin.getDrawable("select_o");
		buttonStyleO.pressedOffsetX = 1;
		buttonStyleO.pressedOffsetY = -1;
		
		Button buttonX= new Button(buttonStyleX);
		Button buttonO= new Button(buttonStyleO);
		
		buttonPlay = new TextButton(" ", textButtonStyle);
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game));
			}
		});

		table.align(Align.center).align(Align.top).padTop(100);
		table.add(heading).padBottom(100).row();
		
		table.add(buttonPlay);
		
		stage.addActor(table);
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
