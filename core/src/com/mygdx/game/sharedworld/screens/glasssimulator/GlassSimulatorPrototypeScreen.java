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

    Music music;
    OrthographicCamera camera;

    public GlassSimulatorPrototypeScreen(final MyGdxGame backInst) {
        game = backInst;

        // get some dimensions
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        // set camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.position.set(0, 0, 0);
        camera.update();

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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the camera
        camera.update();
        // batch drawing
        game.batch.begin();

        // print text
        String text3 = "GLASS SIMUOATOR LOL";
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, text3, 0, 0);  // draw the text


        game.batch.end();
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
