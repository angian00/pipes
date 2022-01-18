package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;


public class LevelScreen extends BaseScreen {
    private static final int N_NEXT_TILES = 5;

    //TODO: vary with level
    //private static final float waterStartTime = 20; //in seconds, time before water starts
    private static final float waterStartTime = 5;
    private static final float waterSpeed = 20; //px per second
    private static final int scoreFactor = 100;
    private static final int distance2win = 5;
    //

    private GameBoard board;
    private Label levelLabel;
    private Label scoreLabel;
    private Label distanceLabel;

    private List<GameTileContainer> tilePreviews;
    private WaterTimer waterTimer;
    private Water water;

    private int nLevel = 1;
    private int nAddedTiles = 0;
    private int score = 0;

    private List<GameTile.TileType> nextTiles;

    private Label textOverlay;


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
        mainStage.addActor(levelLabel);

        scoreLabel = new Label("score: 0", Styles.labelStyle);
        scoreLabel.setAlignment(LevelLayout.TEXT_SCORE_ALIGN);
        labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_SCORE);
        scoreLabel.setPosition(labelRect.x, labelRect.y);
        scoreLabel.setWidth(labelRect.width);
        scoreLabel.setHeight(labelRect.height);
        mainStage.addActor(scoreLabel);

        distanceLabel = new Label("distance: " + distance2win, Styles.labelStyle);
        distanceLabel.setAlignment(LevelLayout.TEXT_DISTANCE_ALIGN);
        labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_DISTANCE);
        distanceLabel.setPosition(labelRect.x, labelRect.y);
        distanceLabel.setWidth(labelRect.width);
        distanceLabel.setHeight(labelRect.height);
        mainStage.addActor(distanceLabel);


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
            nextTiles.add(GameTile.randomPlayableType());
        }
        updateTilePreview();


        Rectangle timerRect = LevelLayout.standard2gdxCoords(LevelLayout.TIMER);
        waterTimer = new WaterTimer(waterStartTime, timerRect, mainStage);
        waterTimer.setColor(LevelLayout.TIMER_COLOR);
        mainStage.addActor(waterTimer);


        textOverlay = new Label("", Styles.labelStyle);
        textOverlay.setFontScale(4.0f);
        textOverlay.setColor(Color.ORANGE);
        textOverlay.setVisible(false);
        uiTable.add(textOverlay).center();
    }

    @Override
    protected void update(float dt) {
        int nAddedTilesNew = board.countTiles();
        if (nAddedTilesNew > nAddedTiles) {
            nAddedTiles = nAddedTilesNew;

            //spawn new tile
            nextTiles.remove(0);
            nextTiles.add(GameTile.randomPlayableType());
            updateTilePreview();
        }

        if (waterTimer.isExpired() && water == null) {
            //instantiate water
            System.out.println("Water is starting!");
            water = new Water(waterSpeed, board, mainStage);
            mainStage.addActor(water);
        }

        if (water != null) {
            int nWaterTiles = water.getPath().size() - 1;
            score = nWaterTiles * scoreFactor;
            scoreLabel.setText("score: " + score);

            int distance2go = distance2win - nWaterTiles;
            if (distance2go < 0)
                distance2go = 0;
            distanceLabel.setText("distance: " + distance2go);

            if (water.isStopped()) {
                endLevel();
            }
        }
    }

    private void endLevel() {
        textOverlay.setText("Game over");
        textOverlay.setVisible(true);
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
