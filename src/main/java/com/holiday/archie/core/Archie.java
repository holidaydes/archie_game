package com.holiday.archie.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.holiday.archie.handlers.ArchieControlsPreferences;
import com.holiday.archie.handlers.ArchiePreferences;
import com.holiday.archie.states.MainMenuScreen;

public final class Archie extends Game {
	public static final String TITLE = "Archie";
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 600;
	public static final int SCALE = 1;
	
    public BitmapFont font36;
    public BitmapFont font72;
    public BitmapFont font192;

	public Camera camera;
	public SpriteBatch batch;
	public Stage stage;
	public Viewport viewport;
	
	public ArchieControlsPreferences controls;
	public ArchiePreferences pref;
    
	@Override
	public void create() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/archie.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 28;
		font36 = generator.generateFont(parameter);
		parameter.size = 56;
		font72 = generator.generateFont(parameter);
		parameter.size = 154;
		font192 = generator.generateFont(parameter);
		generator.dispose();
		
		batch = new SpriteBatch();
		camera = new PerspectiveCamera();
	    viewport = new FitViewport(WIDTH, HEIGHT, camera);
		stage = new Stage(new FitViewport(WIDTH, HEIGHT));
		
		controls = new ArchieControlsPreferences();
		pref = new ArchiePreferences();
		
		this.setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void dispose() {
		Gdx.app.exit();
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.getViewport().update(width, height, true);
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
