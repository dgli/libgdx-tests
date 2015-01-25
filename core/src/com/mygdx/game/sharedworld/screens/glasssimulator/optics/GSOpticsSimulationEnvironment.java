package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by dgli on 24/01/15.
 */
public class GSOpticsSimulationEnvironment {
    ArrayList<GSObject> objectList;

    public GSOpticsSimulationEnvironment(){
        objectList = new ArrayList();
    }

    public void drawObjectShapes(ShapeRenderer sr){

        for(GSObject gso : objectList){
            gso.drawShape(sr);
        }
    }

    public void addObject(GSObject o){
        objectList.add(o);
    }
}
