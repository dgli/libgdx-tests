package com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.config.Constants;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSIRayEmitter;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSObject;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSRaySource;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSRayTrajectory;

import java.util.ArrayList;

/**
 * Created by dgli on 23/01/15.
 */
public class GSOLaserPointer extends GSObject implements GSIRayEmitter {

    private Vector2 position;
    private Vector2 direction;
    private boolean parametersChanged;

    /**
     * The following variables are for rendering cache
     */
    private Vector2 unitDirectionEndPointCache;

    private GSRaySource laserEmitter;


    public GSOLaserPointer(Vector2 position, Vector2 direction){
        this.position = position;
        this.direction = direction;
        parametersChanged = true;

        laserEmitter = new GSRaySource(position, direction, this, Color.RED);
    }

    @Override
    public ArrayList<GSRaySource> getRaySources() {
        ArrayList<GSRaySource> srcList = new ArrayList<GSRaySource>();
        srcList.add(laserEmitter);
        return srcList;
    }

    public void drawShape(ShapeRenderer sr){
        if(parametersChanged){
            recalc();
        }

        sr.setColor(1f, 0, 0, 1f);
        sr.rectLine(position, unitDirectionEndPointCache, Constants.LaserPointer.BARREL_DIAMETER);
        sr.circle(position.x, position.y, Constants.LaserPointer.BASE_DOT_RADIUS);

        //System.out.println("LASER DIRECTION: " + direction);

    }

    public void recalc(){
        parametersChanged = false;

        laserEmitter = new GSRaySource(position, direction, this, Color.RED);
//        unitDirectionEndPointCache = position.cpy().add(
//                new Vector2((float) Math.cos(direction) * Constants.LaserPointer.BARREL_LENGTH,
//                        (float) Math.sin(direction) * Constants.LaserPointer.BARREL_LENGTH));

        unitDirectionEndPointCache = position.cpy().add(direction.cpy().scl(Constants.LaserPointer.BARREL_LENGTH));
    }


    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        parametersChanged = true;
        this.direction = direction;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        parametersChanged = true;
        this.position = position;
    }
}
