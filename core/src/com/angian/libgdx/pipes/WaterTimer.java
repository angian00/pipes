package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WaterTimer extends BaseActor {
    private float origTime;
    private float remainingTime;
    private boolean expired = false;
    private float origHeight;

    private static ShapeRenderer shapeRenderer = new ShapeRenderer();


    public WaterTimer(float time2water, Rectangle layout, Stage s) {
        super(layout.x, layout.y, s);
        setWidth(layout.width);
        setHeight(layout.height);

        remainingTime = time2water;
        origTime = time2water;
        origHeight = layout.height;
    }


    public boolean isExpired() {
        return expired;
    }

    @Override
    public void act(float dt) {
        remainingTime -= dt;
        if (remainingTime <= 0) {
            remainingTime = 0;
            expired = true;
        }

        setHeight(origHeight * remainingTime / origTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColor());
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        shapeRenderer.end();
        batch.begin();
    }
}
