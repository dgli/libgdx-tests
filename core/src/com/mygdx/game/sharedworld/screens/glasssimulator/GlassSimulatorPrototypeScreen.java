package com.mygdx.game.sharedworld.screens.glasssimulator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Segment;
import com.mygdx.game.MyGdxGame;

import com.mygdx.game.sharedworld.screens.MainMenuScreen;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOLaserPointer;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSOpticsSimulationEnvironment;

/**
 * Created by dgli on 01/01/15.
 */
public class GlassSimulatorPrototypeScreen implements Screen, InputProcessor {

    MyGdxGame game;

    GSOpticsSimulationEnvironment simulationEnvironment;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    Music music;


    float testCounter = 0;
    float testSpread = 0;
    int frameCounter = 0;

    Vector2 mouseVec = new Vector2(0, 0);

    GSOLaserPointer testLaserPointer;


    public GlassSimulatorPrototypeScreen(final MyGdxGame backInst) {
        game = backInst;

        // camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = game.batch;

        // get music
        music = Gdx.audio.newMusic(Gdx.files.internal("music/superslambros.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        //music.play();

        // input events
        game.requestInputFocus(this);

        // simulation environment
        simulationEnvironment = new GSOpticsSimulationEnvironment();

        testLaserPointer = new GSOLaserPointer(new Vector2(300, 300), new Vector2(1, 0));

        simulationEnvironment.addObject(testLaserPointer);
        simulationEnvironment.addObject(new GSOInterfaceSegment(new Vector2(100, 500), new Vector2(0, 100), 1, 1));
        simulationEnvironment.addObject(new GSOInterfaceSegment(new Vector2(100, 300), new Vector2(300, 0), 1, 1));
        simulationEnvironment.addObject(new GSOInterfaceSegment(new Vector2(50, 200), new Vector2(200, 100), 1, 1));

    }


    @Override
    public void show() {

    }


    @Override
    public void render(float deltaTime) {

        frameCounter++;
        if(frameCounter%20 != 0){
            //return;
        }

        //angle control
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) testCounter += deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) testCounter -= deltaTime;

        testLaserPointer.setDirection(new Vector2((float)Math.cos(testCounter), (float)Math.sin(testCounter)));

        //spread control
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) testSpread += deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) testSpread -= deltaTime;




        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

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

        sr.setColor(1f, 1f, 0, 1f);
        sr.setProjectionMatrix(camera.combined);

        sr.begin(ShapeRenderer.ShapeType.Filled);

        simulationEnvironment.updateEnvironmentRayTrajectories();
        simulationEnvironment.drawObjectShapes(sr);

        // draw some ray testers
        Vector2 start = new Vector2(300, 300);
        Vector2 end = mouseVec;
        Vector2 mid = start.cpy().add(end).scl(0.5f);

        float direction = end.cpy().sub(start).angleRad() - (float)Math.PI/2f;

        //sr.line(start, end);
        //sr.circle(mid.x, mid.y, 2);
        //sr.line(mid.x, mid.y, mid.x + 50 * (float)Math.cos(direction), mid.y + 50 * (float)Math.sin(direction));


        sr.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();

        // draw some game fonts
        game.font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond() + "  Laser Angle: " + testCounter, 50, 50);

        batch.end();



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
        Vector3 unprojected = camera.unproject(new Vector3(screenX, screenY, 0));

        mouseVec.x = unprojected.x;
        mouseVec.y = unprojected.y;

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
