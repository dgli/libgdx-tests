package com.mygdx.game.danielworld.screens.mountainclimber.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dgli on 15-01-07.
 */
public abstract class AbstractInteractiveGameObject extends AbstractGameObject {
    Vector2 velocity;
    float mass;

    public AbstractInteractiveGameObject(){

    }

    public void update(float deltaTime){
        position.add(velocity);
    }
}
