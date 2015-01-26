package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dgli on 25/01/15.
 */
public class GSInterfaceCollisionResult {
    Vector2 collisionPoint;
    RaySegment incidenceRaySegment;
    RaySegment reflectedRaySegment;
    RaySegment refractedRaySegment;


    public Vector2 getCollisionPoint() {
        return collisionPoint;
    }

    public void setCollisionPoint(Vector2 collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    public RaySegment getIncidenceRaySegment() {
        return incidenceRaySegment;
    }

    public void setIncidenceRaySegment(RaySegment incidenceRaySegment) {
        this.incidenceRaySegment = incidenceRaySegment;
    }

    public RaySegment getReflectedRaySegment() {
        return reflectedRaySegment;
    }

    public void setReflectedRaySegment(RaySegment reflectedRaySegment) {
        this.reflectedRaySegment = reflectedRaySegment;
    }

    public RaySegment getRefractedRaySegment() {
        return refractedRaySegment;
    }

    public void setRefractedRaySegment(RaySegment refractedRaySegment) {
        this.refractedRaySegment = refractedRaySegment;
    }

}
