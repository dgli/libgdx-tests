package com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.config.Constants;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.*;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSRayTrajectory;

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
public class GSOInterfaceSegment extends GSObject implements GSICollidable{
    private Vector2 startPoint, endPoint;
    private float medium1RefracticeIndex, medium2RefracticeIndex;
    private boolean previousIntersectionSuccess;

    public GSOInterfaceSegment(Vector2 start, Vector2 end, float medium1index, float medium2index){
        startPoint = start;
        endPoint = end;
        medium1RefracticeIndex = medium1index;
        medium2RefracticeIndex = medium2index;
        previousIntersectionSuccess = false;
    }


    @Override
    public void drawShape(ShapeRenderer sr) {
        if(previousIntersectionSuccess) {
            sr.setColor(Color.LIGHT_GRAY);
        }else {
            sr.setColor(Color.BLUE);
        }

            sr.rectLine(startPoint, endPoint, Constants.InterfaceSegment.WALL_THICKNESS);
        sr.circle(startPoint.x, startPoint.y, Constants.InterfaceSegment.END_POINT_DOT_RADIUS);
        sr.circle(endPoint.x, endPoint.y, Constants.InterfaceSegment.END_POINT_DOT_RADIUS);
    }


    @Override
    public ArrayList<GSOInterfaceSegment> getCollidableInterfaceSegments() {
        ArrayList<GSOInterfaceSegment> segList = new ArrayList<GSOInterfaceSegment>();
        segList.add(this);
        return segList;
    }

    @Override
    public GSInterfaceCollisionResult getCollisionResultIfExists(RayFront source){

        previousIntersectionSuccess = false;

        Vector2 sourceDirection = source.getDirection().cpy();

        float intersectionCoefficient = Intersector.intersectRayRay(
                source.getRayStart(), sourceDirection,
                startPoint, endPoint.cpy().sub(startPoint));

        if(intersectionCoefficient < 0){
            return null;
        }

        Vector2 intersection =
                source.getRayStart().cpy().add(sourceDirection.scl(intersectionCoefficient));

        float maxX = Math.max(startPoint.x, endPoint.x);
        float maxY = Math.max(startPoint.y, endPoint.y);
        float minX = Math.min(startPoint.x, endPoint.x);
        float minY = Math.min(startPoint.y, endPoint.y);
        float ix = intersection.x;
        float iy = intersection.y;

        if(ix < minX || ix > maxX || iy < minY || iy > maxY){
            return null;
        }

        GSInterfaceCollisionResult result = new GSInterfaceCollisionResult();
        result.setIncidenceRaySegment(
                new RaySegment(source.getRayStart().cpy(), intersection,
                        source.getStartIntensity(), source.getFadingCoefficient())
        );

        if(source.getFadeOutDistance() > 0 &&
                result.getIncidenceRaySegment().getLength() > source.getFadeOutDistance()){
            return null;
        }


        previousIntersectionSuccess = true;
        return result;
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
