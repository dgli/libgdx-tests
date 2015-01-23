package com.mygdx.game.sharedworld.screens.glasssimulator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.MyGdxGame;

import com.mygdx.game.sharedworld.screens.MainMenuScreen;

import java.util.*;

/**
 * Created by dgli on 01/01/15.
 */
public class GlassSimulatorPrototypeScreen implements Screen, InputProcessor {

    MyGdxGame game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    Music music;

    float counter = 0;

    public GlassSimulatorPrototypeScreen(final MyGdxGame backInst) {
        game = backInst;

        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = game.batch;

        // get music
        music = Gdx.audio.newMusic(Gdx.files.internal("henry/weedrunner/Athlweedic.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        // input events
        game.requestInputFocus(this);

    }


    @Override
    public void show() {

    }


    @Override
    public void render(float deltaTime) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // enable additive blending
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops

        ShapeRenderer sr = new ShapeRenderer();
        sr.setColor(1f, 1f, 0, 0.5f);
        sr.setProjectionMatrix(camera.combined);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rectLine(100, 100, 200, 200, 5);
        sr.rectLine(100, 200, 200, 100, 5);
        sr.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();

        // draw some game fonts
        game.font.draw(batch, "Hello!" + counter, 50, 50);

        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) counter += deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) counter -= deltaTime;


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        music.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            music.stop();
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
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
