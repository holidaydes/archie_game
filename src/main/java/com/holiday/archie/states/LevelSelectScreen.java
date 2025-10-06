package com.holiday.archie.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.holiday.archie.core.Archie;
import com.holiday.archie.elements.Level;
import com.holiday.archie.handlers.ArchieRainAnimation;
import com.holiday.archie.managers.AudioManager;

public class LevelSelectScreen implements Screen {
    private final Archie game;

    private final Sound backSound;
    private final Sound selectSound;
    private final Sound validateSound;
    private final Music mainMusic;

    private final Table table;
    private final Table table2;
    private final Skin skin;
    private final Texture background;
    private final ArchieRainAnimation leaf;

    private final TextButton back;
    private final TextButton credit;

    private final TextButton level1;
    private final TextButton level2;
    private final TextButton level3;
    private final TextButton level4;
    private final TextButton level5;

    public LevelSelectScreen(Archie game) {
        this.game = game;

        backSound = AudioManager.SOUND_MENU_BACK.loadSound();
        selectSound = AudioManager.SOUND_MENU_SELECT.loadSound();
        validateSound = AudioManager.SOUND_MENU_VALIDATE.loadSound();
        mainMusic = AudioManager.MUSIC_MAIN_MENU.loadMusic();
        mainMusic.setLooping(true);

        background = new Texture(Gdx.files.internal("pic/main/archiemain4.png"));
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
        table = new Table();
        table2 = new Table();
        leaf = new ArchieRainAnimation("pic/leaf.png", 200, 200, 200, 'y', 1024, 600, 800000000);

        back = new TextButton("Back", skin);
        credit = new TextButton("Credit", skin);

        level1 = new TextButton(Level.ARCHIE_LOST.name, skin);
        level2 = new TextButton(Level.THE_FOREST.name, skin);
        level3 = new TextButton(Level.INTO_THE_DEEP.name, skin);
        level4 = new TextButton(Level.NEAR_THE_END.name, skin);
        level5 = new TextButton(Level.THE_END.name, skin);

        if (!game.pref.getLevelEnabled(1)) {
            level2.setVisible(false);
        }
        if (!game.pref.getLevelEnabled(2)) {
            level3.setVisible(false);
        }
        if (!game.pref.getLevelEnabled(3)) {
            level4.setVisible(false);
        }
        if (!game.pref.getLevelEnabled(4)) {
            level5.setVisible(false);
        }
    }

    @Override
    public void dispose() {
        game.stage.clear();
        table.clear();
        table2.clear();
        skin.dispose();
        background.dispose();
        leaf.dispose();
        backSound.dispose();
        selectSound.dispose();
        validateSound.dispose();
        mainMusic.dispose();
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
    public void render(float dt) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        leaf.animate(game.batch);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();
        game.stage.act(dt);
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
        mainMusic.play();
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backSound.play();
                try {
                    Thread.sleep(470);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new MainMenuScreen(game));
            }
        });

        credit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new TheEndScreen(game, false));
            }
        });

        level1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new StartScreen(game, 0));
            }
        });

        level2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new StartScreen(game, 1));
            }
        });

        level3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new StartScreen(game, 2));
            }
        });

        level4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new StartScreen(game, 3));
            }
        });

        level5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                try {
                    Thread.sleep(790);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                game.setScreen(new StartScreen(game, 4));
            }
        });

        table.center();
        table.add(level1).size(150, 100).pad(5);
        table.add(level2).size(150, 100).pad(5);
        table.add(level3).size(150, 100).pad(5);
        table.add(level4).size(150, 100).pad(5);
        table.add(level5).size(150, 100);
        table.setFillParent(true);
        //table.setDebug(true);

        table2.bottom().right();
        table2.row().padBottom(25);
        table2.add(credit).size(150, 60).right().padRight(25);
        table2.add(back).size(150, 60).right().padRight(25);
        table2.setFillParent(true);
        //table2.setDebug(true);

        game.stage.addActor(table);
        game.stage.addActor(table2);

        Gdx.input.setInputProcessor(game.stage);
    }
}
