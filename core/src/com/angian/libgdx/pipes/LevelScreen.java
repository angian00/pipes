package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;


public class LevelScreen extends BaseScreen {
    private static final int N_NEXT_TILES = 5;
    private static final float WATER_START_TIME = 20; //in seconds, time before water starts  //TODO: vary with level

    private GameBoard board;
    private Label levelLabel;
    private Label scoreLabel;
    private Label distanceLabel;

    private List<GameTileContainer> tilePreviews;
    private WaterTimer waterTimer;
    private BaseActor water;

    private int nLevel = 1;
    private int nAddedTiles = 0;
    private int nWaterTiles = 0;

    private List<GameTile.TileType> nextTiles;


    @Override
    protected void initialize() {
        BaseActor gameBackground = new BaseActor(0, 0, mainStage);
        gameBackground.loadTexture("screen_background.png");
        BaseActor.setWorldBounds(gameBackground);

        Rectangle boardRect = LevelLayout.standard2gdxCoords(LevelLayout.BOARD);
        board = new GameBoard(this, boardRect.x, boardRect.y, mainStage);

        levelLabel = new Label("level: 1", Styles.labelStyle);
        levelLabel.setAlignment(LevelLayout.TEXT_LEVEL_ALIGN);
        Rectangle labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_LEVEL);
        levelLabel.setPosition(labelRect.x, labelRect.y);
        levelLabel.setWidth(labelRect.width);
        levelLabel.setHeight(labelRect.height);
        uiStage.addActor(levelLabel);

        scoreLabel = new Label("score: 1200", Styles.labelStyle);
        scoreLabel.setAlignment(LevelLayout.TEXT_SCORE_ALIGN);
        labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_SCORE);
        scoreLabel.setPosition(labelRect.x, labelRect.y);
        scoreLabel.setWidth(labelRect.width);
        scoreLabel.setHeight(labelRect.height);
        uiStage.addActor(scoreLabel);

        distanceLabel = new Label("distance: 3", Styles.labelStyle);
        distanceLabel.setAlignment(LevelLayout.TEXT_DISTANCE_ALIGN);
        labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_DISTANCE);
        distanceLabel.setPosition(labelRect.x, labelRect.y);
        distanceLabel.setWidth(labelRect.width);
        distanceLabel.setHeight(labelRect.height);
        uiStage.addActor(distanceLabel);


        Rectangle previewRect = LevelLayout.standard2gdxCoords(LevelLayout.TILE_PREVIEW);
        tilePreviews = new ArrayList<>();
        for (int i=0; i < N_NEXT_TILES; i++) {
            GameTileContainer tilePreview = new GameTileContainer(mainStage);
            tilePreview.setPosition(previewRect.x + 5, previewRect.y + 10 + (LevelLayout.TILE_SIZE + LevelLayout.TILE_PREVIEW_PADDING) * i);
            tilePreviews.add(tilePreview);
            mainStage.addActor(tilePreview);
        }

        nextTiles = new ArrayList<>();
        for (int i=0; i < N_NEXT_TILES; i++) {
            nextTiles.add(GameTile.randomType());
        }
        updateTilePreview();


        Rectangle timerRect = LevelLayout.standard2gdxCoords(LevelLayout.TIMER);
        waterTimer = new WaterTimer(WATER_START_TIME, timerRect, mainStage);
        waterTimer.setColor(LevelLayout.TIMER_COLOR);
        mainStage.addActor(waterTimer);


    }

    @Override
    protected void update(float dt) {
        int nAddedTilesNew = board.countTiles();
        if (nAddedTilesNew > nAddedTiles) {
            nAddedTiles = nAddedTilesNew;

            //spawn new tile
            nextTiles.remove(0);
            nextTiles.add(GameTile.randomType());
            updateTilePreview();
        }

        if (waterTimer.isExpired() && water == null) {
            //instantiate water
            System.out.println("Water is starting!");
            water = new BaseActor(mainStage);
        }
    }

    private void updateTilePreview() {
        for (int i=0; i < N_NEXT_TILES; i++) {
            GameTileContainer tilePreview = tilePreviews.get(i);
            tilePreview.setTile(nextTiles.get(i));
        }
    }

    public GameTile.TileType getNextTile() {
        return nextTiles.get(0);
    }

}
