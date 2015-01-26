package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

/**
 * Created by dgli on 25/01/15.
 */
public class GSInterfaceCollisionResult {
    Vector2 collisionPoint;
    RaySegment incidenceRaySegment;
    RayFront reflectedRayFront;
    RayFront refractedRayFront;
    GSOInterfaceSegment collidedInterface;

    public GSInterfaceCollisionResult(GSOInterfaceSegment collidedInterface){
        this.collidedInterface = collidedInterface;
    }


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

    public RayFront getReflectedRayFront() {
        return reflectedRayFront;
    }

    public void setReflectedRayFront(RayFront reflectedRayFront) {
        this.reflectedRayFront = reflectedRayFront;
    }

    public RayFront getRefractedRayFront() {
        return refractedRayFront;
    }

    public void setRefractedRayFront(RayFront refractedRayFront) {
        this.refractedRayFront = refractedRayFront;
    }

    public GSOInterfaceSegment getCollidedInterface() {
        return collidedInterface;
    }

}
