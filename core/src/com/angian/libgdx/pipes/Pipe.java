package com.angian.libgdx.pipes;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.HashMap;
import java.util.Map;

public class Pipe extends BaseActor {
    private final PipeType type;
    private final Map<Direction, Boolean> validDirections;


    public Pipe(PipeType t, Stage s) {
        super(s);

        type = t;
        validDirections = new HashMap<>();
        for (Direction dir: Direction.values()) {
            validDirections.put(dir, false);
        }

        String tileFile;
        switch (type) {
            case HORIZONTAL:
                validDirections.put(Direction.W, true);
                validDirections.put(Direction.E, true);
                tileFile = "tile_hor.png";
                break;

            case VERTICAL:
                validDirections.put(Direction.N, true);
                validDirections.put(Direction.S, true);
                tileFile = "tile_vert.png";
                break;
            case CROSS:
                validDirections.put(Direction.N, true);
                validDirections.put(Direction.S, true);
                validDirections.put(Direction.W, true);
                validDirections.put(Direction.E, true);
                tileFile = "tile_cross.png";
                break;
            case BEND_LEFT_UP:
                validDirections.put(Direction.W, true);
                validDirections.put(Direction.N, true);
                tileFile = "tile_bend_left_up.png";
                break;
            case BEND_UP_RIGHT:
                validDirections.put(Direction.N, true);
                validDirections.put(Direction.E, true);
                tileFile = "tile_bend_up_right.png";
                break;
            case BEND_RIGHT_DOWN:
                validDirections.put(Direction.E, true);
                validDirections.put(Direction.S, true);
                tileFile = "tile_bend_right_down.png";
                break;
            case BEND_DOWN_LEFT:
                validDirections.put(Direction.S, true);
                validDirections.put(Direction.W, true);
                tileFile = "tile_bend_down_left.png";
                break;

            case SOURCE_LEFT:
                validDirections.put(Direction.W, true);
                tileFile = "tile_source_left.png";
                break;
            case SOURCE_UP:
                validDirections.put(Direction.N, true);
                tileFile = "tile_source_up.png";
                break;
            case SOURCE_RIGHT:
                validDirections.put(Direction.E, true);
                tileFile = "tile_source_right.png";
                break;
            case SOURCE_DOWN:
                validDirections.put(Direction.S, true);
                tileFile = "tile_source_down.png";
                break;

            default:
                tileFile = "tile_empty.png";
        }

        loadTexture(tileFile);
    }

    public boolean isValidDirection(Direction dir) {
        return validDirections.get(dir);
    }

    public Direction anyValidDirection() {
        for (Direction dir: validDirections.keySet()) {
            if (validDirections.get(dir))
                return dir;
        }

        return null;
    }

    public Direction followPipe(Direction inDir) {
        if (!isValidDirection(inDir))
            return null;


        if (type == PipeType.CROSS) {
            return inDir.flip();
        }

        //following works for straights, bends AND sources (with inDir == null)
        for (Direction dir: validDirections.keySet()) {
            if (dir != inDir && validDirections.get(dir))
                return dir;
        }

        return null;
    }
}
