package com.mygdx.game.danielworld.screens.mountainclimber;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.danielworld.screens.mountainclimber.objects.GrabPointObject;
import com.mygdx.game.danielworld.screens.mountainclimber.objects.PlayerObject;

import java.util.ArrayList;

/**
 * Created by dgli on 02/01/15.
 */
public class MCGameScreen  implements Screen, InputProcessor {

    MyGdxGame game;

    Music music;
    Stage stage;

    PlayerObject player;
    ArrayList<GrabPointObject> grabLocations;


    public MCGameScreen(final MyGdxGame backInst) {
        game = backInst;

        // get music
        music = Gdx.audio.newMusic(Gdx.files.internal("music/harmony.mp3"));
        music.setLooping(true);
        music.play();

        // input events
        stage = new Stage(new ScreenViewport());

        // make game objects
        player = new PlayerObject();
        grabLocations = new ArrayList<GrabPointObject>();

        grabLocations.add(new GrabPointObject(new Vector2(100, 100), new Vector2(100, 20)));
    }


    @Override
    public void show() {
        InputMultiplexer inputMux = new InputMultiplexer();
        inputMux.addProcessor(this);
        inputMux.addProcessor(stage);

        game.requestInputFocus(inputMux);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the player
        player.render(game.batch);

        for(GrabPointObject g : grabLocations){
            g.render(game.batch);
        }

        // draw game debugging information
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Lumbridge ", 15, 15);
        game.batch.end();

        // draw GUI stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void hide() {
        music.pause();
    }

    @Override
    public void dispose() {
        music.dispose();
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            game.setScreen(new MCMenuScreen(game));
            dispose();
            return true;
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
