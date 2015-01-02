package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.screens.HenrySandboxScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class MyGdxGame extends Game implements ApplicationListener {

//	Skin skin;
//	Stage stage;
//	Texture texture1;
//	Texture texture2;
//	Label fpsLabel;

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create () {

		batch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));

		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}



	public void resume () {
	}


	public void pause () {
	}


}
