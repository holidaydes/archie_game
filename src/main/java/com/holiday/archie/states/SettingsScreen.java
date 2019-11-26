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
import com.holiday.archie.handlers.ArchieConstantVariables;

public class SettingsScreen implements Screen{

	private final Archie game;
	
	private Table table;
	private Table table2;
	private Skin skin;
	private Sound backSound;
	private Sound selectSound;
	private Sound validateSound;
	private Music mainMusic;
	
    private ArchieRainAnimation pow;
    
	private final int fieldWidth = 150;
	private final int fieldHeight = 45;
	
	private int buttonCode = 0;
	
	private TextButton back;
	private TextButton setDefault;
	private TextButton resetSave;
	
	private Label keyboardLabel;
	private Label controllerLabel;
	private Label leftLabel;
	private Label rightLabel;
	private Label jumpLabel;
	private Label runLabel;
	private Label swordLabel;
	private Label shieldLabel;
	private Label pauseLabel;
	private Label exitLabel;
	
	private TextField leftButtonKeyboard;
	private TextField rightButtonKeyboard;
	private TextField jumpButtonKeyboard;
	private TextField runButtonKeyboard;
	private TextField swordButtonKeyboard;
	private TextField shieldButtonKeyboard;
	private TextField pauseButtonKeyboard;
	private TextField exitButtonKeyboard;
	
	private TextField leftButtonController;
	private TextField rightButtonController;
	private TextField jumpButtonController;
	private TextField runButtonController;
	private TextField swordButtonController;
	private TextField shieldButtonController;
	private TextField pauseButtonController;
	private TextField exitButtonController;
	
	public SettingsScreen(Archie game){
		this.game = game;
		
		skin = new Skin(Gdx.files.internal("skins/uiskin.json"), new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas")));
		table = new Table();
		table2 = new Table();
		backSound = Gdx.audio.newSound(Gdx.files.internal(ArchieConstantVariables.soundPath[1]));
		selectSound = Gdx.audio.newSound(Gdx.files.internal(ArchieConstantVariables.soundPath[2]));
		validateSound = Gdx.audio.newSound(Gdx.files.internal(ArchieConstantVariables.soundPath[0]));
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal(ArchieConstantVariables.musicPath[0]));
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
		table.clear();
		table2.clear();
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
		game.font36.draw(game.batch, "TIP: for the controller users\n- first push button on the controller\n- then click on desired textfield", Archie.WIDTH-450, Archie.HEIGHT-150);
		game.font36.draw(game.batch, "TIP: about buttons...\n- set default: both keyboard and controller\nset to default\n- reset save game: delete your saved game", Archie.WIDTH-450, Archie.HEIGHT-350);
		game.font72.setColor(Color.BLACK);
		game.font72.draw(game.batch, "Settings", Archie.WIDTH-250, Archie.HEIGHT-20);
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
		mainMusic.play();
		//controller buttons
		Controllers.addListener(new ControllerAdapter() {
			public boolean buttonDown (Controller controller, int buttonIndex) {
				selectSound.play();
				buttonCode = buttonIndex;
				return false;
			}
		});
		
		leftButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				leftButtonController.setText(""+buttonCode);
				game.controls.setCLeftButton(buttonCode);
			}
		});
		rightButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				rightButtonController.setText(""+buttonCode);
				game.controls.setCRightButton(buttonCode);
			}
		});
		jumpButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				jumpButtonController.setText(""+buttonCode);
				game.controls.setCJumpButton(buttonCode);
			}
		});
		runButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				runButtonController.setText(""+buttonCode);
				game.controls.setCRunButton(buttonCode);
			}
		});
		swordButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				swordButtonController.setText(""+buttonCode);
				game.controls.setCSwordButton(buttonCode);
			}
		});
		shieldButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				shieldButtonController.setText(""+buttonCode);
				game.controls.setCShieldButton(buttonCode);
			}
		});
		pauseButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				pauseButtonController.setText(""+buttonCode);
				game.controls.setCPauseButton(buttonCode);
			}
		});
		exitButtonController.addListener(new ClickListener(){
			@Override
            public void clicked(InputEvent event, float x, float y) {
				selectSound.play();
				exitButtonController.setText(""+buttonCode);
				game.controls.setCExitButton(buttonCode);
			}
		});
		
		//keyboard buttons
		leftButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				leftButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setLeftButton(keycode);
				return false;
			}
		});
		rightButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				rightButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setRightButton(keycode);
				return false;
			}
		});
		jumpButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				jumpButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setJumpButton(keycode);
				return false;
			}
		});
		runButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				runButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setRunButton(keycode);
				return false;
			}
		});
		swordButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				swordButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setSwordButton(keycode);
				return false;
			}
		});
		shieldButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				shieldButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setShieldButton(keycode);
				return false;
			}
		});
		pauseButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				pauseButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setPauseButton(keycode);
				return false;
			}
		});
		exitButtonKeyboard.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keycode){
				selectSound.play();
				exitButtonKeyboard.setText(Input.Keys.toString(keycode));
				game.controls.setExitButton(keycode);
				return false;
			}
		});
		
		//buttons
        setDefault.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	validateSound.play();
            	game.controls.setDefault();
            	reloadControls();
            }
        });
        resetSave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	validateSound.play();
            	game.pref.resetSavedGame();
            }
        });
        back.addListener(new ClickListener(){
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
        table.top().left().padLeft(25).padTop(50);
        table.add();
        table.add(keyboardLabel).size(fieldWidth, fieldHeight).padRight(25);
        table.add(controllerLabel).size(fieldWidth, fieldHeight);
        table.row();
        table.add(leftLabel).size(fieldWidth, fieldHeight);
        table.add(leftButtonKeyboard).size(fieldWidth,fieldHeight).padRight(25);
        table.add(leftButtonController).size(fieldWidth,fieldHeight);
        table.row();
        table.add(rightLabel).size(fieldWidth, fieldHeight);
        table.add(rightButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        table.add(rightButtonController).size(fieldWidth, fieldHeight);
        table.row();
        table.add(jumpLabel).size(fieldWidth, fieldHeight);
        table.add(jumpButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        table.add(jumpButtonController).size(fieldWidth, fieldHeight);
        table.row();
        table.add(runLabel).size(fieldWidth, fieldHeight);
        table.add(runButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        table.add(runButtonController).size(fieldWidth, fieldHeight);
        table.row();
        table.add(swordLabel).size(fieldWidth, fieldHeight);
        table.add(swordButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        table.add(swordButtonController).size(fieldWidth, fieldHeight);
        table.row();
        table.add(shieldLabel).size(fieldWidth, fieldHeight);
        table.add(shieldButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        table.add(shieldButtonController).size(fieldWidth, fieldHeight);
        table.row();
        table.add(pauseLabel).size(fieldWidth, fieldHeight);
        table.add(pauseButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        table.add(pauseButtonController).size(fieldWidth, fieldHeight);
        table.row();
        table.add(exitLabel).size(fieldWidth, fieldHeight);
        table.add(exitButtonKeyboard).size(fieldWidth, fieldHeight).padRight(25);
        table.add(exitButtonController).size(fieldWidth, fieldHeight);
        
        table.setFillParent(true);
        //table.setDebug(true);
        
        table2.bottom().right();
        table2.row().padBottom(25);
        table2.add(setDefault).size(230,60).right().padRight(25);
        table2.add(resetSave).size(230,60).right().padRight(25);
        table2.add(back).size(100,60).right().padRight(25);

        table2.setFillParent(true);
        //table2.setDebug(true);
        
        game.stage.addActor(table);
        game.stage.addActor(table2);
        
        Gdx.input.setInputProcessor(game.stage);
	}
	
	//reload the entire controls keys from controls pref
	private void reloadControls(){
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
