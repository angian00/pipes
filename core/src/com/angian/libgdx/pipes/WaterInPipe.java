package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.angian.libgdx.pipes.LevelLayout.PIPE_BEND_STRAIGHT_LEN;

public class WaterInPipe extends BaseActor {
    private static final int N_ARC_SEGMENTS = 10;

    //fraction of capacity in the curve part for bend pipes;
    //total capacity is bend + 2* straight = 1.0f ==> percStraight = (1.0f - bend)/2
    private static final float PIPE_PERC_BEND = 0.0648f;
    private static final float PIPE_PERC_STRAIGHT = (1.0f - PIPE_PERC_BEND)/2;

    private static ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final Pipe pipe;
    private final Direction fromDir;
    private final Direction toDir;
    private float waterLevel;

    private final float tileSize;
    private final float pipeSize;


    public WaterInPipe(Pipe p, Direction from, Direction to, Stage s) {
        super(s);
        fromDir = from;
        toDir = to;
        setColor(Color.CYAN);  //TODO: external setting for color

        pipe = p;
        waterLevel = 0;

        tileSize = pipe.getWidth();
        pipeSize = LevelLayout.PIPE_INSIDE_SIZE;

        setWidth(p.getWidth());
        setHeight(p.getHeight());
    }

    public void setWaterLevel(float wl) {
        waterLevel = wl;

        switch (pipe.getType()) {
            case HORIZONTAL:
                setHeight(pipeSize);
                setY((tileSize - pipeSize)/2);

                setWidth(tileSize * waterLevel);
                if (fromDir == Direction.W)
                    setX(0);
                else
                    setX(tileSize *  (1-waterLevel));

                break;

            case VERTICAL:
                setWidth(pipeSize);
                setX((tileSize - pipeSize)/2);

                setHeight(tileSize * waterLevel);
                if (fromDir == Direction.S)
                    setY(0);
                else
                    setY(tileSize *  (1-waterLevel));
                break;

            case CROSS:
                switch (fromDir) {
                    case W:
                        setHeight(pipeSize);
                        setY((tileSize - pipeSize)/2);

                        setWidth(tileSize * waterLevel);
                        setX(0);

                        break;
                    case E:
                        setHeight(pipeSize);
                        setY((tileSize - pipeSize)/2);

                        setWidth(tileSize * waterLevel);
                        setX(tileSize *  (1-waterLevel));

                        break;
                    case S:
                        setWidth(pipeSize);
                        setX((tileSize - pipeSize)/2);

                        setHeight(tileSize * waterLevel);
                        setY(0);

                        break;
                    case N:
                        setWidth(pipeSize);
                        setX((tileSize - pipeSize)/2);

                        setHeight(tileSize * waterLevel);
                        setY(tileSize *  (1-waterLevel));

                        break;
                }

                break;

            case SOURCE_DOWN:
                setWidth(pipeSize);
                setX((tileSize - pipeSize)/2);

                setHeight(tileSize * (waterLevel-0.5f));
                setY(tileSize *  (1.0f-waterLevel));

                break;

            case SOURCE_UP:
                setWidth(pipeSize);
                setX((tileSize - pipeSize)/2);

                setHeight(tileSize * (waterLevel-0.5f));
                setY(tileSize/2);

                break;

            case SOURCE_LEFT:
                setHeight(pipeSize);
                setY((tileSize - pipeSize)/2);

                setWidth(tileSize * (waterLevel-0.5f));
                setX(tileSize *  (1.0f-waterLevel));

                break;

            case SOURCE_RIGHT:
                setHeight(pipeSize);
                setY((tileSize - pipeSize)/2);

                setWidth(tileSize * (waterLevel-0.5f));
                setX(tileSize/2);

                break;

            default:
                //ignore;
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        //shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColor());
        if (pipe.getType().isBend()) {
            float frac1 = waterLevel / PIPE_PERC_STRAIGHT;
            if (frac1 > 1)
                frac1 = 1;
            if (frac1 > 0)
                drawBendSegment1(frac1, shapeRenderer);

            float frac2 = (waterLevel - PIPE_PERC_STRAIGHT) / PIPE_PERC_BEND;
            if (frac2 > 1)
                frac2 = 1;
            if (frac2 > 0)
                drawBendSegment2(frac2, shapeRenderer);

            float frac3 = (waterLevel - PIPE_PERC_STRAIGHT - PIPE_PERC_BEND) / PIPE_PERC_STRAIGHT;
            if (frac3 > 0)
                drawBendSegment3(frac3, shapeRenderer);

        } else {
            shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        }
        shapeRenderer.end();

        batch.begin();
    }

    private void drawBendSegment1(float perc, ShapeRenderer sr) {
        float x = 0;
        float y = 0;
        float width = 0;
        float height = 0;

        switch (fromDir) {
            case W:
                height = pipeSize;
                y = (tileSize - pipeSize) / 2;

                width = PIPE_BEND_STRAIGHT_LEN * perc;
                x = 0;

                break;

            case E:
                height = pipeSize;
                y = (tileSize - pipeSize) / 2;

                width = PIPE_BEND_STRAIGHT_LEN * perc;
                x = tileSize - width;

                break;

            case S:
                width = pipeSize;
                x = (tileSize - pipeSize) / 2;

                height = PIPE_BEND_STRAIGHT_LEN * perc;
                y = 0;

                break;

            case N:
                width = pipeSize;
                x = (tileSize - pipeSize) / 2;

                height = PIPE_BEND_STRAIGHT_LEN * perc;
                y = tileSize - height;

                break;
        }

        shapeRenderer.rect(x, y, width, height);
    }


    private void drawBendSegment2(float perc, ShapeRenderer sr) {
        boolean isRightBend = false;
        float startAngle = 0;
        float angle = 0;

        switch (fromDir) {
            case W:
                if (toDir == Direction.N) {
                    isRightBend = false;
                    startAngle = -90;

                } else {
                    isRightBend = true;
                    startAngle = 90;
                }

                break;

            case E:
                if (toDir == Direction.S) {
                    isRightBend = false;
                    startAngle = 90;

                } else {
                    isRightBend = true;
                    startAngle = -90;
                }

                break;

            case S:
                if (toDir == Direction.W) {
                    isRightBend = false;
                    startAngle = 0;

                } else {
                    isRightBend = true;
                    startAngle = 180;
                }

                break;

            case N:
                if (toDir == Direction.E) {
                    isRightBend = false;
                    startAngle = 180;  //FIXME

                } else {
                    isRightBend = true;
                    startAngle = 0;
                }

                break;
        }

        angle = 90 * perc;
        if (isRightBend)
            angle = -angle;

        sr.arc(tileSize/2, tileSize/2, LevelLayout.PIPE_BEND_RADIUS, startAngle, angle, N_ARC_SEGMENTS);

    }

    private void drawBendSegment3(float perc, ShapeRenderer sr) {
        float x = 0;
        float y = 0;
        float width = 0;
        float height = 0;

        switch (toDir) {
            case W:
                height = pipeSize;
                y = (tileSize - pipeSize) / 2;

                width = PIPE_BEND_STRAIGHT_LEN * perc;
                x = PIPE_BEND_STRAIGHT_LEN * (1.0f - perc);

                break;

            case E:
                height = pipeSize;
                y = (tileSize - pipeSize) / 2;

                width = PIPE_BEND_STRAIGHT_LEN * perc;
                x = tileSize - PIPE_BEND_STRAIGHT_LEN;

                break;

            case S:
                width = pipeSize;
                x = (tileSize - pipeSize) / 2;

                height = PIPE_BEND_STRAIGHT_LEN * perc;
                y = PIPE_BEND_STRAIGHT_LEN * (1.0f - perc);

                break;

            case N:
                width = pipeSize;
                x = (tileSize - pipeSize) / 2;

                height = PIPE_BEND_STRAIGHT_LEN * perc;
                y = tileSize - PIPE_BEND_STRAIGHT_LEN;

                break;
        }

        shapeRenderer.rect(x, y, width, height);
    }
}
