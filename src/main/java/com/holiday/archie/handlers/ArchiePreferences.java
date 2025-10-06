package com.holiday.archie.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.holiday.archie.elements.PlayerBody;
import com.holiday.archie.elements.PlayerShield;
import com.holiday.archie.elements.PlayerSword;

public class ArchiePreferences {
    private static final String COIN = "coin";

    private static final String EQUIPPED_BODY = "equippedBody";
    private static final String EQUIPPED_SWORD = "equippedSword";
    private static final String EQUIPPED_SHIELD = "equippedShield";

    private static final String BODY_WOOD = "bodyWood";
    private static final String BODY_IRON = "bodyIron";
    private static final String BODY_GOLD = "bodyGold";
    private static final String BODY_DIAMOND = "bodyDiamond";
    private static final String BODY_SKETCH = "bodySketch";

    private static final String SWORD_WOOD = "swordWood";
    private static final String SWORD_IRON = "swordIron";
    private static final String SWORD_GOLD = "swordGold";
    private static final String SWORD_DIAMOND = "swordDiamond";
    private static final String SWORD_SKETCH = "swordSketch";

    private static final String SHIELD_WOOD = "shieldWood";
    private static final String SHIELD_IRON = "shieldIron";
    private static final String SHIELD_GOLD = "shieldGold";
    private static final String SHIELD_DIAMOND = "shieldDiamond";
    private static final String SHIELD_SKETCH = "shieldSketch";

    private static final String PREFS_LEVEL0 = "level0";
    private static final String PREFS_LEVEL1 = "level1";
    private static final String PREFS_LEVEL2 = "level2";
    private static final String PREFS_LEVEL3 = "level3";
    private static final String PREFS_LEVEL4 = "level4";

    private static final String NAME = "archie_saves";

    private Preferences preferences;

    public ArchiePreferences() {

    }

    protected Preferences getPrefs() {
        if (preferences == null) {
            preferences = Gdx.app.getPreferences(NAME);
            //resetSavedGame(); //LOL
        }
        return preferences;
    }

    public int getCoins() {
        return getPrefs().getInteger(COIN, 0);
    }

    public void setCoin(int amount) {
        getPrefs().putInteger(COIN, getPrefs().getInteger(COIN) + amount);
        getPrefs().flush();
    }

    public int getEquippedBody() {
        return getPrefs().getInteger(EQUIPPED_BODY, PlayerBody.WOOD.id);
    }

    public void setEquippedBody(int id) {
        getPrefs().putInteger(EQUIPPED_BODY, id);
        getPrefs().flush();
    }

    public int getEquippedSword() {
        return getPrefs().getInteger(EQUIPPED_SWORD, PlayerSword.WOOD.id);
    }

    public void setEquippedSword(int id) {
        getPrefs().putInteger(EQUIPPED_SWORD, id);
        getPrefs().flush();
    }

    public int getEquippedShield() {
        return getPrefs().getInteger(EQUIPPED_SHIELD, PlayerShield.WOOD.id);
    }

    public void setEquippedShield(int id) {
        getPrefs().putInteger(EQUIPPED_SHIELD, id);
        getPrefs().flush();
    }

    public boolean getEnabledSkin(int number) {
        boolean enabled = false;
        switch (number) {
            case 0:
                enabled = getPrefs().getBoolean(BODY_WOOD, true);
                break;
            case 1:
                enabled = getPrefs().getBoolean(BODY_IRON, false);
                break;
            case 2:
                enabled = getPrefs().getBoolean(BODY_GOLD, false);
                break;
            case 3:
                enabled = getPrefs().getBoolean(BODY_DIAMOND, false);
                break;
            case 4:
                enabled = getPrefs().getBoolean(BODY_SKETCH, false);
                break;
        }
        return enabled;
    }

    public void setEnabledSkin(int number) {
        switch (number) {
            case 0:
                getPrefs().putBoolean(BODY_WOOD, true);
                break;
            case 1:
                getPrefs().putBoolean(BODY_IRON, true);
                break;
            case 2:
                getPrefs().putBoolean(BODY_GOLD, true);
                break;
            case 3:
                getPrefs().putBoolean(BODY_DIAMOND, true);
                break;
            case 4:
                getPrefs().putBoolean(BODY_SKETCH, true);
                break;
        }
        getPrefs().flush();
    }

    public boolean getEnabledSword(int number) {
        boolean enabled = false;
        switch (number) {
            case 0:
                enabled = getPrefs().getBoolean(SWORD_WOOD, true);
                break;
            case 1:
                enabled = getPrefs().getBoolean(SWORD_IRON, false);
                break;
            case 2:
                enabled = getPrefs().getBoolean(SWORD_GOLD, false);
                break;
            case 3:
                enabled = getPrefs().getBoolean(SWORD_DIAMOND, false);
                break;
            case 4:
                enabled = getPrefs().getBoolean(SWORD_SKETCH, false);
                break;
        }
        return enabled;
    }

    public void setEnabledSword(int number) {
        switch (number) {
            case 0:
                getPrefs().putBoolean(SWORD_WOOD, true);
                break;
            case 1:
                getPrefs().putBoolean(SWORD_IRON, true);
                break;
            case 2:
                getPrefs().putBoolean(SWORD_GOLD, true);
                break;
            case 3:
                getPrefs().putBoolean(SWORD_DIAMOND, true);
                break;
            case 4:
                getPrefs().putBoolean(SWORD_SKETCH, true);
                break;
        }
        getPrefs().flush();
    }

    public boolean getEnabledShield(int number) {
        boolean enabled = false;
        switch (number) {
            case 0:
                enabled = getPrefs().getBoolean(SHIELD_WOOD, true);
                break;
            case 1:
                enabled = getPrefs().getBoolean(SHIELD_IRON, false);
                break;
            case 2:
                enabled = getPrefs().getBoolean(SHIELD_GOLD, false);
                break;
            case 3:
                enabled = getPrefs().getBoolean(SHIELD_DIAMOND, false);
                break;
            case 4:
                enabled = getPrefs().getBoolean(SHIELD_SKETCH, false);
                break;
        }
        return enabled;
    }

    public void setEnabledShield(int number) {
        switch (number) {
            case 0:
                getPrefs().putBoolean(SHIELD_WOOD, true);
                break;
            case 1:
                getPrefs().putBoolean(SHIELD_IRON, true);
                break;
            case 2:
                getPrefs().putBoolean(SHIELD_GOLD, true);
                break;
            case 3:
                getPrefs().putBoolean(SHIELD_DIAMOND, true);
                break;
            case 4:
                getPrefs().putBoolean(SHIELD_SKETCH, true);
                break;
        }
        getPrefs().flush();
    }

    public boolean getLevelEnabled(int number) {
        boolean enabled = false;
        switch (number) {
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

    public void setLevelEnabled(int number) {
        switch (number) {
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
        getPrefs().putInteger(COIN, 0);

        getPrefs().putInteger(EQUIPPED_BODY, PlayerBody.WOOD.id);
        getPrefs().putInteger(EQUIPPED_SWORD, PlayerSword.WOOD.id);
        getPrefs().putInteger(EQUIPPED_SHIELD, PlayerShield.WOOD.id);

        getPrefs().putBoolean(BODY_IRON, false);
        getPrefs().putBoolean(BODY_GOLD, false);
        getPrefs().putBoolean(BODY_DIAMOND, false);
        getPrefs().putBoolean(BODY_SKETCH, false);

        getPrefs().putBoolean(SWORD_IRON, false);
        getPrefs().putBoolean(SWORD_GOLD, false);
        getPrefs().putBoolean(SWORD_DIAMOND, false);
        getPrefs().putBoolean(SWORD_SKETCH, false);

        getPrefs().putBoolean(SHIELD_IRON, false);
        getPrefs().putBoolean(SHIELD_GOLD, false);
        getPrefs().putBoolean(SHIELD_DIAMOND, false);
        getPrefs().putBoolean(SHIELD_SKETCH, false);

        getPrefs().putBoolean(PREFS_LEVEL1, false);
        getPrefs().putBoolean(PREFS_LEVEL2, false);
        getPrefs().putBoolean(PREFS_LEVEL3, false);
        getPrefs().putBoolean(PREFS_LEVEL4, false);

        getPrefs().flush();
    }

}
