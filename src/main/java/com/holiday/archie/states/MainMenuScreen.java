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

import java.util.Random;

public class MainMenuScreen implements Screen {
    private final Archie game;
    private int levelNumber;

    private final Table table;
    private final Skin skin;

    private final TextButton newGame;
    private final TextButton levelSelect;
    private final TextButton setting;
    private final TextButton exit;

    private final Sound back;
    private final Sound validate;
    private final Music mainMusic;

    private final Texture background;
    private final ArchieRainAnimation leaf;

    public MainMenuScreen(Archie game) {
        this.game = game;

        table = new Table();
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
        back = AudioManager.SOUND_MENU_BACK.loadSound();
        validate = AudioManager.SOUND_MENU_VALIDATE.loadSound();
        mainMusic = AudioManager.MUSIC_MAIN_MENU.loadMusic();
        mainMusic.setLooping(true);

        game.font192.setColor(Color.BLACK);

        Random rnd = new Random();
        int mainBackgroundNumber = rnd.nextInt(3);
        background = new Texture(Gdx.files.internal("pic/main/archiemain" + mainBackgroundNumber + ".png"));
        leaf = new ArchieRainAnimation("pic/leaf.png", 200, 200, 200, 'y', 1024, 600, 800000000);

        levelNumber = 0;
        for (int i = 1; i < 5; i++) {
            if (game.pref.getLevelEnabled(i)) {
                levelNumber++;
            }
        }
        if (levelNumber > 0) {
            newGame = new TextButton("Continue", skin);
        } else {
            newGame = new TextButton("New Game", skin);
        }
        levelSelect = new TextButton("Level Select", skin);
        setting = new TextButton("Settings", skin);
        exit = new TextButton("Exit", skin);

        for (int i = 0; i < 4; i++) {
            game.pref.getEnabledSkin(i);
            game.pref.getEnabledSword(i);
            game.pref.getEnabledShield(i);
            game.pref.getLevelEnabled(i);
        }
    }

    @Override
    public void dispose() {
        background.dispose();
        leaf.dispose();
        skin.dispose();
        back.dispose();
        validate.dispose();
        mainMusic.dispose();
        game.stage.clear();
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
    public void render(float dtime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        leaf.animate(game.batch);
        game.batch.begin();
        game.batch.draw(background, 0, 0);

        game.font192.draw(game.batch, "Archie", Archie.WIDTH - 350, Archie.HEIGHT - 10);
        game.batch.end();

        game.stage.act(dtime);
        game.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void show() {
        mainMusic.play();
        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new StartScreen(game, levelNumber));
            }
        });
        levelSelect.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new LevelSelectScreen(game));
            }
        });
        setting.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validate.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new SettingsScreen(game));
            }
        });
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back.play();
                try {
                    Thread.sleep(470);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.dispose();
            }
        });

        table.add(newGame).size(175, 60).padBottom(10).row();
        table.add(levelSelect).size(175, 60).padBottom(10).row();
        table.add(setting).size(175, 60).padBottom(10).row();
        table.add(exit).size(175, 60).row();

        table.setFillParent(true);
        table.padLeft(800);
        game.stage.addActor(table);

        Gdx.input.setInputProcessor(game.stage);
    }

}
