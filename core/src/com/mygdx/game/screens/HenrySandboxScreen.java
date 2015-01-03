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
import com.mygdx.game.MyGdxGame;

import java.util.*;

/**
 * Created by dgli on 01/01/15.
 */
public class HenrySandboxScreen implements Screen, InputProcessor {

    MyGdxGame game;

    TextureAtlas textureAtlas;
    Animation animation;

    private float elapsedTime = 0;
    private float snoopX = 512;
    private float snoopY = 512;
    private float snoopSpeed = 1;
    private float sanicSpeed = 4;

    // dimensions
    private int mapWidth;
    private int mapHeight;
    private int snoopWidth;
    private int snoopHeight;
    private int sanicDim = 100;

    // key holds
    boolean rightHold = false;
    boolean leftHold = false;
    boolean upHold = false;
    boolean downHold = false;

    // Misc
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    TiledMap tiledMap;
        TiledMapTileLayer background;
        TiledMapTileLayer foreground;
    Music music;
    Sound sfx, gg;
    Random rand = new Random();

    // Sanic
    Texture sanicTexture;
    Sprite sanicSprite;
    float sanicX;
    float sanicY;

    // game over
    boolean gameover = false;
    TextureAtlas bootyAtlas;
    Animation bootyAnim;

    // Power ups
    Texture powerupTexture;
    List<Powerup> powerupList = new LinkedList<Powerup>();
    List<Powerup> powerupRemoval = new LinkedList<Powerup>();
    boolean firstDone = false;
    boolean firstPass = false;

    public class Powerup {
        public float x;
        public float y;
        public Sprite sprite;

        public Powerup() {
            this.x = rand.nextInt(mapWidth);
            this.y = rand.nextInt(mapHeight);
            this.sprite = new Sprite(powerupTexture);
            sprite.setPosition(x, y);
        }
    }

    public HenrySandboxScreen(final MyGdxGame backInst) {
        game = backInst;

        // import the sprite
        textureAtlas = new TextureAtlas(Gdx.files.internal("henry/snoop.atlas"));
        animation = new Animation(1/8f, textureAtlas.getRegions());

        // get some dimensions
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        snoopWidth = animation.getKeyFrame(0).getRegionWidth();
        snoopHeight = animation.getKeyFrame(0).getRegionHeight();

        // import the map and its layers
        tiledMap = new TmxMapLoader().load("henry/map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, game.batch);
        background = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        foreground = (TiledMapTileLayer) tiledMap.getLayers().get(1);

        // map dimensions
        mapWidth = tiledMap.getProperties().get("width", Integer.class) * 32;
        mapHeight = tiledMap.getProperties().get("height", Integer.class) * 32;

        // set sprite coordinates
        snoopX = mapWidth/2f - snoopWidth/2f;
        snoopY = mapHeight/2f - snoopHeight/2f;

        // set camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.position.set(mapWidth/2f, mapHeight/2f, 0);
        camera.update();

        // get music
        music = Gdx.audio.newMusic(Gdx.files.internal("henry/Athlweedic.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();

        // get sfx
        sfx = Gdx.audio.newSound(Gdx.files.internal("henry/Nipah.mp3"));
        gg = Gdx.audio.newSound(Gdx.files.internal("henry/Aw.mp3"));

        // powerups sprite
        powerupTexture = new Texture(Gdx.files.internal("henry/pp.png"));
        for (int i = 0; i < 10; i++) {
            powerupList.add(new Powerup());
        }

        // sanic sprite
        sanicTexture = new Texture(Gdx.files.internal("henry/sanic.png"));
        sanicSprite = new Sprite(sanicTexture);
        sanicSprite.setCenter(mapWidth/2f, mapHeight/2f);
        sanicX = mapWidth/2;
        sanicY = mapHeight/2;

        // game over
        bootyAtlas = new TextureAtlas(Gdx.files.internal("henry/booty.atlas"));
        bootyAnim = new Animation(1/10f, bootyAtlas.getRegions());

        // input events
        game.requestInputFocus(this);

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

        // camera and sprite translations
        if (!gameover) {
            if (leftHold) {
                if (snoopX >= 0 - snoopWidth / 2) {
                    snoopX -= snoopSpeed;
                    camera.translate(-snoopSpeed, 0);
                }
            }
            if (rightHold) {
                if (snoopX < mapWidth - snoopWidth / 2) {
                    snoopX += snoopSpeed;
                    camera.translate(snoopSpeed, 0);
                }
            }
            if (upHold) {
                if (snoopY < mapHeight) {
                    snoopY += snoopSpeed;
                    camera.translate(0, snoopSpeed);
                }
            }
            if (downHold) {
                if (snoopY >= 0) {
                    snoopY -= snoopSpeed;
                    camera.translate(0, -snoopSpeed);
                }
            }
        }

        // update the camera
        camera.update();
        tiledMapRenderer.setView(camera);

        // batch drawing
        game.batch.begin();

        tiledMapRenderer.renderTileLayer(background);   // render the background

        // draw the animated sprite
        elapsedTime += Gdx.graphics.getDeltaTime();
        game.batch.draw(animation.getKeyFrame(elapsedTime, true), snoopX, snoopY);

        tiledMapRenderer.renderTileLayer(foreground);   // render the foreground (2nd layer)

        // draw power ups
        float snoopXmid = snoopX + snoopWidth/2;
        for (Powerup p : powerupList) {
            if (snoopXmid >= p.x && snoopXmid <= p.x + p.sprite.getRegionWidth()
                    && snoopY >= p.y && snoopY <= p.y + p.sprite.getRegionHeight()) {
                powerupRemoval.add(p);
                snoopSpeed += 0.05;
            }
            else
                p.sprite.draw(game.batch);
        }

        // remove used powerups
        for (Powerup p : powerupRemoval) {
            sfx.play(2.5f);

            powerupList.remove(p);
            powerupList.add(new Powerup());
        }
        powerupRemoval.clear();

        // activate sanic
        if (snoopSpeed >= 4.20)
            firstDone = true;

        // print text
        String text1;
        String text2;
        if (firstDone) {
            text1 = "Current SnoopSpeed: 4.20 No Smoke";
            text2 = "WOOP WOOP PULL OVER DAT ASS 2FAST";

            // switch music
            if (!firstPass) {
                music.stop();
                music = Gdx.audio.newMusic(Gdx.files.internal("henry/booty.mp3"));
                music.setLooping(true);
                music.setVolume(0.35f);
                music.play();
                firstPass = true;
            }
        }
        else {
            text1 = "Current SnoopSpeed: " + String.format("%.2f", snoopSpeed);
            text2 = "GET HIGHER!";
        }
        String text3 = "Weeds smoked: " + (int)((snoopSpeed - 1)*20);
        game.font.draw(game.batch, text1,
                camera.position.x - Gdx.graphics.getWidth()/2 + 15, camera.position.y - Gdx.graphics.getHeight()/2 + 25);  // draw the text
        game.font.draw(game.batch, text2,
                camera.position.x - Gdx.graphics.getWidth()/2 + 15, camera.position.y + Gdx.graphics.getHeight()/2 - 15);  // draw the text
        game.font.draw(game.batch, text3,
                camera.position.x + Gdx.graphics.getWidth()/2 - 150, camera.position.y - Gdx.graphics.getHeight()/2 + 25);  // draw the text

        // sanic chasing
        if (firstDone) {
            double translateX = 0;
            double translateY = 0;

            // edge case same x or y coords
            if (sanicX != snoopX && sanicY != snoopY) {
                double slope = Math.abs((sanicY - snoopY) / (sanicX - snoopX));

                translateX = Math.sqrt(Math.pow(sanicSpeed, 2) / (1 + Math.pow(slope, 2)));
                translateY = slope * translateX;

                if (sanicX - snoopX > 0)
                    translateX = 0 - translateX;
                if (sanicY - snoopY > 0)
                    translateY = 0 - translateY;
            }

            // draw and translate sanic
            sanicX += translateX;
            sanicY += translateY;
            sanicSprite.setCenter(sanicX, sanicY);
            sanicSprite.draw(game.batch);

            // game over initialize
            if (snoopX <= sanicX + 25 && snoopX >= sanicX - 25 && !gameover &&
                    snoopY <= sanicY + 25 && snoopY >= sanicY - 25) {
                gg.play(0.5f);
                gameover = true;
            }
        }

        // game over setting
        if (gameover) {
            String textG = "2SLOW M8 GAME OVER";
            game.font.draw(game.batch, textG,
                    camera.position.x - Gdx.graphics.getWidth()/8, camera.position.y - Gdx.graphics.getHeight()/3);
            int animW = bootyAnim.getKeyFrame(0).getRegionWidth();
            int animH = bootyAnim.getKeyFrame(0).getRegionHeight();
            game.batch.draw(bootyAnim.getKeyFrame(elapsedTime, true),
                    camera.position.x - animW/2 - Gdx.graphics.getWidth()/4, camera.position.y - animH/2);
            game.batch.draw(bootyAnim.getKeyFrame(elapsedTime, true),
                    camera.position.x - animW/2 + Gdx.graphics.getWidth()/4, camera.position.y - animH/2);
        }

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
        sfx.dispose();
        gg.dispose();
        textureAtlas.dispose();
        tiledMap.dispose();
        bootyAtlas.dispose();
        sanicTexture.dispose();
        powerupTexture.dispose();
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
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE){
            music.stop();
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }

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
        if(screenX < Gdx.graphics.getWidth()/2)
            leftHold = true;
        if(screenX > Gdx.graphics.getWidth()/2)
            rightHold = true;
        if(screenY > Gdx.graphics.getHeight()/2)
            upHold = true;
        if(screenY < Gdx.graphics.getHeight()/2)
            downHold = true;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX < Gdx.graphics.getWidth()/2)
            leftHold = false;
        if(screenX > Gdx.graphics.getWidth()/2)
            rightHold = false;
        if(screenY > Gdx.graphics.getHeight()/2)
            upHold = false;
        if(screenY < Gdx.graphics.getHeight()/2)
            downHold = false;

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
