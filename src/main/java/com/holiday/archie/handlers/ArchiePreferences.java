package com.holiday.archie.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ArchiePreferences {
	private static final String PREFS_COIN = "coin";

	private static final String PREFS_EQUIPED_SKIN = "equipedSkin";
	private static final String PREFS_EQUIPED_SWORD = "equipedSword";
	private static final String PREFS_EQUIPED_SHIELD = "equipedShield";
	
	private static final String PREFS_SKIN0 = "skin0";
	private static final String PREFS_SKIN1 = "skin1";
	private static final String PREFS_SKIN2 = "skin2";
	private static final String PREFS_SKIN3 = "skin3";
	private static final String PREFS_SKIN4 = "skin4";
	
	private static final String PREFS_SWORD0 = "sword0";
	private static final String PREFS_SWORD1 = "sword1";
	private static final String PREFS_SWORD2 = "sword2";
	private static final String PREFS_SWORD3 = "sword3";
	private static final String PREFS_SWORD4 = "sword4";
	
	private static final String PREFS_SHIELD0 = "shield0";
	private static final String PREFS_SHIELD1 = "shield1";
	private static final String PREFS_SHIELD2 = "shield2";
	private static final String PREFS_SHIELD3 = "shield3";
	private static final String PREFS_SHIELD4 = "shield4";
	
	private static final String PREFS_LEVEL0 = "level0";
	private static final String PREFS_LEVEL1 = "level1";
	private static final String PREFS_LEVEL2 = "level2";
	private static final String PREFS_LEVEL3 = "level3";
	private static final String PREFS_LEVEL4 = "level4";
	
	private static final String PREFS_NAME = "archie_saves";

	private Preferences preferences;

	public ArchiePreferences() {
		
	}

	protected Preferences getPrefs(){
		if(preferences==null){
			 preferences = Gdx.app.getPreferences(PREFS_NAME);
			 //resetSavedGame(); //LOL
		}
		return preferences;
	}

	public int getCoins() {
	    return getPrefs().getInteger(PREFS_COIN, 0);
	}

	public void setCoin(int amount) {
	    getPrefs().putInteger(PREFS_COIN, getPrefs().getInteger(PREFS_COIN)+amount);
	    getPrefs().flush();
	}

	public int getEquipedSkin() {
	    return getPrefs().getInteger(PREFS_EQUIPED_SKIN, 0);
	}

	public void setEquipedSkin(int number) {
	    getPrefs().putInteger(PREFS_EQUIPED_SKIN, number);
	    getPrefs().flush();
	}
	
	public int getEquipedSword() {
	    return getPrefs().getInteger(PREFS_EQUIPED_SWORD, 0);
	}

	public void setEquipedSword(int number) {
	    getPrefs().putInteger(PREFS_EQUIPED_SWORD, number);
	    getPrefs().flush();
	}
	
	public int getEquipedShield() {
	    return getPrefs().getInteger(PREFS_EQUIPED_SHIELD, 0);
	}

	public void setEquipedShield(int number) {
	    getPrefs().putInteger(PREFS_EQUIPED_SHIELD, number);
	    getPrefs().flush();
	}

	public boolean getEnabledSkin(int number){
		boolean enabled = false;
		switch(number){
		case 0:
			enabled = getPrefs().getBoolean(PREFS_SKIN0, true);
			break;
		case 1:
			enabled = getPrefs().getBoolean(PREFS_SKIN1, false);
			break;
		case 2:
			enabled = getPrefs().getBoolean(PREFS_SKIN2, false);
			break;
		case 3:
			enabled = getPrefs().getBoolean(PREFS_SKIN3, false);
			break;
		case 4:
			enabled = getPrefs().getBoolean(PREFS_SKIN4, false);
			break;
		}
		return enabled;
	}
	
	public void setEnabledSkin(int number){
		switch(number){
		case 0:
			getPrefs().putBoolean(PREFS_SKIN0, true);
			break;
		case 1:
			getPrefs().putBoolean(PREFS_SKIN1, true);
			break;
		case 2:
			getPrefs().putBoolean(PREFS_SKIN2, true);
			break;
		case 3:
			getPrefs().putBoolean(PREFS_SKIN3, true);
			break;
		case 4:
			getPrefs().putBoolean(PREFS_SKIN4, true);
			break;
		}
		getPrefs().flush();
	}
	
	public boolean getEnabledSword(int number){
		boolean enabled = false;
		switch(number){
		case 0:
			enabled = getPrefs().getBoolean(PREFS_SWORD0, true);
			break;
		case 1:
			enabled = getPrefs().getBoolean(PREFS_SWORD1, false);
			break;
		case 2:
			enabled = getPrefs().getBoolean(PREFS_SWORD2, false);
			break;
		case 3:
			enabled = getPrefs().getBoolean(PREFS_SWORD3, false);
			break;
		case 4:
			enabled = getPrefs().getBoolean(PREFS_SWORD4, false);
			break;
		}
		return enabled;
	}
	
	public void setEnabledSword(int number){
		switch(number){
		case 0:
			getPrefs().putBoolean(PREFS_SWORD0, true);
			break;
		case 1:
			getPrefs().putBoolean(PREFS_SWORD1, true);
			break;
		case 2:
			getPrefs().putBoolean(PREFS_SWORD2, true);
			break;
		case 3:
			getPrefs().putBoolean(PREFS_SWORD3, true);
			break;
		case 4:
			getPrefs().putBoolean(PREFS_SWORD4, true);
			break;
		}
		getPrefs().flush();
	}
	public boolean getEnabledShield(int number){
		boolean enabled = false;
		switch(number){
		case 0:
			enabled = getPrefs().getBoolean(PREFS_SHIELD0, true);
			break;
		case 1:
			enabled = getPrefs().getBoolean(PREFS_SHIELD1, false);
			break;
		case 2:
			enabled = getPrefs().getBoolean(PREFS_SHIELD2, false);
			break;
		case 3:
			enabled = getPrefs().getBoolean(PREFS_SHIELD3, false);
			break;
		case 4:
			enabled = getPrefs().getBoolean(PREFS_SHIELD4, false);
			break;
		}
		return enabled;
	}
	
	public void setEnabledShield(int number){
		switch(number){
		case 0:
			getPrefs().putBoolean(PREFS_SHIELD0, true);
			break;
		case 1:
			getPrefs().putBoolean(PREFS_SHIELD1, true);
			break;
		case 2:
			getPrefs().putBoolean(PREFS_SHIELD2, true);
			break;
		case 3:
			getPrefs().putBoolean(PREFS_SHIELD3, true);
			break;
		case 4:
			getPrefs().putBoolean(PREFS_SHIELD4, true);
			break;
		}
		getPrefs().flush();
	}
	public boolean getLevelEnabled(int number) {
		boolean enabled = false;
		switch(number){
		case 0:
			enabled = getPrefs().getBoolean(PREFS_LEVEL0, true);
			break;
		case 1:
			enabled = getPrefs().getBoolean(PREFS_LEVEL1, false);
			break;
		case 2:
			enabled = getPrefs().getBoolean(PREFS_LEVEL2, false);
			break;
		case 3:
			enabled = getPrefs().getBoolean(PREFS_LEVEL3, false);
			break;
		case 4:
			enabled = getPrefs().getBoolean(PREFS_LEVEL4, false);
			break;
		}
		return enabled;
	}

	public void setLevelEnabled(int number){
		switch(number){
		case 0:
			getPrefs().putBoolean(PREFS_LEVEL0, true);
			break;
		case 1:
			getPrefs().putBoolean(PREFS_LEVEL1, true);
			break;
		case 2:
			getPrefs().putBoolean(PREFS_LEVEL2, true);
			break;
		case 3:
			getPrefs().putBoolean(PREFS_LEVEL3, true);
			break;
		case 4:
			getPrefs().putBoolean(PREFS_LEVEL4, true);
			break;
		}
		getPrefs().flush();
	}
	
	//reset saved game
	public void resetSavedGame() {
	    getPrefs().putInteger(PREFS_COIN, 0);
	    
	    getPrefs().putInteger(PREFS_EQUIPED_SKIN, 0);
	    getPrefs().putInteger(PREFS_EQUIPED_SWORD, 0);
	    getPrefs().putInteger(PREFS_EQUIPED_SHIELD, 0);
	    
	    getPrefs().putBoolean(PREFS_SKIN1, false);
	    getPrefs().putBoolean(PREFS_SKIN2, false);
	    getPrefs().putBoolean(PREFS_SKIN3, false);
	    getPrefs().putBoolean(PREFS_SKIN4, false);
	    
	    getPrefs().putBoolean(PREFS_SWORD1, false);
	    getPrefs().putBoolean(PREFS_SWORD2, false);
	    getPrefs().putBoolean(PREFS_SWORD3, false);
	    getPrefs().putBoolean(PREFS_SWORD4, false);
	    
	    getPrefs().putBoolean(PREFS_SHIELD1, false);
	    getPrefs().putBoolean(PREFS_SHIELD2, false);
	    getPrefs().putBoolean(PREFS_SHIELD3, false);
	    getPrefs().putBoolean(PREFS_SHIELD4, false);
	    
	    getPrefs().putBoolean(PREFS_LEVEL1, false);
	    getPrefs().putBoolean(PREFS_LEVEL2, false);
	    getPrefs().putBoolean(PREFS_LEVEL3, false);
	    getPrefs().putBoolean(PREFS_LEVEL4, false);

	    getPrefs().flush();
	}

}
