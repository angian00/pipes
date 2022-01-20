package com.angian.libgdx.pipes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Tile extends BaseActor {
    public static final int TILE_SIZE = 100;

    private final LevelScreen level;
    private final int tileX;
    private final int tileY;
    private Pipe pipe;
    private final BaseActor border;


    public Tile(Stage s) {
        this(null, -999, -999, s);
    }

    public Tile(LevelScreen ls, int i, int j, Stage s) {
        super(i * TILE_SIZE, j * TILE_SIZE, s);
        loadTexture("tile_empty.png");

        border = new BaseActor(s);
        border.loadTexture("tile_border.png");
        border.setVisible(false);
        this.addActor(border);

        level = ls;
        tileX = i;
        tileY = j;

        if (isBoardTile()) {
            addListener(new ClickListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //System.out.println("Click inside: " + tileX + ", " + tileY);
                    if (isClickable()) {
                        setPipe(level.getNextPipe());
                    }

                    return false;
                }

                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    //System.out.println("Mouse enter: " + tileX + ", " + tileY);
                    if (isClickable()) {
                        border.setVisible(true);
                    }
                }

                public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    //System.out.println("Mouse exit: " + tileX + ", " + tileY);
                    border.setVisible(false);
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
            if (isBoardTile() && !t.isSource())
                level.spawnPipe();

            pipe = new Pipe(t, getStage());
            this.addActor(pipe);
            pipe.setZIndex(0);  //border must be in front

            if (isBoardTile())
                pipe.flashIn();
            else
                pipe.setOpacity(1.0f);
        }
    }

    public boolean isClickable() {
        return (pipe == null || !pipe.hasWater() && !pipe.getType().isSource());
    }

    private boolean isBoardTile() {
        return (tileX >= 0 && tileY >= 0);
    }
}
