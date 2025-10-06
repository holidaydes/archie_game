package com.holiday.archie.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.holiday.archie.core.log.LoggerTag;

public enum AudioManager {
    //menu sounds
    SOUND_MENU_BACK("sound/button/broumbroum__sf3-sfx-menu-back.wav", Type.SOUND),
    SOUND_MENU_SELECT("sound/button/broumbroum__sf3-sfx-menu-select.wav", Type.SOUND),
    SOUND_MENU_VALIDATE("sound/button/broumbroum__sf3-sfx-menu-validate.wav", Type.SOUND),
    //music
    MUSIC_MAIN_MENU("sound/music/erokia__elementary-wave-11.mp3", Type.MUSIC),
    MUSIC_IN_GAME("sound/music/foolboymedia_sky-loop.mp3", Type.MUSIC),
    //common sounds
    SOUND_HARD_KICK("sound/game/flick3r_hardkick-6.mp3", Type.SOUND),
    SOUND_SWORD("sound/game/attack/smokebomb99_sword-slash-1_playersword.mp3", Type.SOUND),
    //item sounds
    ITEM_SOUND_COIN("sound/game/item/lloydevans09_jump2_coin.mp3", Type.SOUND),
    ITEM_SOUND_MEDIC("sound/game/item/cman634_jump-sound-or-power-up-sound_medickit.mp3", Type.SOUND),
    //player sounds
    PLAYER_SOUND_JUMP("sound/game/player/coby12388_enerjump_jump.mp3", Type.SOUND),
    PLAYER_SOUND_PUNCH("sound/game/player/kuzyaburst_painful-sounding-punches_punchbyEnemy.mp3", Type.SOUND),
    //enemy sounds
    ENEMY_SOUND_PUNCH("sound/game/enemy/kuzyaburst_painful-sounding-punches_punchbyPlayer.mp3", Type.SOUND),
    ENEMY_SOUND_DEATH("sound/game/enemy/michel88_paind_die.mp3", Type.SOUND);

    final String path;
    final Type type;

    AudioManager(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    public Sound loadSound() {
        if (this.type == Type.SOUND) {
            Gdx.app.log(LoggerTag.LOAD, this.name());
            return Gdx.audio.newSound(Gdx.files.internal(this.path));
        } else {
            throw new RuntimeException("Selected file is not a sound!");
        }
    }

    public Music loadMusic() {
        if (this.type == Type.MUSIC) {
            Gdx.app.log(LoggerTag.LOAD, this.name());
            return Gdx.audio.newMusic(Gdx.files.internal(this.path));
        } else {
            throw new RuntimeException("Selected file is not a music!");
        }
    }

    enum Type {
        SOUND,
        MUSIC
    }
}
