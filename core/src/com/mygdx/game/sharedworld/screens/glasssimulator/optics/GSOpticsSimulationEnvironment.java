package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.config.Constants;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

import java.util.ArrayList;

/**
 * Created by dgli on 24/01/15.
 */
public class GSOpticsSimulationEnvironment {
    ArrayList<GSObject> objectList;
    ArrayList<GSRayTrajectory> rayList;
    int environmentSimulationVersion;

    public GSOpticsSimulationEnvironment(){
        objectList = new ArrayList<GSObject>();
        rayList = new ArrayList<GSRayTrajectory>();

        environmentSimulationVersion = 0;
    }

    public void drawObjectShapes(ShapeRenderer sr){

        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        for(GSRayTrajectory rt : rayList){
            rt.drawTrajectoryShape(sr);
        }
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);

        for(GSObject gso : objectList){
            gso.drawShape(sr);
        }

    }

    public void updateEnvironmentRayTrajectories(){
        environmentSimulationVersion++;
        if(environmentSimulationVersion > Integer.MAX_VALUE - 1){
            environmentSimulationVersion = 0;
        }

        // remove the old rays and recast.
        rayList.clear();

        for(GSObject gso : objectList){
            if(gso instanceof GSIRayEmitter) {

                // not all objects are ray emmitters.  So if they are we convert them.
                GSIRayEmitter emitter = (GSIRayEmitter)gso;

                for (GSRaySource rSrc : emitter.getRaySources()){
                    rayList.add(projectRay(rSrc));
                }

            }

        }

    }

    public GSRayTrajectory projectRay(GSRaySource rSrc) {

        GSRayTrajectory traj = new GSRayTrajectory(rSrc.baseColor);

        RayFront initialRay = new RayFront(rSrc.position.cpy(), rSrc.direction.cpy(), 1, 0.002f, null);

        int i = 0;
        while(i++ < 100){
            GSInterfaceCollisionResult closestResult = null;
            float closestDistance = Float.MAX_VALUE;

            for (GSObject o : objectList) {
                if (o instanceof GSICollidable) {
                    GSICollidable collidable = (GSICollidable) o;
                    ArrayList<GSOInterfaceSegment> segments = ((GSICollidable) o).getCollidableInterfaceSegments();

                    for (GSOInterfaceSegment seg : segments) {
                        GSInterfaceCollisionResult collisionResult = seg.getCollisionResultIfExists(initialRay);

                        if (collisionResult != null) {
                            if (closestResult == null) {
                                closestResult = collisionResult;
                                closestDistance = collisionResult.incidenceRaySegment.length;
                            } else {
                                if (collisionResult.incidenceRaySegment.length < closestDistance) {
                                    closestResult = collisionResult;
                                    closestDistance = collisionResult.incidenceRaySegment.length;
                                }
                            }
                        }
                    }
                }
            }


            if (closestResult != null) {
                closestResult.incidenceRaySegment.getCollidedInterface().markCollision = true;
                traj.addUninterruptedSegment(closestResult.incidenceRaySegment);
                traj.addCollisionResult(closestResult);

                initialRay = closestResult.getReflectedRayFront();

            } else {

                if(initialRay != null) {
                    Vector2 rayEndPoint = initialRay.getRayStart().cpy()
                            .add(initialRay.direction.cpy().scl(initialRay.fadeOutDistance));
                    traj.addUninterruptedSegment(initialRay.rayStart, rayEndPoint,
                            initialRay.getStartIntensity(), initialRay.fadingCoefficient, null);
                }
                break;
            }
        }

        return traj;
    }

    public void addObject(GSObject o){
        objectList.add(o);
    }
}
