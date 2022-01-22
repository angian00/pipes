package com.angian.libgdx.pipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    private static final float audioVolume = 1.0f;

    private static Sound startLevel;
    private static Sound startWater;
    private static Sound winLevel;
    private static Sound gameOver;
    private static Sound plop;
    private static Sound crunch;


    public static void initialize() {
        startLevel = Gdx.audio.newSound(Gdx.files.internal("sounds/start_countdown.wav"));
        startWater = Gdx.audio.newSound(Gdx.files.internal("sounds/start_water.wav"));
        winLevel = Gdx.audio.newSound(Gdx.files.internal("sounds/win_level.wav"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("sounds/game_over.wav"));
        plop = Gdx.audio.newSound(Gdx.files.internal("sounds/plop.wav"));
        crunch = Gdx.audio.newSound(Gdx.files.internal("sounds/crunch.wav"));
    }

    public static void startLevel() {
        startLevel.play(audioVolume);
    }

    public static void startWater() {
        startWater.play(audioVolume);
    }

    public static void winLevel() {
        winLevel.play(audioVolume);
    }

    public static void gameOver() {
        gameOver.play(audioVolume);
    }

    public static void plop() {
        plop.play(audioVolume);
    }

    public static void crunch() {
        crunch.play(audioVolume);
    }

}
