package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameTileContainer extends BaseActor {
    public static final int TILE_SIZE = 100;

    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private LevelScreen level;
    private final int tileX;
    private final int tileY;
    private GameTile tile;

    public GameTileContainer(Stage s) {
        this(null, -999, -999, s);
    }

    public GameTileContainer(LevelScreen ls, int i, int j, Stage s) {
        super(i * TILE_SIZE, j * TILE_SIZE, s);
        loadTexture("tile_empty.png");

        level = ls;
        tileX = i;
        tileY = j;

        if (tileX >= 0) {
            addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("Click inside: " + tileX + ", " + tileY);
                    if (GameTileContainer.this.tile == null) {
                        setTile(level.getNextTile());
                    }

                    return false;
                }
            });
        }
    }

    public GameTile getTile() {
        return tile;
    }

    public void setTile(GameTile.TileType t) {
        if (tile != null) {
            removeActor(tile);
            tile.remove();
        }

        if (t != null) {
            tile = new GameTile(t, getStage());
            addActor(tile);
        }
    }
}
