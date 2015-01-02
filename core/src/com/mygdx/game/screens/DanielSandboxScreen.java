package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.MyGdxGame;

/**
 * Created by dgli on 01/01/15.
 */
public class DanielSandboxScreen implements Screen{

    MyGdxGame game;


    public DanielSandboxScreen(final MyGdxGame backInst) {
        game = backInst;

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to henry's sandbox!!!! ", 100, 150);
        game.font.draw(game.batch, "Henry's sexlife now belongs to this screen. SPACE DICKS", 100, 100);
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

    }
}
