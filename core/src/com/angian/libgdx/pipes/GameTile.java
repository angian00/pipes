package com.angian.libgdx.pipes;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameTile extends BaseActor {

    public enum TileType {
        HORIZONTAL,
        VERTICAL,
        CROSS,
        BEND_LEFT_UP,
        BEND_UP_RIGHT,
        BEND_RIGHT_DOWN,
        BEND_DOWN_LEFT,

        SOURCE_LEFT,
        SOURCE_UP,
        SOURCE_RIGHT,
        SOURCE_DOWN;

        public boolean isPlayable() {
            return (
                this == HORIZONTAL ||
                this == VERTICAL ||
                this == CROSS ||
                this == BEND_LEFT_UP ||
                this == BEND_UP_RIGHT ||
                this == BEND_RIGHT_DOWN ||
                this == BEND_DOWN_LEFT
            );
        }

        public boolean isSource() {
            return (
                this == SOURCE_LEFT ||
                this == SOURCE_UP ||
                this == SOURCE_RIGHT ||
                this == SOURCE_DOWN
            );
        }
    }

    public enum TileDirection {
        W,
        N,
        E,
        S;

        public TileDirection flip() {
            switch (this) {
                case W:
                    return E;
                case N:
                    return S;
                case E:
                    return W;
                case S:
                    return N;
            }

            return null;
        }
    }

    public static class PathItem {
        public GridPoint2 pos;
        public GameTile.TileDirection outDir;

        public PathItem(GridPoint2 p, GameTile.TileDirection d) {
            pos = p;
            outDir = d;
        }

        public String toString() {
            return "(" + pos.x + ", " + pos.y + ") going " + outDir;
        }
    }


    private static final Random rnd = new Random();


    public static GameTile.TileType randomPlayableType() {
        TileType[] types = {
                TileType.HORIZONTAL,
                TileType.VERTICAL,
                TileType.CROSS,
                TileType.BEND_LEFT_UP,
                TileType.BEND_UP_RIGHT,
                TileType.BEND_RIGHT_DOWN,
                TileType.BEND_DOWN_LEFT,
        };

        int iChoice = rnd.nextInt(types.length);
        return types[iChoice];
    }

    public static GameTile.TileType randomSourceType() {
        TileType[] types = {
                TileType.SOURCE_LEFT,
                TileType.SOURCE_UP,
                TileType.SOURCE_RIGHT,
                TileType.SOURCE_DOWN,
        };

        int iChoice = rnd.nextInt(types.length);
        return types[iChoice];
    }


    private final TileType type;
    private final Map<TileDirection, Boolean> validDirections;

    public GameTile(TileType t, Stage s) {
        super(s);

        type = t;
        validDirections = new HashMap<>();
        for (TileDirection dir: TileDirection.values()) {
            validDirections.put(dir, false);
        }

        String tileFile;
        switch (type) {
            case HORIZONTAL:
                validDirections.put(TileDirection.W, true);
                validDirections.put(TileDirection.E, true);
                tileFile = "tile_hor.png";
                break;

            case VERTICAL:
                validDirections.put(TileDirection.N, true);
                validDirections.put(TileDirection.S, true);
                tileFile = "tile_vert.png";
                break;
            case CROSS:
                validDirections.put(TileDirection.N, true);
                validDirections.put(TileDirection.S, true);
                validDirections.put(TileDirection.W, true);
                validDirections.put(TileDirection.E, true);
                tileFile = "tile_cross.png";
                break;
            case BEND_LEFT_UP:
                validDirections.put(TileDirection.W, true);
                validDirections.put(TileDirection.N, true);
                tileFile = "tile_bend_left_up.png";
                break;
            case BEND_UP_RIGHT:
                validDirections.put(TileDirection.N, true);
                validDirections.put(TileDirection.E, true);
                tileFile = "tile_bend_up_right.png";
                break;
            case BEND_RIGHT_DOWN:
                validDirections.put(TileDirection.E, true);
                validDirections.put(TileDirection.S, true);
                tileFile = "tile_bend_right_down.png";
                break;
            case BEND_DOWN_LEFT:
                validDirections.put(TileDirection.S, true);
                validDirections.put(TileDirection.W, true);
                tileFile = "tile_bend_down_left.png";
                break;

            case SOURCE_LEFT:
                validDirections.put(TileDirection.W, true);
                tileFile = "tile_source_left.png";
                break;
            case SOURCE_UP:
                validDirections.put(TileDirection.N, true);
                tileFile = "tile_source_up.png";
                break;
            case SOURCE_RIGHT:
                validDirections.put(TileDirection.E, true);
                tileFile = "tile_source_right.png";
                break;
            case SOURCE_DOWN:
                validDirections.put(TileDirection.S, true);
                tileFile = "tile_source_down.png";
                break;

            default:
                tileFile = "tile_empty.png";
        }

        loadTexture(tileFile);
    }

    public boolean isValidDirection(TileDirection dir) {
        return validDirections.get(dir);
    }

    public TileDirection anyValidDirection() {
        for (TileDirection dir: validDirections.keySet()) {
            if (validDirections.get(dir))
                return dir;
        }

        return null;
    }

    public TileDirection followPipe(TileDirection inDir) {
        if (!isValidDirection(inDir))
            return null;


        if (type == TileType.CROSS) {
            return inDir.flip();
        }

        //following works for straights, bends AND sources (with inDir == null)
        for (TileDirection dir: validDirections.keySet()) {
            if (dir != inDir && validDirections.get(dir))
                return dir;
        }

        return null;
    }
}
