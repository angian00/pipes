package com.angian.libgdx.pipes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PipesGame extends BaseGame {
	@Override
	public void create () {
		super.create();

		Sounds.initialize();
		setActiveScreen(new MenuScreen());
	}
}
