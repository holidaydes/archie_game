package com.holiday.archie.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.holiday.archie.core.Archie;
import com.holiday.archie.handlers.ArchieRainAnimation;
import com.holiday.archie.managers.AudioManager;

public class TheEndScreen implements Screen {

    private final Archie game;

    private final Sound backSound;
    private final Sound validateSound;
    private final Music mainMusic;
    private final ArchieRainAnimation leaf;
    private final Texture background;
    private final Texture theEnd;

    private final Table table;
    private final Skin skin;

    private final TextButton back;
    private final String[] credits;
    private final boolean isEnd;

    public TheEndScreen(Archie game, boolean end) {
        this.game = game;
        isEnd = end;

        backSound = AudioManager.SOUND_MENU_BACK.loadSound();
        validateSound = AudioManager.SOUND_MENU_VALIDATE.loadSound();
        mainMusic = AudioManager.MUSIC_MAIN_MENU.loadMusic();
        mainMusic.setLooping(true);
        leaf = new ArchieRainAnimation("pic/leaf.png", 200, 200, 200, 'y', 1024, 600, 800000000);
        background = new Texture(Gdx.files.internal("pic/main/archiemain3.png"));
        theEnd = new Texture(Gdx.files.internal("pic/theEnd.png"));

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
        table = new Table();

        if (isEnd) {
            back = new TextButton("Fin", skin);
        } else {
            back = new TextButton("Back", skin);
        }

        credits = new String[5];

        credits[0] = "coded by holiday in 2015";
        credits[1] = "used programs:";
        credits[2] = "eclipse luna service release 2 (4.4.2)\nlibgdx 1.5.3\ntiled map editor version 0.11.0\ngimp 2.8.14\nskin editor v0.31\naudacity 2.1.10";
        credits[3] = "used sound sources from freesound.org";
        credits[4] = "thanks for sounds to\n"
                + "broumbroum, cman634\n"
                + "coby12388, erokia\n"
                + "foolboymedia, flick3r\n"
                + "kuzyaburst, lloydevans09\n"
                + "michel88, smokebomb99\n";
    }

    @Override
    public void dispose() {
        backSound.dispose();
        validateSound.dispose();
        mainMusic.dispose();
        leaf.dispose();
        background.dispose();
        theEnd.dispose();
        game.stage.clear();
        table.clear();
        skin.dispose();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float arg0) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        leaf.animate(game.batch);
        game.batch.begin();
        game.font36.setColor(Color.BLACK);
        if (isEnd) {
            game.font72.setColor(Color.BLACK);
            game.font72.draw(game.batch, "THE END", 50, Archie.HEIGHT - 75);
            game.font36.draw(game.batch, "archie finally\ngot out from\nthe forrest.\nand he lives\nhappily ever\nafter.", 325, Archie.HEIGHT - 300);
            game.batch.draw(theEnd, 50, 25);
        }
        if (!isEnd) {
            game.batch.draw(background, 0, 0);
        }
        game.font36.draw(game.batch, credits[0], Archie.WIDTH / 2f, Archie.HEIGHT - 25f);
        game.font36.draw(game.batch, credits[1], Archie.WIDTH / 2f + 25f, Archie.HEIGHT - 75f);
        game.font36.draw(game.batch, credits[2], Archie.WIDTH / 2f + 50f, Archie.HEIGHT - 100f);
        game.font36.draw(game.batch, credits[3], Archie.WIDTH / 2f + 25f, Archie.HEIGHT - 300f);
        game.font36.draw(game.batch, credits[4], Archie.WIDTH / 2f + 50f, Archie.HEIGHT - 350f);
        game.batch.end();
        game.stage.act(arg0);
        game.stage.draw();
    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        try {
            mainMusic.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isEnd) {
                    validateSound.play();
                    try {
                        Thread.sleep(790);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    game.setScreen(new MainMenuScreen(game));
                } else {
                    backSound.play();
                    try {
                        Thread.sleep(470);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    game.setScreen(new LevelSelectScreen(game));
                }
            }
        });
        table.bottom().right();
        table.row().padBottom(25);
        table.add(back).size(150, 60).right().padRight(25);
        table.setFillParent(true);
        //table.setDebug(true);

        game.stage.addActor(table);

        Gdx.input.setInputProcessor(game.stage);
    }
}
