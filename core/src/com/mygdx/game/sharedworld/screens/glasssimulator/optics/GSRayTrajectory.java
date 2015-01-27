package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

import java.util.ArrayList;

/**
 * Created by dgli on 25/01/15.
 */
public class GSRayTrajectory {


    /**
     * List of uninterrupted segments
     */
    ArrayList<RaySegment> segmentList;
    ArrayList<GSInterfaceCollisionResult> rayCollisions;
    Color baseColor;

    public GSRayTrajectory (Color baseColor){
        this.baseColor = baseColor;
        segmentList = new ArrayList<RaySegment>();
        rayCollisions = new ArrayList<GSInterfaceCollisionResult>();
    }

    public void drawTrajectoryShape(ShapeRenderer sr){
        for(RaySegment r : segmentList){
            Color startColor = new Color(baseColor.r, baseColor.g, baseColor.b, r.startIntensity);
            Color endColor = new Color(baseColor.r, baseColor.g, baseColor.b, r.endIntensity);
            sr.line(r.segmentStart.x, r.segmentStart.y, r.segmentEnd.x, r.segmentEnd.y, startColor, endColor);
        }

        for(GSInterfaceCollisionResult r : rayCollisions){
            sr.setColor(Color.GRAY);
            sr.line(r.collisionPoint, r.collisionPoint.cpy().add(r.getCollidedInterface().getNormal().cpy().scl(5)));
        }
    }

    public void addUninterruptedSegment(Vector2 start, Vector2 end,
                                        float startInten, float endInten,
                                        GSOInterfaceSegment collidedInterface){
        segmentList.add(new RaySegment(start, end, startInten, endInten, collidedInterface));
    }

    public void addUninterruptedSegment(RaySegment seg){
        segmentList.add(seg);
    }

    public void addCollisionResult(GSInterfaceCollisionResult r){
        rayCollisions.add(r);
    }
}
