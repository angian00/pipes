package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameTile extends BaseActor {

    public static enum TileType {
        HORIZONTAL,
        VERTICAL,
        CROSS,
        BEND_LEFT_UP,
        BEND_UP_RIGHT,
        BEND_RIGHT_DOWN,
        BEND_DOWN_LEFT,
    }

    public GameTile(Stage s) {
        super(s);

        //TODO: differentiate by type
        loadTexture("tile_vert.png");
    }

}
