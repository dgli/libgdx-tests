package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects.GSOInterfaceSegment;

import java.util.ArrayList;

/**
 * Created by dgli on 25/01/15.
 */
public interface GSICollidable {
    public ArrayList<GSOInterfaceSegment> getCollidableInterfaceSegments();
    public GSInterfaceCollisionResult getCollisionResultIfExists(RayFront source);
}
