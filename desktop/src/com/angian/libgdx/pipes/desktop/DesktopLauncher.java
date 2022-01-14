package com.angian.libgdx.pipes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.angian.libgdx.pipes.PipesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pipes (clone by AnGian)";
		config.width = 1200;
		config.height = 900;
		config.forceExit = false;

		new LwjglApplication(new PipesGame(), config);
	}
}
