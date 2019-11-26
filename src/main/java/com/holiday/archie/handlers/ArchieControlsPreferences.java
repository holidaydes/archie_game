package com.holiday.archie.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ArchieControlsPreferences {
	
	private static final String PREFS_NAME = "archie_controls";
	
	private static final String PREFS_LEFT = "left";
	private static final String PREFS_RIGHT = "right";
	private static final String PREFS_JUMP = "jump";
	private static final String PREFS_RUN = "run";
	private static final String PREFS_SWORD = "sword";
	private static final String PREFS_SHIELD = "shield";
	private static final String PREFS_PAUSE = "pause";
	private static final String PREFS_EXIT = "exit";
	
	private static final String PREFS_CLEFT = "cleft";
	private static final String PREFS_CRIGHT = "cright";
	private static final String PREFS_CJUMP = "cjump";
	private static final String PREFS_CRUN = "crun";
	private static final String PREFS_CSWORD = "csword";
	private static final String PREFS_CSHIELD = "cshield";
	private static final String PREFS_CPAUSE = "cpause";
	private static final String PREFS_CEXIT = "cexit";
	
	private Preferences preferences;

	public ArchieControlsPreferences() {
		
	}

	protected Preferences getPrefs(){
		if(preferences==null){
			 preferences = Gdx.app.getPreferences(PREFS_NAME);
		}
		return preferences;
	}

	//left button
	public int getLeftButton() {
	    return getPrefs().getInteger(PREFS_LEFT, 29);
	}

	public void setLeftButton(int keycode) {
	    getPrefs().putInteger(PREFS_LEFT, keycode);
	    getPrefs().flush();
	}
	
	public int getCLeftButton() {
	    return getPrefs().getInteger(PREFS_CLEFT, 15);
	}

	public void setCLeftButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CLEFT, keyCode);
	    getPrefs().flush();
	}
	
	//right button
	public int getRightButton() {
	    return getPrefs().getInteger(PREFS_RIGHT, 32);
	}

	public void setRightButton(int keycode) {
	    getPrefs().putInteger(PREFS_RIGHT, keycode);
	    getPrefs().flush();
	}
	
	public int getCRightButton() {
	    return getPrefs().getInteger(PREFS_CRIGHT, 13);
	}

	public void setCRightButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CRIGHT, keyCode);
	    getPrefs().flush();
	}
	
	//jump button
	public int getJumpButton() {
	    return getPrefs().getInteger(PREFS_JUMP, 51);
	}

	public void setJumpButton(int keycode) {
	    getPrefs().putInteger(PREFS_JUMP, keycode);
	    getPrefs().flush();
	}
	
	public int getCJumpButton() {
	    return getPrefs().getInteger(PREFS_CJUMP, 2);
	}

	public void setCJumpButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CJUMP, keyCode);
	    getPrefs().flush();
	}
	
	//run button
	public int getRunButton() {
	    return getPrefs().getInteger(PREFS_RUN, 59);
	}

	public void setRunButton(int keycode) {
	    getPrefs().putInteger(PREFS_RUN, keycode);
	    getPrefs().flush();
	}
	
	public int getCRunButton() {
	    return getPrefs().getInteger(PREFS_CRUN, 6);
	}

	public void setCRunButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CRUN, keyCode);
	    getPrefs().flush();
	}
	
	//sword button
	public int getSwordButton() {
	    return getPrefs().getInteger(PREFS_SWORD, 39);
	}

	public void setSwordButton(int keycode) {
	    getPrefs().putInteger(PREFS_SWORD, keycode);
	    getPrefs().flush();
	}
	
	public int getCSwordButton() {
	    return getPrefs().getInteger(PREFS_CSWORD, 3);
	}

	public void setCSwordButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CSWORD, keyCode);
	    getPrefs().flush();
	}
	
	//shield button
	public int getShieldButton() {
	    return getPrefs().getInteger(PREFS_SHIELD, 38);
	}

	public void setShieldButton(int keycode) {
	    getPrefs().putInteger(PREFS_SHIELD, keycode);
	    getPrefs().flush();
	}
	
	public int getCShieldButton() {
	    return getPrefs().getInteger(PREFS_CSHIELD, 1);
	}

	public void setCShieldButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CSHIELD, keyCode);
	    getPrefs().flush();
	}
	
	//pause button
	public int getPauseButton() {
	    return getPrefs().getInteger(PREFS_PAUSE, 44);
	}

	public void setPauseButton(int keycode) {
	    getPrefs().putInteger(PREFS_PAUSE, keycode);
	    getPrefs().flush();
	}
	
	public int getCPauseButton() {
	    return getPrefs().getInteger(PREFS_CPAUSE, 9);
	}

	public void setCPauseButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CPAUSE, keyCode);
	    getPrefs().flush();
	}
	
	//exit button
	public int getExitButton() {
	    return getPrefs().getInteger(PREFS_EXIT, 131);
	}

	public void setExitButton(int keycode) {
	    getPrefs().putInteger(PREFS_EXIT, keycode);
	    getPrefs().flush();
	}
	
	public int getCExitButton() {
	    return getPrefs().getInteger(PREFS_CEXIT, 8);
	}

	public void setCExitButton(int keyCode) {
	    getPrefs().putInteger(PREFS_CEXIT, keyCode);
	    getPrefs().flush();
	}
	
	//set default controls both keyboard and controller
	public void setDefault() {
	    getPrefs().putInteger(PREFS_LEFT, 29);
	    getPrefs().putInteger(PREFS_CLEFT, 15);
	    getPrefs().putInteger(PREFS_RIGHT, 32);
	    getPrefs().putInteger(PREFS_CRIGHT, 13);
	    getPrefs().putInteger(PREFS_JUMP, 51);
	    getPrefs().putInteger(PREFS_CJUMP, 2);
	    getPrefs().putInteger(PREFS_RUN, 59);
	    getPrefs().putInteger(PREFS_CRUN, 6);
	    getPrefs().putInteger(PREFS_SWORD, 39);
	    getPrefs().putInteger(PREFS_CSWORD, 3);
	    getPrefs().putInteger(PREFS_SHIELD, 38);
	    getPrefs().putInteger(PREFS_CSHIELD, 1);
	    getPrefs().putInteger(PREFS_PAUSE, 44);
	    getPrefs().putInteger(PREFS_CPAUSE, 9);
	    getPrefs().putInteger(PREFS_EXIT, 131);
	    getPrefs().putInteger(PREFS_CEXIT, 8);
	    
	    getPrefs().flush();
	}
}
