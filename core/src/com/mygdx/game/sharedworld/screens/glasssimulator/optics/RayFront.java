package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

/**
 * Created by dgli on 26/01/15.
 */
public class RayFront {

    Vector2 rayStart;
    Vector2 direction;
    float startIntensity;
    float fadeOutDistance;
    float fadingCoefficient;
    GSOInterfaceSegment fromSegment;

    public RayFront(Vector2 rayStart, Vector2 direction, float startIntensity,
                    float fadingCoefficient, GSOInterfaceSegment from) {
        this.rayStart = rayStart;
        this.direction = direction;
        this.startIntensity = startIntensity;
        this.fromSegment = from;

        if(fadingCoefficient > 0.00000001f) {
            this.fadeOutDistance = 1 / fadingCoefficient;
        }else{
            this.fadeOutDistance = -1;
        }

        this.fadingCoefficient = fadingCoefficient;
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

    public float getStartIntensity() {
        return startIntensity;
    }

    public void setStartIntensity(float startIntensity) {
        this.startIntensity = startIntensity;
    }

    public float getFadeOutDistance() {
        return fadeOutDistance;
    }

    public void setFadeOutDistance(float fadeOutDistance) {
        this.fadeOutDistance = fadeOutDistance;
    }

    public float getFadingCoefficient() {
        return fadingCoefficient;
    }

    public void setFadingCoefficient(float fadingCoefficient) {
        this.fadingCoefficient = fadingCoefficient;
    }
}
