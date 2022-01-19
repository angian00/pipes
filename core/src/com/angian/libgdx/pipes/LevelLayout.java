package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;

public class LevelLayout {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 900;

    public static final int TILE_SIZE = 100;

    public static final Rectangle BOARD = new Rectangle(180, 180, 1000, 700);

    public static final int TEXT_FONT_SIZE = 32;
    public static final int TEXT_Y = 100;
    public static final int TEXT_HEIGHT = 70;

    public static final Rectangle TEXT_LEVEL = new Rectangle(200, TEXT_Y, 200, TEXT_HEIGHT);
    public static final int TEXT_LEVEL_ALIGN = Align.left;

    public static final Rectangle TEXT_SCORE = new Rectangle(180, TEXT_Y, 1000, TEXT_HEIGHT);
    public static final int TEXT_SCORE_ALIGN = Align.center;

    public static final Rectangle TEXT_DISTANCE = new Rectangle(960, TEXT_Y, 200, TEXT_HEIGHT);
    public static final int TEXT_DISTANCE_ALIGN = Align.right;

    public static final Rectangle TILE_PREVIEW = new Rectangle(10, 180, 110, 600);
    public static final int TILE_PREVIEW_PADDING = 20;

    public static final Rectangle TIMER = new Rectangle(135, 380, 20, 500);
    public static final Color TIMER_COLOR = Color.ORANGE;


    public static final int PIPE_INSIDE_SIZE = 30;
    public static final int PIPE_BEND_RADIUS = 15;
    public static final int PIPE_BEND_STRAIGHT_LEN = 50;


    public static Rectangle standard2gdxCoords(Rectangle layoutRect) {
        float flippedY = SCREEN_HEIGHT - layoutRect.y -layoutRect.height;
        return new Rectangle(layoutRect.x, flippedY, layoutRect.width, layoutRect.height);
    }
}
