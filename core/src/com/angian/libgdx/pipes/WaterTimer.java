package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class WaterTimer extends BaseActor {
    public enum Status {
        INIT,
        FREEZED,
        STARTED,
        EXPIRED,
    }

    private float origTime;
    private float remainingTime;
    private Status status = Status.INIT;
    private float origHeight;

    private static ShapeRenderer shapeRenderer = new ShapeRenderer();


    public WaterTimer(Rectangle layout, Stage s) {
        super(layout.x, layout.y, s);
        setWidth(layout.width);
        setHeight(layout.height);

        origHeight = layout.height;

        origTime = Float.MAX_VALUE;
        remainingTime = Float.MAX_VALUE;
    }

    public void start(float time2water) {
        origTime = time2water;
        remainingTime = time2water;
        status = Status.STARTED;
    }

    public boolean isInit() {
        return status == Status.INIT;
    }

    public boolean isStarted() {
        return status == Status.STARTED;
    }

    public boolean isExpired() {
        return status == Status.EXPIRED;
    }

    public void freeze() {
        status = Status.FREEZED;
    }

    @Override
    public void act(float dt) {
        remainingTime -= dt;
        if (isStarted() && remainingTime <= 0) {
            remainingTime = 0;
            status = Status.EXPIRED;
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
