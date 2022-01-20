package com.angian.libgdx.pipes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Styles {
    public static Label.LabelStyle labelStyle;
    public static Label.LabelStyle titleStyle;

    static {
        initStyles();
    }

    private static void initStyles() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf")); //TODO: choose font
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = LevelLayout.TEXT_FONT_SIZE;
        fontParameters.color = Color.WHITE;
        fontParameters.minFilter = Texture.TextureFilter.Linear;
        fontParameters.magFilter = Texture.TextureFilter.Linear;

        BitmapFont customFont = fontGenerator.generateFont(fontParameters);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = customFont;

        fontParameters.size = 3 * LevelLayout.TEXT_FONT_SIZE;
        customFont = fontGenerator.generateFont(fontParameters);
        titleStyle = new Label.LabelStyle();
        titleStyle.font = customFont;
    }
}
