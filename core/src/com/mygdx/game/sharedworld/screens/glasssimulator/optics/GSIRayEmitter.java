package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import java.util.ArrayList;

/**
 * Created by dgli on 24/01/15.
 *
 * Anything that implements this interface means that it emits light, and is able to
 * provide a list of ray sources for ray optics calculations.
 */
public interface GSIRayEmitter {
    public ArrayList<GSRaySource> getRaySources();
}
