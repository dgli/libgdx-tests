package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.MyGdxGame;

/**
 * Created by dgli on 15-01-02.
 */
public class GlobalConsole extends Stage {
    MyGdxGame game;

    Skin skin;

    public GlobalConsole(MyGdxGame game){
        this.game = game;

        // setup the console
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Window window = new Window("Debug Console", skin);

        window.setSize(300, 300);
        window.getButtonTable().add(new TextButton("X", skin)).height(window.getPadTop());
        window.setPosition(0, 0);
        window.defaults().spaceBottom(10);
        //window.pack();

        // stage.addActor(new Button("Behind Window", skin));
        addActor(window);

        setDebugAll(true);
    }



}

