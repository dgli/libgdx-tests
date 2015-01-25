package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dgli on 24/01/15.
 */
public class GSRaySource {
    Vector2 position;
    float direction;
    GSIRayEmitter sourceContainingObject;

    public GSRaySource(Vector2 position, float direction, GSIRayEmitter parent){
        this.position = position;
        this.direction = direction;

        sourceContainingObject = parent;
    }
}
