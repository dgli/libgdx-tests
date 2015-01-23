package com.mygdx.game.sharedworld.screens.glasssimulator.optics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by dgli on 23/01/15.
 */
public class GSORay {
    private float sourceX, sourceY;
    private float directionInRadians;
    private float cutOffLength;
    public static final float RAY_UNIT_WIDTH = 0.5f;
    private boolean parametersChanged;

    // for rendering efficiency
    private float endPointX, endPointY;

    public GSORay(float srcX, float srcY, float direction){
        sourceX = srcX;
        sourceY = srcY;
        directionInRadians = direction;

        cutOffLength = 1000000;
        parametersChanged = true;
    }

    public void drawRayShape(ShapeRenderer sr){
        if(parametersChanged){
            recalc();
        }
        sr.rectLine(sourceX, sourceY, endPointX, endPointY, RAY_UNIT_WIDTH);
    }

    public void recalc(){
        parametersChanged = false;
        endPointX = (float)Math.cos(directionInRadians) * cutOffLength;
        endPointY = (float)Math.sin(directionInRadians) * cutOffLength;
    }


    public float getDirectionInRadians() {
        return directionInRadians;
    }

    public void setDirectionInRadians(float directionInRadians) {
        parametersChanged = true;
        this.directionInRadians = directionInRadians;
    }

    public float getSourceY() {
        return sourceY;
    }

    public void setSourceY(float sourceY) {
        parametersChanged = true;
        this.sourceY = sourceY;
    }

    public float getSourceX() {
        return sourceX;
    }

    public void setSourceX(float sourceX) {
        parametersChanged = true;
        this.sourceX = sourceX;
    }

    public float getCutOffLength() {
        return cutOffLength;
    }

    public void setCutOffLength(float cutOffLength) {
        parametersChanged = true;
        this.cutOffLength = cutOffLength;
    }
}
