package com.angian.libgdx.pipes;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class GameBoard extends BaseActor {
    public static final int N_TILES_X = 10;
    public static final int N_TILES_Y = 7;

    private static final Random rnd = new Random();

    private final LevelScreen level;
    private final GameTileContainer[][] tiles = new GameTileContainer[N_TILES_X][N_TILES_Y];
    private final GridPoint2 sourcePos;


    public GameBoard(LevelScreen ls, float x, float y, Stage s) {
        super(x, y, s);
        setSize(N_TILES_X * GameTileContainer.TILE_SIZE, N_TILES_Y * GameTileContainer.TILE_SIZE);

        level = ls;


        int iSource;
        int jSource;
        GameTile.TileType sourceType;
        do {
            iSource = rnd.nextInt(N_TILES_X);
            jSource = rnd.nextInt(N_TILES_Y);
            sourceType = GameTile.randomSourceType();
        } while (!isSourceValid(iSource, jSource, sourceType));

        sourcePos = new GridPoint2(iSource, jSource);

        for (int i=0; i < N_TILES_X; i++) {
            for (int j = 0; j < N_TILES_Y; j++) {
                GameTileContainer gtc = new GameTileContainer(level, i, j, s);
                if (i == iSource && j == jSource)
                    gtc.setTile(sourceType);

                addActor(gtc);
                tiles[i][j] = gtc;
            }
        }


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



    public GameTile.PathItem followPipe(GridPoint2 fromPos, GameTile.TileDirection fromDirection) {
        GameTile fromTile = getTile(fromPos);
        if (fromTile == null)
            return null;

        GridPoint2 toPos = new GridPoint2(fromPos.x, fromPos.y);
        switch (fromDirection) {
            case W:
                toPos.x --;
                break;
            case N:
                toPos.y ++;
                break;
            case E:
                toPos.x ++;
                break;
            case S:
                toPos.y --;
                break;
        }

        GameTile toTile = getTile(toPos);
        if (toTile == null)
            return null;

        GameTile.TileDirection nextDir = toTile.followPipe(fromDirection.flip());

        if (nextDir == null)
            return null;

        return new GameTile.PathItem(toPos, nextDir);
    }


    public GridPoint2 getSourcePos() {
        return sourcePos;
    }

    public GameTile getTile(GridPoint2 pos) {
        if (pos.x < 0 || pos.x >= N_TILES_X || pos.y < 0 || pos.y >= N_TILES_Y)
            return null;

        return tiles[pos.x][pos.y].getTile();
    }

    private boolean isSourceValid(int i, int j, GameTile.TileType type) {
        if (!type.isSource())
            return false;

        if (i == 0 && type == GameTile.TileType.SOURCE_LEFT)
            return false;

        if (i == (N_TILES_X-1) && type == GameTile.TileType.SOURCE_RIGHT)
            return false;

        if (j == 0 && type == GameTile.TileType.SOURCE_DOWN)
            return false;

        if (j == (N_TILES_Y-1) && type == GameTile.TileType.SOURCE_UP)
            return false;

        return true;
    }

}
