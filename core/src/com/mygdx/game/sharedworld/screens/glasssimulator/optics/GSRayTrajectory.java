package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by dgli on 25/01/15.
 */
public class GSRayTrajectory {

    /**
     * Represents an uninterrupted ray segment.
     */
    private class RaySegment{
        Vector2 segmentStart, segmentEnd;
        float length;
        float startIntensity, endIntensity;

        public RaySegment(Vector2 segStart, Vector2 segEnd, float startInten, float endInten) {
            segmentStart = segStart;
            segmentEnd = segEnd;
            startIntensity = startInten;
            endIntensity = endInten;
            length = segEnd.dst(segStart);
        }
    }

    /**
     * List of uninterrupted segments
     */
    ArrayList<RaySegment> segmentList;
    Color baseColor;

    public GSRayTrajectory (Color baseColor){
        this.baseColor = baseColor;
        segmentList = new ArrayList<RaySegment>();
    }

    public void drawTrajectoryShape(ShapeRenderer sr){
        for(RaySegment r : segmentList){
            Color startColor = new Color(baseColor.r, baseColor.g, baseColor.b, r.startIntensity);
            Color endColor = new Color(baseColor.r, baseColor.g, baseColor.b, r.endIntensity);
            sr.line(r.segmentStart.x, r.segmentStart.y, r.segmentEnd.x, r.segmentEnd.y, startColor, endColor);
        }
    }

    public void addUninterruptedSegment(Vector2 start, Vector2 end, float startInten, float endInten){
        segmentList.add(new RaySegment(start, end, startInten, endInten));
    }
}
