package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;


public class LevelScreen extends BaseScreen {
    private Label levelLabel;
    private Label scoreLabel;
    private Label distanceLabel;

    @Override
    protected void initialize() {
        BaseActor gameBackground = new BaseActor(0, 0, mainStage);
        gameBackground.loadTexture("screen_background.png");
        BaseActor.setWorldBounds(gameBackground);

        //new GameBoard(LevelLayout.BOARD_X, LevelLayout.flipYCoord(LevelLayout.BOARD_Y), mainStage);
        Rectangle boardRect = LevelLayout.standard2gdxCoords(LevelLayout.BOARD);
        new GameBoard(boardRect.x, boardRect.y, mainStage);

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

    }

    @Override
    protected void update(float dt) {

    }

}
