package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

/**
 * Created by dgli on 01/01/15.
 */
public class HenrySandboxScreen implements Screen, InputProcessor {

    MyGdxGame game;

    TextureAtlas textureAtlas;
    Animation animation;
    private float elapsedTime = 0;
    private int snoopX = 512;
    private int snoopY = 512;

    // key holds
    boolean rightHold = false;
    boolean leftHold = false;
    boolean upHold = false;
    boolean downHold = false;

    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMap tiledMap;
        TiledMapTileLayer background;
        TiledMapTileLayer foreground;
    Music music;

    public HenrySandboxScreen(final MyGdxGame backInst) {
        game = backInst;

        // import the sprite
        textureAtlas = new TextureAtlas(Gdx.files.internal("henry/pack.atlas"));
        animation = new Animation(1/8f, textureAtlas.getRegions());

        // get some dimensions
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        int snoopWidth = animation.getKeyFrame(0).getRegionWidth();
        int snoopHeight = animation.getKeyFrame(0).getRegionHeight();

        // import the map and its layers
        tiledMap = new TmxMapLoader().load("henry/map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, game.batch);
        background = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        foreground = (TiledMapTileLayer) tiledMap.getLayers().get(1);

        // map dimensions
        int mapWidth = tiledMap.getProperties().get("width", Integer.class) * 32;
        int mapHeight = tiledMap.getProperties().get("height", Integer.class) * 32;

        // set sprite coordinates
        snoopX = mapWidth/2 - snoopWidth/2;
        snoopY = mapHeight/2 - snoopHeight/2;

        // set camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.position.set(mapWidth/2, mapHeight/2, 0);
        camera.update();

        // get music
        music = Gdx.audio.newMusic(Gdx.files.internal("henry/Athlweedic.mp3"));
        music.setLooping(true);
        music.play();

        // input events
        Gdx.input.setInputProcessor(this);

        System.out.println("SNOOP DIMENSIONS: " + snoopWidth + ", " + snoopHeight);
        System.out.println("MAP DIMENSIONS: " + mapWidth + ", " + mapHeight);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(leftHold) {
            snoopX--;
            camera.translate(-1, 0);
        }
        if(rightHold) {
            snoopX++;
            camera.translate(1, 0);
        }
        if(upHold) {
            snoopY++;
            camera.translate(0, 1);
        }
        if(downHold) {
            snoopY--;
            camera.translate(0, -1);
        }

        camera.update();
        tiledMapRenderer.setView(camera);
        //tiledMapRenderer.render();

        game.batch.begin();

        tiledMapRenderer.renderTileLayer(background);

        elapsedTime += Gdx.graphics.getDeltaTime();
        game.batch.draw(animation.getKeyFrame(elapsedTime, true), snoopX, snoopY);

        tiledMapRenderer.renderTileLayer(foreground);

        game.font.draw(game.batch, "Welcome to Henry's weedbox!!!! ", 15, 15);
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
        if(keycode == Input.Keys.LEFT)
            leftHold = true;
        if(keycode == Input.Keys.RIGHT)
            rightHold = true;
        if(keycode == Input.Keys.UP)
            upHold = true;
        if(keycode == Input.Keys.DOWN)
            downHold = true;

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            leftHold = false;
        if(keycode == Input.Keys.RIGHT)
            rightHold = false;
        if(keycode == Input.Keys.UP)
            upHold = false;
        if(keycode == Input.Keys.DOWN)
            downHold = false;

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
