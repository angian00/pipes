package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameBoard extends BaseActor {
    public static final int N_TILES_X = 10;
    public static final int N_TILES_Y = 7;

    public GameBoard(float x, float y, Stage s) {
        super(x, y, s);

        for (int i=0; i < N_TILES_X; i++) {
            for (int j = 0; j < N_TILES_Y; j++) {
                addActor(new GameTileContainer(i, j, s));
            }
        }

        setSize(N_TILES_X * GameTileContainer.TILE_SIZE, N_TILES_Y * GameTileContainer.TILE_SIZE);
    }
}
