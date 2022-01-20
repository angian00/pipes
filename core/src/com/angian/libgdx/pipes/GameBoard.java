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
    private final Tile[][] tiles = new Tile[N_TILES_X][N_TILES_Y];
    private final GridPoint2 sourcePos;


    public GameBoard(LevelScreen ls, float x, float y, Stage s) {
        super(x, y, s);
        setSize(N_TILES_X * Tile.TILE_SIZE, N_TILES_Y * Tile.TILE_SIZE);

        level = ls;


        int iSource;
        int jSource;
        PipeType sourceType;
        do {
            iSource = rnd.nextInt(N_TILES_X);
            jSource = rnd.nextInt(N_TILES_Y);
            sourceType = PipeType.randomSourceType();
        } while (!isSourceValid(iSource, jSource, sourceType));

        sourcePos = new GridPoint2(iSource, jSource);

        for (int i=0; i < N_TILES_X; i++) {
            for (int j = 0; j < N_TILES_Y; j++) {
                Tile tile = new Tile(level, i, j, s);
                if (i == iSource && j == jSource)
                    tile.setPipe(sourceType);

                addActor(tile);
                tiles[i][j] = tile;
            }
        }


    }

    public PathItem followPipe(GridPoint2 fromPos, Direction fromDirection) {
        Pipe fromPipe = getPipe(fromPos);
        if (fromPipe == null)
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

        Pipe toPipe = getPipe(toPos);
        if (toPipe == null)
            return null;

        Direction nextDir = toPipe.followPipe(fromDirection.flip());

        if (nextDir == null)
            return null;

        return new PathItem(toPos, nextDir);
    }


    public GridPoint2 getSourcePos() {
        return sourcePos;
    }

    public Pipe getPipe(GridPoint2 pos) {
        if (pos.x < 0 || pos.x >= N_TILES_X || pos.y < 0 || pos.y >= N_TILES_Y)
            return null;

        return tiles[pos.x][pos.y].getPipe();
    }

    private boolean isSourceValid(int i, int j, PipeType type) {
        if (!type.isSource())
            return false;

        if (i < 2 && type == PipeType.SOURCE_LEFT)
            return false;

        if (i >= (N_TILES_X-2) && type == PipeType.SOURCE_RIGHT)
            return false;

        if (j < 2 && type == PipeType.SOURCE_DOWN)
            return false;

        if (j >= (N_TILES_Y-2) && type == PipeType.SOURCE_UP)
            return false;

        return true;
    }

}
