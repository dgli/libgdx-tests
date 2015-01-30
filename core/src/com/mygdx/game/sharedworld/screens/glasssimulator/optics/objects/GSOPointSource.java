package com.mygdx.game.sharedworld.screens.glasssimulator.optics.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sharedworld.screens.glasssimulator.config.Constants;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSIRayEmitter;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSObject;
import com.mygdx.game.sharedworld.screens.glasssimulator.optics.GSRaySource;

import java.util.ArrayList;

/**
 * Created by dgli on 29/01/15.
 */
public class GSOPointSource  extends GSObject implements GSIRayEmitter {

    private Vector2 position;
    private boolean parametersChanged;
    private float emitterResolution = 0.002f;
    private ArrayList<GSRaySource> emitters;


    public GSOPointSource(Vector2 position){
        this.position = position;

        emitters = new ArrayList<GSRaySource>();
        recalc();
    }

    @Override
    public ArrayList<GSRaySource> getRaySources() {
        return emitters;
    }

    @Override
    public void drawShape(ShapeRenderer sr) {

        if(parametersChanged){
            recalc();
        }

        sr.setColor(1f, 0, 0, 1f);
        sr.circle(position.x, position.y, Constants.LaserPointer.BASE_DOT_RADIUS);

        //System.out.println("LASER DIRECTION: " + direction);

    }

    public void recalc(){
        parametersChanged = false;

        emitters.clear();

        for(float n = 0; n < (float)(2*Math.PI); n+= emitterResolution){
            emitters.add(new GSRaySource(position,
                    new Vector2(
                            (float)Math.cos(n),
                            (float)Math.sin(n)),
                    this, new Color(1f, 0.5f, 0.5f, 0.01f)));
        }

    }


    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        parametersChanged = true;
        this.position = position;
    }

    public float getEmitterResolution() {
        return emitterResolution;
    }

    public void setEmitterResolution(float emitterResolution) {
        this.emitterResolution = emitterResolution;
    }
}
