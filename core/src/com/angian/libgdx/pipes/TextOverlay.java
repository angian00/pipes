package com.angian.libgdx.pipes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TextOverlay extends BaseActor {
    private final LevelScreen level;
    private final Label title;
    private final Label subtitle;


    public TextOverlay(LevelScreen _level, Stage s) {
        super(s);
        level = _level;
        setSize(LevelLayout.SCREEN_WIDTH, LevelLayout.SCREEN_HEIGHT);

        Table layoutTable = new Table();
        layoutTable.setFillParent(true);
        s.addActor(layoutTable);

        title = new Label("", Styles.titleStyle);
        subtitle = new Label("", Styles.labelStyle);
        title.setColor(Color.ORANGE);
        subtitle.setColor(Color.ORANGE);

        layoutTable.row();
        layoutTable.add(title).center();
        layoutTable.row();
        layoutTable.add(subtitle).center();

        this.setVisible(false);
    }

    public void setTitle(String text) {
        title.setText(text);
        title.setVisible(true);
    }

    public void setSubtitle(String text) {
        subtitle.setText(text);
        subtitle.setVisible(true);
    }


    public void countdown() {
        final float COUNTDOWN_STEP_DURATION = 0.05f;
        setSubtitle("");
        setOpacity(0.0f);
        setVisible(true);

        level.disableClicks();

        Action countdown = Actions.sequence(
                Actions.run( () -> setTitle("3") ),
                //Actions.run( () -> blip.play() ),
                Actions.alpha(1),
                Actions.scaleTo(5.1f,5.1f, COUNTDOWN_STEP_DURATION),
                //Actions.run( () -> blip.play() ),
                Actions.scaleTo(1.0f,1.0f, COUNTDOWN_STEP_DURATION),
                Actions.delay(0.5f),
                Actions.fadeOut(0.5f - 2 * COUNTDOWN_STEP_DURATION),
                Actions.run( () -> setTitle("2") ),
                //Actions.run( () -> blip.play() ),
                Actions.alpha(1),
                Actions.scaleTo(5.1f,5.1f, COUNTDOWN_STEP_DURATION),
                Actions.scaleTo(1.0f,1.0f, COUNTDOWN_STEP_DURATION),
                Actions.delay(0.5f),
                Actions.fadeOut(0.5f - 2 * COUNTDOWN_STEP_DURATION),
                Actions.run( () -> setTitle("1") ),
                //Actions.run( () -> blip.play() ),
                Actions.alpha(1),
                Actions.scaleTo(1.1f,1.1f, COUNTDOWN_STEP_DURATION),
                Actions.scaleTo(1.0f,1.0f, COUNTDOWN_STEP_DURATION),
                Actions.delay(0.5f),
                Actions.fadeOut(0.5f - 2 * COUNTDOWN_STEP_DURATION),
                Actions.run( () -> setTitle("Go!") ),
                //Actions.run( () -> tone.play() ),
                Actions.alpha(1),
                Actions.delay(0.5f),
                Actions.run(() -> title.setVisible(false)),
                Actions.run(level::enableClicks)
        );

        title.addAction(countdown);
    }

}
