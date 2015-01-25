package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dgli on 24/01/15.
 */
public class GSRaySource {
    Vector2 position;
    float direction;
    GSIRayEmitter sourceContainingObject;
    Color baseColor;

    GSRayTrajectory cacheTrajectory;

    public GSRaySource(Vector2 position, float direction, GSIRayEmitter parent, Color baseColor){
        this.position = position;
        this.direction = direction;
        this.baseColor = baseColor;

        sourceContainingObject = parent;
    }
}
