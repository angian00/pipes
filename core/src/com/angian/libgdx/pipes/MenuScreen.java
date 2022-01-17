package com.angian.libgdx.pipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends BaseScreen {
	public void initialize() {
		BaseActor screenBackground = new BaseActor(0, 0, mainStage);
		screenBackground.loadTexture("start_screen.png");
		screenBackground.setSize(1200, 900);
	}


	public void update(float dt) {
		if (Gdx.input.isKeyPressed(Input.Keys.S))
			PipesGame.setActiveScreen(new LevelScreen());
	}


	@Override
	public boolean keyDown(int keyCode) {
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
			PipesGame.setActiveScreen(new LevelScreen());
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();

		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		PipesGame.setActiveScreen(new LevelScreen());

		return true;
	}
}
