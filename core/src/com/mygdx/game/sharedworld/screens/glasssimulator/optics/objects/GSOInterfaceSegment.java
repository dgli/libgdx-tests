package com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.config.Constants;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.*;

import java.util.ArrayList;

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
 *
 *
 *
 *     (p1)-------|------>>(p2)
 *                V
 *
 *            medium 1
 *
 *
 */
public class GSOInterfaceSegment extends GSObject implements GSICollidable{
    private Vector2 startPoint, endPoint;
    private Vector2 normal;
    private float medium1RefracticeIndex, medium2RefracticeIndex;
    private float interfaceAbsorbance;
    private boolean previousIntersectionSuccess;
    public boolean markCollision;

    public GSOInterfaceSegment(Vector2 start, Vector2 end, float medium1index, float medium2index){
        startPoint = start;
        endPoint = end;
        medium1RefracticeIndex = medium1index;
        medium2RefracticeIndex = medium2index;
        previousIntersectionSuccess = false;

        normal = new Vector2();
        updateNormal();
    }


    @Override
    public void drawShape(ShapeRenderer sr) {
        if(markCollision){
            sr.setColor(Color.LIGHT_GRAY);
        }
        else if(previousIntersectionSuccess) {
            sr.setColor(Color.DARK_GRAY);
        }
        else {
            sr.setColor(Color.BLUE);
        }

        sr.rectLine(startPoint, endPoint, Constants.InterfaceSegment.WALL_THICKNESS);
        sr.circle(endPoint.x, endPoint.y, Constants.InterfaceSegment.END_POINT_DOT_RADIUS);
        // mark start point as yellow
        sr.setColor(Color.YELLOW);
        sr.circle(startPoint.x, startPoint.y, Constants.InterfaceSegment.END_POINT_DOT_RADIUS);


        Vector2 mid = startPoint.cpy().add(endPoint).scl(0.5f);

        float direction = endPoint.cpy().sub(startPoint).angleRad() - (float)Math.PI/2f;

        sr.circle(mid.x, mid.y, 2);
        sr.line(mid.x, mid.y, mid.x + 10 * normal.x, mid.y + 10 * normal.y);
    }


    @Override
    public ArrayList<GSOInterfaceSegment> getCollidableInterfaceSegments() {
        ArrayList<GSOInterfaceSegment> segList = new ArrayList<GSOInterfaceSegment>();
        segList.add(this);
        return segList;
    }

    @Override
    public GSInterfaceCollisionResult getCollisionResultIfExists(RayFront source){

        if(source == null || source.getFromSegment() == this){
            return null;
        }

        previousIntersectionSuccess = false;
        markCollision = false;

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

        GSInterfaceCollisionResult result = new GSInterfaceCollisionResult(this);

        RaySegment incidenceSegment =
                new RaySegment(source.getRayStart().cpy(), intersection,
                        source.getIntensity(),
                        this);


        /////
        //
        //
        //
        // calculate reflection
        //
        //
        //
        /////



        float crossRadius = Intersector.distanceLinePoint(intersection.x, intersection.y,
                intersection.x + normal.x, intersection.y + normal.y,
                source.getRayStart().x, source.getRayStart().y
                );

        Vector2 crossVector;


        crossVector = endPoint.cpy().sub(startPoint).nor();

        //
        // if the cross radius vector is on the same side of the normal as the incident ray
        // then we know we are on the wrong side.
        //
        int incSide = side(intersection, intersection.cpy().add(normal.cpy().scl(10000)),
                source.getRayStart());

        int reflSide = side(intersection, intersection.cpy().add(normal.cpy().scl(10000)),
                source.getRayStart().cpy().add(crossVector));

        //System.out.println("INCSIDE:" + incSide + "   REFSIDE:" + reflSide);

        if(incSide > 0 && reflSide > 0){
            crossVector.scl(-1);
        }

        crossVector.scl(crossRadius * 2);

        RayFront reflectionRayFront =
                new RayFront(intersection.cpy(),
                        source.getRayStart().cpy().add(crossVector).sub(intersection).nor(),
                        incidenceSegment.getIntensity(),
                        this);

//        RaySegment reflectionSeg =
//                new RaySegment(intersection.cpy(), source.getRayStart().cpy().add(crossVector),
//                        incidenceSegment.getEndIntensity(), source.getFadingCoefficient(),
//                        this);
//
//        RaySegment normalSeg =
//                new RaySegment(intersection, intersection.cpy().add(normal.cpy().scl(500)),
//                        incidenceSegment.getEndIntensity(), source.getFadingCoefficient(),
//                        this);

        result.setReflectedRayFront(reflectionRayFront);
        result.setIncidenceRaySegment(incidenceSegment);
        result.setCollisionPoint(intersection.cpy());





        previousIntersectionSuccess = true;
        return result;
    }

    int side(Vector2 p1, Vector2 p2, Vector2 p)
    {
        Vector2 diff = p2.cpy().sub(p1);
        Vector2 perp = new Vector2(-diff.y, diff.x);
        float d = p.cpy().sub(p1).dot(perp);

        return (d > 0 ? 1 : -1);
    }


    private void updateNormal(){
        float direction = endPoint.cpy().sub(startPoint).angleRad() - (float)Math.PI/2f;
        normal.x = (float)Math.cos(direction);
        normal.y = (float)Math.sin(direction);
    }


    public float getInterfaceAbsorbance() {
        return interfaceAbsorbance;
    }

    public void setInterfaceAbsorbance(float interfaceAbsorbance) {
        this.interfaceAbsorbance = interfaceAbsorbance;
    }

    public Vector2 getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Vector2 startPoint) {
        updateNormal();
        this.startPoint = startPoint;
    }

    public Vector2 getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Vector2 endPoint) {
        updateNormal();
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

    public Vector2 getNormal() {
        return normal;
    }
}
