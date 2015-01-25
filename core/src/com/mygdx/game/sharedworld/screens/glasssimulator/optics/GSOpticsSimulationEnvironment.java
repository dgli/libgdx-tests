package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.config.Constants;

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

    public GSRayTrajectory projectRay(GSRaySource rSrc){

        // for now, just cast straight
        GSRayTrajectory traj = new GSRayTrajectory(rSrc.baseColor);

        Vector2 endPoint = new Vector2(
                (float) Math.cos(rSrc.direction) * 500,
                (float) Math.sin(rSrc.direction) * 500);

        //System.out.println("SOURCE: " + rSrc.position + "    COPY: " + rSrc.position.cpy());

        traj.addUninterruptedSegment(rSrc.position, rSrc.position.cpy().add(endPoint), 1, 0);

        return traj;
    }

    public void addObject(GSObject o){
        objectList.add(o);
    }
}
