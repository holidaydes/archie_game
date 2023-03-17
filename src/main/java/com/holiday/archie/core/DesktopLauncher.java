package com.holiday.archie.core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args){
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Archie.TITLE;
		cfg.width = Archie.WIDTH * Archie.SCALE;
		cfg.height = Archie.HEIGHT * Archie.SCALE;
		cfg.fullscreen = false;
		
		new LwjglApplication(new Archie(), cfg);
	}
}
