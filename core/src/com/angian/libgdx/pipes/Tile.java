package com.angian.libgdx.pipes;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Tile extends BaseActor {
    public static final int TILE_SIZE = 100;

    private LevelScreen level;
    private final int tileX;
    private final int tileY;
    private Pipe pipe;


    public Tile(Stage s) {
        this(null, -999, -999, s);
    }

    public Tile(LevelScreen ls, int i, int j, Stage s) {
        super(i * TILE_SIZE, j * TILE_SIZE, s);
        loadTexture("tile_empty.png");

        level = ls;
        tileX = i;
        tileY = j;

        if (tileX >= 0) {
            addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("Click inside: " + tileX + ", " + tileY);
                    if (Tile.this.pipe == null) {
                        setPipe(level.getNextPipe());
                    }

                    return false;
                }
            });
        }
    }

    public Pipe getPipe() {
        return pipe;
    }

    public void setPipe(PipeType t) {
        if (pipe != null) {
            removeActor(pipe);
            pipe.remove();
        }

        if (t != null) {
            pipe = new Pipe(t, getStage());
            this.addActor(pipe);
        }
    }
}
