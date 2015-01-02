package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Created by dgli on 01/01/15.
 */
public class MainMenuScreen implements Screen {
    MyGdxGame game;

    Skin skin;
    Stage stage;

    public MainMenuScreen(MyGdxGame backInst){
        game = backInst;
    }

    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Button goToHenrysTestsButton = new TextButton("Henry's Sandbox", skin);
        Button goToDanielsTestsButton = new TextButton("Daniel's Sandbox", skin);
        Button exitButton = new TextButton("Exit Game", skin);

        goToHenrysTestsButton.pad(20);
        goToDanielsTestsButton.pad(20);
        exitButton.pad(20);

        // my own table
        Table table = new Table();

        table.row();
        table.add(goToHenrysTestsButton).pad(25);
        table.row();
        table.add(goToDanielsTestsButton).pad(25);
        table.row();
        table.add(exitButton).pad(25);

        table.setFillParent(true);
        stage.addActor(table);

        goToHenrysTestsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HenrySandboxScreen(game));
                dispose();
            }
        });

        goToDanielsTestsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new DanielSandboxScreen(game));
                dispose();
            }
        });

        exitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //fpsLabel.setText("fps: " + Gdx.graphics.getFramesPerSecond());

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
