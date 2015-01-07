package com.mygdx.game.danielworld.screens.mountainclimber.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dgli on 15-01-07.
 */
public class PlayerObject extends AbstractGameObject {
    private ShapeRenderer renderer = new ShapeRenderer();


    public PlayerObject(){
        position = new Vector2(300, 300);
        dimension = new Vector2(50, 50);
        origin = new Vector2(0, 0);
        scale = new Vector2(1, 1);
        rotation = 0;

    }

    @Override
    public void render(SpriteBatch batch){

        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.translate(position.x, position.y, 0);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLUE);
        renderer.rect(0, 0, dimension.x, dimension.y);
        renderer.end();

    }
}
