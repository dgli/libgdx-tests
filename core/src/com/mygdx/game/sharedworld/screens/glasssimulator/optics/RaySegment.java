package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

/**
 * Represents an uninterrupted ray segment.
 */
public class RaySegment{
    Vector2 segmentStart, segmentEnd;
    float length;
    float startIntensity, endIntensity;

    GSOInterfaceSegment collidedInterface;

    public RaySegment(Vector2 segStart, Vector2 segEnd, float startInten, float fadingCoefficient,
                      GSOInterfaceSegment collidedInterface) {
        segmentStart = segStart;
        segmentEnd = segEnd;
        startIntensity = startInten;
        this.collidedInterface = collidedInterface;
        length = segEnd.cpy().sub(segStart).len();

        // calculate the intensity of the ray by the time it went it's length.
        if(fadingCoefficient <= 0){
            endIntensity = startInten;
        }
        else{
            endIntensity = startInten - fadingCoefficient * length;
        }
    }

    public Vector2 getSegmentStart() {
        return segmentStart;
    }

    public Vector2 getSegmentEnd() {
        return segmentEnd;
    }

    public float getLength() {
        return length;
    }

    public float getStartIntensity() {
        return startIntensity;
    }

    public float getEndIntensity() {
        return endIntensity;
    }

    public GSOInterfaceSegment getCollidedInterface() {
        return collidedInterface;
    }

}