package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

/**
 * Represents an uninterrupted ray segment.
 */
public class RaySegment{
    private Vector2 segmentStart, segmentEnd;
    private float length;
    private float intensity;

    GSOInterfaceSegment collidedInterface;

    public RaySegment(Vector2 segStart, Vector2 segEnd, float startInten,
                      GSOInterfaceSegment collidedInterface) {
        segmentStart = segStart;
        segmentEnd = segEnd;
        intensity = startInten;
        this.collidedInterface = collidedInterface;
        length = segEnd.cpy().sub(segStart).len();
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

    public float getIntensity() {
        return intensity;
    }

    public GSOInterfaceSegment getCollidedInterface() {
        return collidedInterface;
    }

}