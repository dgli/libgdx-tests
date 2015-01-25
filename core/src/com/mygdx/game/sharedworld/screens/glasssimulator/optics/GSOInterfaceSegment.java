package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.config.Constants;

/**
 * Created by dgli on 23/01/15.
 *
 *
 * CONVENTION:
 *
 * Given a segment from left to right, medium 1 is bottom and medium 2 is top.
 *
 * e.g.
 *
 *            medium 2
 *     (p1)-------------->>(p2)
 *            medium 1
 *
 *
 */
public class GSOInterfaceSegment extends GSObject{
    private Vector2 startPoint, endPoint;
    private float medium1RefracticeIndex, medium2RefracticeIndex;

    public GSOInterfaceSegment(Vector2 start, Vector2 end, float medium1index, float medium2index){
        startPoint = start;
        endPoint = end;
        medium1RefracticeIndex = medium1index;
        medium2RefracticeIndex = medium2index;
    }


    @Override
    public void drawShape(ShapeRenderer sr) {
        sr.setColor(Color.WHITE);
        sr.rectLine(startPoint, endPoint, 3);
    }


    public Vector2 getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Vector2 startPoint) {
        this.startPoint = startPoint;
    }

    public Vector2 getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Vector2 endPoint) {
        this.endPoint = endPoint;
    }

    public float getMedium1RefracticeIndex() {
        return medium1RefracticeIndex;
    }

    public void setMedium1RefracticeIndex(float medium1RefracticeIndex) {
        this.medium1RefracticeIndex = medium1RefracticeIndex;
    }

    public float getMedium2RefracticeIndex() {
        return medium2RefracticeIndex;
    }

    public void setMedium2RefracticeIndex(float medium2RefracticeIndex) {
        this.medium2RefracticeIndex = medium2RefracticeIndex;
    }

}
