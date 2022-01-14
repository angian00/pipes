package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameTileContainer extends BaseActor {
    public static final int TILE_SIZE = 100;

    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final int tileX;
    private final int tileY;
    private GameTile tile;

    public GameTileContainer(int i, int j, Stage s) {
        super(i * TILE_SIZE, j * TILE_SIZE, s);
        tileX = i;
        tileY = j;
        setSize(TILE_SIZE, TILE_SIZE);
        setColor(Color.GRAY);


        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Click inside: " + tileX + ", " + tileY);
                if (GameTileContainer.this.tile == null) {
                    GameTileContainer.this.tile = new GameTile(getStage());
                    GameTileContainer.this.addActor(tile);
                }

                return true;
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
/*        Color c = getColor();
        batch.setColor(c);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(c);
        shapeRenderer.rect(getX(), getY(), TILE_SIZE, TILE_SIZE);
        shapeRenderer.end();
*/
        super.draw(batch, parentAlpha);
    }
}
