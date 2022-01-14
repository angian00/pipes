package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;

public class LevelScreen extends BaseScreen {

    @Override
    protected void initialize() {
        BaseActor gameBackground = new BaseActor(0, 0, mainStage);
        //gameBackground.loadTexture("water-border.jpg");
        gameBackground.setColor(Color.DARK_GRAY);
        gameBackground.setSize(1200, 900);
        BaseActor.setWorldBounds(gameBackground);

        new GameBoard(0, 0, mainStage);

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {

        return false;
    }
}
