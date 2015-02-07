package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

/**
 * Created by dgli on 26/01/15.
 */
public class RayFront {

    Vector2 rayStart;
    Vector2 direction;
    float intensity;
    GSOInterfaceSegment fromSegment;

    public RayFront(Vector2 rayStart, Vector2 direction, float intensity,
                    GSOInterfaceSegment from) {
        this.rayStart = rayStart;
        this.direction = direction;
        this.intensity = intensity;
        this.fromSegment = from;
    }



    public GSOInterfaceSegment getFromSegment() {
        return fromSegment;
    }

    public Vector2 getRayStart() {
        return rayStart;
    }

    public void setRayStart(Vector2 rayStart) {
        this.rayStart = rayStart;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

}
