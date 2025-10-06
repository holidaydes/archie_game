package com.holiday.archie.core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] args){
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle(Archie.TITLE);
        cfg.setWindowedMode(Archie.WIDTH * Archie.SCALE, Archie.HEIGHT * Archie.SCALE);
		
		new Lwjgl3Application(new Archie(), cfg);
	}
}
