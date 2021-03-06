package com.angian.libgdx.pipes;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;


public class LevelScreen extends BaseScreen {
    private static final int N_NEXT_TILES = 5;

    private final int nLevel;
    private final LevelParams params;
    private final int startScore;

    private GameBoard board;
    private Label levelLabel;
    private Label scoreLabel;
    private Label distanceLabel;

    private List<Tile> pipePreviews;
    private WaterTimer waterTimer;
    private Water water;

    private int nWaterTiles = 0;
    private int score;
    private boolean clicksDisabled = true;
    private Runnable clickCallback;


    private List<PipeType> nextPipes;

    private TextOverlay textOverlay;


    public LevelScreen() {
        this(1, 0);
    }

    public LevelScreen(int _level, int _startScore) {
        nLevel = _level;
        startScore = _startScore;

        params = LevelParams.get(nLevel);
    }


    @Override
    protected void initialize() {
        BaseActor gameBackground = new BaseActor(0, 0, mainStage);
        gameBackground.loadTexture("screen_background.png");
        BaseActor.setWorldBounds(gameBackground);

        Rectangle boardRect = LevelLayout.standard2gdxCoords(LevelLayout.BOARD);
        board = new GameBoard(this, boardRect.x, boardRect.y, mainStage);

        levelLabel = new Label("level: x", Styles.labelStyle);
        levelLabel.setAlignment(LevelLayout.TEXT_LEVEL_ALIGN);
        Rectangle labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_LEVEL);
        levelLabel.setPosition(labelRect.x, labelRect.y);
        levelLabel.setWidth(labelRect.width);
        levelLabel.setHeight(labelRect.height);
        mainStage.addActor(levelLabel);

        scoreLabel = new Label("score: x", Styles.labelStyle);
        scoreLabel.setAlignment(LevelLayout.TEXT_SCORE_ALIGN);
        labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_SCORE);
        scoreLabel.setPosition(labelRect.x, labelRect.y);
        scoreLabel.setWidth(labelRect.width);
        scoreLabel.setHeight(labelRect.height);
        mainStage.addActor(scoreLabel);

        distanceLabel = new Label("distance: x", Styles.labelStyle);
        distanceLabel.setAlignment(LevelLayout.TEXT_DISTANCE_ALIGN);
        labelRect = LevelLayout.standard2gdxCoords(LevelLayout.TEXT_DISTANCE);
        distanceLabel.setPosition(labelRect.x, labelRect.y);
        distanceLabel.setWidth(labelRect.width);
        distanceLabel.setHeight(labelRect.height);
        mainStage.addActor(distanceLabel);


        Rectangle previewRect = LevelLayout.standard2gdxCoords(LevelLayout.TILE_PREVIEW);
        pipePreviews = new ArrayList<>();
        for (int i=0; i < N_NEXT_TILES; i++) {
            Tile tilePreview = new Tile(mainStage);
            tilePreview.setPosition(previewRect.x + 5, previewRect.y + previewRect.height - 10 - (LevelLayout.TILE_SIZE + LevelLayout.TILE_PREVIEW_PADDING) * i);
            pipePreviews.add(tilePreview);
        }

        nextPipes = new ArrayList<>();
        for (int i=0; i < N_NEXT_TILES; i++) {
            nextPipes.add(PipeType.randomPlayableType());
        }
        updatePipePreview();


        Rectangle timerRect = LevelLayout.standard2gdxCoords(LevelLayout.TIMER);
        waterTimer = new WaterTimer(timerRect, mainStage);
        waterTimer.setColor(LevelLayout.WATER_COLOR);


        textOverlay = new TextOverlay(this, uiStage);
        //textOverlay.countdown();
    }

    @Override
    protected void update(float dt) {
        if (waterTimer != null && waterTimer.isInit()) {
            //first frame
            levelLabel.setText("level: " + nLevel);
            scoreLabel.setText("score: " + startScore);
            distanceLabel.setText("distance: " + params.distance2win);

            waterTimer.freeze();
            textOverlay.countdown(() -> waterTimer.start(params.waterStartTime));
        }

        if (waterTimer != null && waterTimer.isExpired() && water == null) {
            waterTimer.remove();
            waterTimer = null;

            //instantiate water
            System.out.println("Water is starting!");
            water = new Water(params.waterStartSpeed, board, mainStage);
            Sounds.startWater();
        }

        if (water != null) {
            nWaterTiles = water.getPath().size() - 1;
            score = startScore + nWaterTiles * params.scoreFactor;
            scoreLabel.setText("score: " + score);

            int distance2go = params.distance2win - nWaterTiles;
            if (distance2go < 0)
                distance2go = 0;
            distanceLabel.setText("distance: " + distance2go);

            if (water.isStopped()) {
                endLevel();
                water.remove();
                water = null;
            }
        }
    }

    public void spawnPipe() {
        nextPipes.remove(0);
        nextPipes.add(PipeType.randomPlayableType());
        updatePipePreview();
    }

    public void enableClicks() {
        clicksDisabled = false;
    }

    public void disableClicks() {
        clicksDisabled = true;
    }

    private void endLevel() {
        if (nWaterTiles < params.distance2win) {
            textOverlay.setTitle("Game over");
            textOverlay.setSubtitle("Click to restart");
            textOverlay.setVisible(true);

            Sounds.gameOver();

            clickCallback = () -> PipesGame.setActiveScreen(new LevelScreen());

        } else {
            textOverlay.setTitle("Level complete");
            textOverlay.setSubtitle("Click to proceed to next level");
            textOverlay.setVisible(true);

            Sounds.winLevel();

            clickCallback = () -> PipesGame.setActiveScreen(new LevelScreen(nLevel + 1, score));
        }
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (clicksDisabled) {
            return true;
        }

        if (clickCallback != null) {
                clickCallback.run();
                return true;
        } else {
            return false;
        }
    }

    private void updatePipePreview() {
        for (int i=0; i < N_NEXT_TILES; i++) {
            Tile tilePreview = pipePreviews.get(i);
            tilePreview.setPipe(nextPipes.get(i));
        }
    }

    public PipeType getNextPipe() {
        return nextPipes.get(0);
    }
}
