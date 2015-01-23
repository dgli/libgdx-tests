package com.mygdx.game.danielworld.screens.mountainclimber.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dgli on 15-01-07.
 */
public class HookObject extends AbstractInteractiveGameObject{
    private ShapeRenderer renderer = new ShapeRenderer();


    public HookObject(Vector2 position, Vector2 velocity, float rotation){
        this.position = position;
        this.dimension = new Vector2(5, 5);
        this.velocity = velocity;
        origin = new Vector2(0, 0);
        scale = new Vector2(1, 1);
        this.rotation = rotation;

    }

    @Override
    public void render(SpriteBatch batch){

        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.translate(0, 0, 0);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.rect(position.x, position.y, dimension.x, dimension.y);
        renderer.end();

    }
}
