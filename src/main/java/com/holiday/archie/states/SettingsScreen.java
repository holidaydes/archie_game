package com.holiday.archie.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.holiday.archie.core.Archie;
import com.holiday.archie.handlers.ArchieRainAnimation;
import com.holiday.archie.managers.AudioManager;

public class SettingsScreen implements Screen {

    private final Archie game;

    private final Table inputTable;
    private final Table menuControlTable;
    private final Skin skin;
    private final Sound backSound;
    private final Sound selectSound;
    private final Sound validateSound;
    private final Music mainMusic;

    private final ArchieRainAnimation pow;

    private int buttonCode = 0;

    private final TextButton back;
    private final TextButton setDefault;
    private final TextButton resetSave;

    private final Label keyboardLabel;
    private final Label controllerLabel;
    private final Label leftLabel;
    private final Label rightLabel;
    private final Label jumpLabel;
    private final Label runLabel;
    private final Label swordLabel;
    private final Label shieldLabel;
    private final Label pauseLabel;
    private final Label exitLabel;

    private final TextField leftButtonKeyboard;
    private final TextField rightButtonKeyboard;
    private final TextField jumpButtonKeyboard;
    private final TextField runButtonKeyboard;
    private final TextField swordButtonKeyboard;
    private final TextField shieldButtonKeyboard;
    private final TextField pauseButtonKeyboard;
    private final TextField exitButtonKeyboard;

    private final TextField leftButtonController;
    private final TextField rightButtonController;
    private final TextField jumpButtonController;
    private final TextField runButtonController;
    private final TextField swordButtonController;
    private final TextField shieldButtonController;
    private final TextField pauseButtonController;
    private final TextField exitButtonController;

    public SettingsScreen(Archie game) {
        this.game = game;

        skin = new Skin(Gdx.files.internal("skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
        inputTable = new Table();
        menuControlTable = new Table();
        backSound = AudioManager.SOUND_MENU_BACK.loadSound();
        selectSound = AudioManager.SOUND_MENU_SELECT.loadSound();
        validateSound = AudioManager.SOUND_MENU_VALIDATE.loadSound();
        mainMusic = AudioManager.MUSIC_MAIN_MENU.loadMusic();
        mainMusic.setLooping(true);

        pow = new ArchieRainAnimation("pic/pow.png", 200, 200, 250, 'x', 1024, 600, 1000000000);

        setDefault = new TextButton("Set Default", skin);
        resetSave = new TextButton("Reset saved game", skin);
        back = new TextButton("Back", skin);

        keyboardLabel = new Label("Keyboard", skin);
        controllerLabel = new Label("Controller", skin);
        leftLabel = new Label("Left", skin);
        rightLabel = new Label("Right", skin);
        jumpLabel = new Label("Jump", skin);
        runLabel = new Label("Run", skin);
        swordLabel = new Label("Sword", skin);
        shieldLabel = new Label("Shield", skin);
        pauseLabel = new Label("Pause", skin);
        exitLabel = new Label("Exit", skin);

        leftButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getLeftButton()), skin);
        rightButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getRightButton()), skin);
        jumpButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getJumpButton()), skin);
        runButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getRunButton()), skin);
        swordButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getSwordButton()), skin);
        shieldButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getShieldButton()), skin);
        pauseButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getPauseButton()), skin);
        exitButtonKeyboard = new TextField(Input.Keys.toString(game.controls.getExitButton()), skin);

        leftButtonController = new TextField("" + game.controls.getCLeftButton(), skin);
        rightButtonController = new TextField("" + game.controls.getCRightButton(), skin);
        jumpButtonController = new TextField("" + game.controls.getCJumpButton(), skin);
        runButtonController = new TextField("" + game.controls.getCRunButton(), skin);
        swordButtonController = new TextField("" + game.controls.getCSwordButton(), skin);
        shieldButtonController = new TextField("" + game.controls.getCShieldButton(), skin);
        pauseButtonController = new TextField("" + game.controls.getCPauseButton(), skin);
        exitButtonController = new TextField("" + game.controls.getCExitButton(), skin);
    }

    @Override
    public void dispose() {
        inputTable.clear();
        menuControlTable.clear();
        skin.dispose();
        mainMusic.dispose();
        pow.dispose();
        game.stage.clear();
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
    public void render(float dtime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        pow.animate(game.batch);
        game.batch.begin();
        game.font36.setColor(Color.BLACK);
        game.font36.draw(game.batch, "TIP: for the controller users\n- first push button on the controller\n- then click on desired textfield", Archie.WIDTH - 450, Archie.HEIGHT - 150);
        game.font36.draw(game.batch, "TIP: about buttons...\n- set default: both keyboard and controller\nset to default\n- reset save game: delete your saved game", Archie.WIDTH - 450, Archie.HEIGHT - 350);
        game.font72.setColor(Color.BLACK);
        game.font72.draw(game.batch, "Settings", Archie.WIDTH - 250, Archie.HEIGHT - 20);
        game.batch.end();
        game.stage.act(dtime);
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
        final int fieldWidth = 150;
        final int fieldHeight = 45;

        mainMusic.play();
        //controller buttons
        Controllers.addListener(new ControllerAdapter() {
            public boolean buttonDown(Controller controller, int buttonIndex) {
                selectSound.play();
                buttonCode = buttonIndex;
                return false;
            }
        });

        leftButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                leftButtonController.setText("" + buttonCode);
                game.controls.setCLeftButton(buttonCode);
            }
        });
        rightButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                rightButtonController.setText("" + buttonCode);
                game.controls.setCRightButton(buttonCode);
            }
        });
        jumpButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                jumpButtonController.setText("" + buttonCode);
                game.controls.setCJumpButton(buttonCode);
            }
        });
        runButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                runButtonController.setText("" + buttonCode);
                game.controls.setCRunButton(buttonCode);
            }
        });
        swordButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                swordButtonController.setText("" + buttonCode);
                game.controls.setCSwordButton(buttonCode);
            }
        });
        shieldButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                shieldButtonController.setText("" + buttonCode);
                game.controls.setCShieldButton(buttonCode);
            }
        });
        pauseButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                pauseButtonController.setText("" + buttonCode);
                game.controls.setCPauseButton(buttonCode);
            }
        });
        exitButtonController.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectSound.play();
                exitButtonController.setText("" + buttonCode);
                game.controls.setCExitButton(buttonCode);
            }
        });

        //keyboard buttons
        leftButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                leftButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setLeftButton(keycode);
                return false;
            }
        });
        rightButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                rightButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setRightButton(keycode);
                return false;
            }
        });
        jumpButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                jumpButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setJumpButton(keycode);
                return false;
            }
        });
        runButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                runButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setRunButton(keycode);
                return false;
            }
        });
        swordButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                swordButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setSwordButton(keycode);
                return false;
            }
        });
        shieldButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                shieldButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setShieldButton(keycode);
                return false;
            }
        });
        pauseButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                pauseButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setPauseButton(keycode);
                return false;
            }
        });
        exitButtonKeyboard.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                selectSound.play();
                exitButtonKeyboard.setText(Input.Keys.toString(keycode));
                game.controls.setExitButton(keycode);
                return false;
            }
        });

        //buttons
        setDefault.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                game.controls.setDefault();
                reloadControls();
            }
        });
        resetSave.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                validateSound.play();
                game.pref.resetSavedGame();
            }
        });
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

        //table
        inputTable.top().left().padLeft(25).padTop(50);
        inputTable.add();
        inputTable.add(keyboardLabel).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(controllerLabel).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(leftLabel).size(fieldWidth, fieldHeight);
        inputTable.add(leftButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(leftButtonController).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(rightLabel).size(fieldWidth, fieldHeight);
        inputTable.add(rightButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(rightButtonController).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(jumpLabel).size(fieldWidth, fieldHeight);
        inputTable.add(jumpButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(jumpButtonController).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(runLabel).size(fieldWidth, fieldHeight);
        inputTable.add(runButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(runButtonController).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(swordLabel).size(fieldWidth, fieldHeight);
        inputTable.add(swordButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(swordButtonController).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(shieldLabel).size(fieldWidth, fieldHeight);
        inputTable.add(shieldButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(shieldButtonController).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(pauseLabel).size(fieldWidth, fieldHeight);
        inputTable.add(pauseButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(pauseButtonController).size(fieldWidth, fieldHeight);
        inputTable.row();
        inputTable.add(exitLabel).size(fieldWidth, fieldHeight);
        inputTable.add(exitButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        inputTable.add(exitButtonController).size(fieldWidth, fieldHeight);

        inputTable.setFillParent(true);
        //table.setDebug(true);

        menuControlTable.bottom().right();
        menuControlTable.row().padBottom(25);
        menuControlTable.add(setDefault).size(230, 60).right().padRight(25);
        menuControlTable.add(resetSave).size(230, 60).right().padRight(25);
        menuControlTable.add(back).size(100, 60).right().padRight(25);

        menuControlTable.setFillParent(true);
        //table2.setDebug(true);

        game.stage.addActor(inputTable);
        game.stage.addActor(menuControlTable);

        Gdx.input.setInputProcessor(game.stage);
    }

    //reload the entire controls keys from controls pref
    private void reloadControls() {
        leftButtonKeyboard.setText(Input.Keys.toString(game.controls.getLeftButton()));
        rightButtonKeyboard.setText(Input.Keys.toString(game.controls.getRightButton()));
        jumpButtonKeyboard.setText(Input.Keys.toString(game.controls.getJumpButton()));
        runButtonKeyboard.setText(Input.Keys.toString(game.controls.getRunButton()));
        swordButtonKeyboard.setText(Input.Keys.toString(game.controls.getSwordButton()));
        shieldButtonKeyboard.setText(Input.Keys.toString(game.controls.getShieldButton()));
        pauseButtonKeyboard.setText(Input.Keys.toString(game.controls.getPauseButton()));
        exitButtonKeyboard.setText(Input.Keys.toString(game.controls.getExitButton()));

        leftButtonController.setText("" + game.controls.getCLeftButton());
        rightButtonController.setText("" + game.controls.getCRightButton());
        jumpButtonController.setText("" + game.controls.getCJumpButton());
        runButtonController.setText("" + game.controls.getCRunButton());
        swordButtonController.setText("" + game.controls.getCSwordButton());
        shieldButtonController.setText("" + game.controls.getCShieldButton());
        pauseButtonController.setText("" + game.controls.getCPauseButton());
        exitButtonController.setText("" + game.controls.getCExitButton());
    }
}
