package com.ilker.tictactoe;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

		public static final AssetManager manager = new AssetManager();
		
		public static final AssetDescriptor<Texture> background = new AssetDescriptor<Texture>("img/game_background.png",Texture.class);
		public static final AssetDescriptor<Texture> logo = new AssetDescriptor<Texture>("img/logo.png",Texture.class);
		public static final AssetDescriptor<TextureAtlas> atlasCell = new AssetDescriptor<TextureAtlas>("ui/cell.pack",TextureAtlas.class);
		public static final AssetDescriptor<TextureAtlas> atlasButton = new AssetDescriptor<TextureAtlas>("ui/button.pack",TextureAtlas.class);
		public static final String gameSkin = "ui/gameSkin.json";
		public static final String menuSkin = "ui/menuSkin.json";
		public static final AssetDescriptor<BitmapFont> fontBlack = new AssetDescriptor<BitmapFont>("font/black.fnt", BitmapFont.class);
		
		public static void load(){
			manager.load(background);
			manager.load(logo);
			manager.load(atlasCell);
			manager.load(atlasButton);
			
			manager.load(fontBlack);
		}
	
		public static void dispose(){
			
		}
}
