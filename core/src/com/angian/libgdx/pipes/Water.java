package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class Water extends BaseActor {
    private static final float TILE_CAPACITY = 100.0f;

    private final float speed;
    private final GameBoard board;

    private final List<PathItem> path;
    private float tileOccupation;
    private boolean stopped;


    public Water(float sp, GameBoard b, Stage s) {
        super(s);
        speed = sp;
        board = b;

        tileOccupation = TILE_CAPACITY / 2;  // water starts from the center of source tile
        stopped = false;

        path = new ArrayList<>();

        GridPoint2 sourcePos = board.getSourcePos();
        path.add(new PathItem(sourcePos, board.getTile(sourcePos).anyValidDirection()));
    }

    public boolean isStopped() {
        return stopped;
    }

    public List<PathItem> getPath() {
        return path;
    }

    @Override
    public void act(float dt) {
        if (stopped)
            return;

        tileOccupation += (speed * dt);
        if (tileOccupation >= TILE_CAPACITY) {
            //check next tile
            PathItem lastStep = path.get(path.size() - 1);
            PathItem nextStep = board.followPipe(lastStep.pos, lastStep.outDir);
            System.out.println("Water transitioning to a new tile");

            if (nextStep != null) {
                System.out.println("Added new step to water path: " + nextStep);
                path.add(nextStep);
                tileOccupation -= TILE_CAPACITY;
            } else {
                System.out.println("!! Game over; total distance: " + path.size());
                stopped = true;
            }
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        //TODO
    }
}
