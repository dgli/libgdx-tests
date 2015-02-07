package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dgli on 24/01/15.
 */
public class GSRaySource {

    Vector2 position;
    Vector2 direction;
    GSIRayEmitter sourceContainingObject;
    Color baseColor;

    GSRayTrajectory cacheTrajectory;

    public GSRaySource(Vector2 position, Vector2 direction, GSIRayEmitter parent, Color baseColor){
        this.position = position;
        this.direction = direction;
        this.baseColor = baseColor;

        sourceContainingObject = parent;
    }


    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public GSRayTrajectory getCacheTrajectory() {
        return cacheTrajectory;
    }

    public void setCacheTrajectory(GSRayTrajectory cacheTrajectory) {
        this.cacheTrajectory = cacheTrajectory;
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

    public GSIRayEmitter getSourceContainingObject() {
        return sourceContainingObject;
    }

    public void setSourceContainingObject(GSIRayEmitter sourceContainingObject) {
        this.sourceContainingObject = sourceContainingObject;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }
}
