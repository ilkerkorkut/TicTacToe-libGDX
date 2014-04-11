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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ilker.tictactoe.Assets;

public class ResultScreen implements Screen{
	
	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private BitmapFont blackFont;
	private Label heading;
	private Label again;
	private SpriteBatch batch;
	private Texture background;
	private Sprite sprite;
	private String winner;
	
	public ResultScreen(String winner){
		this.winner = winner;
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
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = Assets.manager.get(Assets.atlasCell);
		skin = new Skin(Gdx.files.internal("ui/gameSkin.json"),atlas);
		blackFont = Assets.manager.get(Assets.fontBlack);
		
		heading = new Label("GAME RESULT",skin);
		again = new Label("Click or Touch here to play again",skin);
		batch = new SpriteBatch();
		background = Assets.manager.get(Assets.background);
		background.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(background);
		sprite.setColor(1, 1, 1, 1);
		sprite.setX(Gdx.graphics.getWidth() / 2 - (sprite.getWidth() / 2));
		sprite.setY(Gdx.graphics.getHeight() / 2 - (sprite.getHeight() / 2));
		
		again.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
		
		table = new Table(skin);
		
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		table.align(Align.center).align(Align.top).padTop(100);
		
		table.add(heading).right().padBottom(100).row();
		table.add(winner).row();
		table.add(again).padTop(100);
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
