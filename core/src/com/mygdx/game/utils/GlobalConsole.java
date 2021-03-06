package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.game.MyGdxGame;

/**
 * Created by dgli on 15-01-02.
 */
public class GlobalConsole extends Stage {
    MyGdxGame game;

    Skin skin;

    boolean enabled;

    InputMultiplexer inputGate;

    public GlobalConsole(MyGdxGame game){
        this.game = game;

        // setup the console
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        inputGate = new InputMultiplexer();

        Window window = new Window("Debug Console", skin);

        window.setSize(300, 300);
        //window.getButtonTable().add(new TextButton("X", skin)).height(window.getPadTop()-4).pad(2);

        window.row();
        TextArea messageBox = new TextArea("We'll make this into a nice debug console in the future", skin);
        messageBox.setDisabled(true);
        window.add(messageBox).size(290, 250).padTop(12);

        window.row();
        TextField textField = new TextField("", skin);
        //textField.setSize(200, 200);
        window.add(textField).width(290);

        window.setPosition(0, 0);
        window.defaults().spaceBottom(10);
        //window.pack();

        // stage.addActor(new Button("Behind Window", skin));
        addActor(window);

        //setDebugAll(true);
        setEnabled(false);
    }

    public void toggleEnabled(){
        setEnabled(!enabled);
    }

    public void setEnabled(boolean e){
        enabled = e;

        if(enabled){
            inputGate.addProcessor(this);
        }else{
            inputGate.clear();
        }
    }

    public void draw(){
        if(enabled) super.draw();
    }

    public void act(float delta){
        if(enabled) super.act(delta);
    }

    public void act(){
        if(enabled) super.act();
    }

    public InputProcessor getInputReceiver(){
        return inputGate;
    }

}

