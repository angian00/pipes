package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameBoard extends BaseActor {
    public static final int N_TILES_X = 10;
    public static final int N_TILES_Y = 7;

    private LevelScreen level;

    public GameBoard(LevelScreen ls, float x, float y, Stage s) {
        super(x, y, s);

        level = ls;

        for (int i=0; i < N_TILES_X; i++) {
            for (int j = 0; j < N_TILES_Y; j++) {
                addActor(new GameTileContainer(level, i, j, s));
            }
        }

        setSize(N_TILES_X * GameTileContainer.TILE_SIZE, N_TILES_Y * GameTileContainer.TILE_SIZE);
    }


    public int countTiles() {
        int nTiles = 0;

        for (Actor a: getChildren()) {
            if (!(a instanceof GameTileContainer))
                continue;

            GameTileContainer gtc = (GameTileContainer) a;
            if (gtc.getTile() != null)
                nTiles ++;
        }

        return nTiles;
    }
}
