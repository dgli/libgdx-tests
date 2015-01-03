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
import com.mygdx.game.utils.GlobalConsole;

public class MyGdxGame extends Game implements ApplicationListener, InputProcessor {

	public SpriteBatch batch;
	public BitmapFont font;
	public Screen currentActiveScreen;

	public InputMultiplexer inputMux;
	public GlobalConsole cc;

	public void requestInputFocus(InputProcessor i){
		inputMux = new InputMultiplexer();
		inputMux.addProcessor(this);
		inputMux.addProcessor(cc.getInputReceiver());
		inputMux.addProcessor(i);

		Gdx.input.setInputProcessor(inputMux);
	}

	@Override
	public void create () {
		// make global console
		cc = new GlobalConsole(this);

		batch = new SpriteBatch();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();

		Gdx.input.setCatchBackKey(true);

		this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();

		cc.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		cc.draw();
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


	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.GRAVE){
			cc.toggleEnabled();
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
