package com.angian.libgdx.pipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    private static final float audioVolume = 1.0f;

    private static Sound countdown_3;
    private static Sound countdown_2;
    private static Sound countdown_1;
    private static Sound countdown_go;
    private static Sound startWater;
    private static Sound winLevel;
    private static Sound gameOver;
    private static Sound plop;
    private static Sound crunch;


    public static void initialize() {
        countdown_3 = Gdx.audio.newSound(Gdx.files.internal("sounds/countdown_3.wav"));
        countdown_2 = Gdx.audio.newSound(Gdx.files.internal("sounds/countdown_2.wav"));
        countdown_1 = Gdx.audio.newSound(Gdx.files.internal("sounds/countdown_1.wav"));
        countdown_go = Gdx.audio.newSound(Gdx.files.internal("sounds/countdown_go.wav"));

        startWater = Gdx.audio.newSound(Gdx.files.internal("sounds/start_water.wav"));
        winLevel = Gdx.audio.newSound(Gdx.files.internal("sounds/win_level.wav"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("sounds/game_over.wav"));
        plop = Gdx.audio.newSound(Gdx.files.internal("sounds/plop.wav"));
        crunch = Gdx.audio.newSound(Gdx.files.internal("sounds/crunch.wav"));
    }

    public static void countdown_3() {
        countdown_3.play();
    }
    public static void countdown_2() {
        countdown_2.play();
    }
    public static void countdown_1() {
        countdown_1.play();
    }
    public static void countdown_go() {
        countdown_go.play();
    }

    public static void startWater() {
        startWater.play();
    }

    public static void winLevel() {
        winLevel.play();
    }

    public static void gameOver() {
        gameOver.play();
    }

    public static void plop() {
        plop.play();
    }

    public static void crunch() {
        crunch.play();
    }

}
