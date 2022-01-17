package com.angian.libgdx.pipes;

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
    }

    private enum TileDirection {
        N,
        S,
        W,
        E
    }

    private static final Random rnd = new Random();


    public static GameTile.TileType randomType() {
        TileType[] types = TileType.values();
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
            default:
                tileFile = "tile_empty.png";
        }

        loadTexture(tileFile);
    }

}
