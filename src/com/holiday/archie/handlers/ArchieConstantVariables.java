package com.holiday.archie.handlers;

public class ArchieConstantVariables {
	public static final float PPM = 100f;
	
	public static final short BIT_PLAYER = 1;
	public static final short BIT_GROUND = 2;
	public static final short BIT_ENEMY = 4;
	public static final short BIT_COIN = 8;
	public static final short BIT_TRAP = 16;
	public static final short BIT_MEDICKIT = 32;
	public static final short BIT_SWORD = 64;
	public static final short BIT_SHIELD = 128;
	public static final short BIT_ENEMYSWORD = 256;
	
	public static final String[] level = {"res/levels/level0.tmx", "res/levels/level1.tmx", "res/levels/level2.tmx", "res/levels/level3.tmx", "res/levels/level4.tmx"};
	public static final String[] levelName = {"archie lost", "the forest", "into\nthe deep", "near\nthe end", "the end"};
	public static final String[] facePath = {"res/pic/start/archieFace0.png", "res/pic/start/archieFace1.png", "res/pic/start/archieFace2.png", "res/pic/start/archieFace3.png", "res/pic/start/archieFace4.png"};
	public static final String[] swordPath = {"res/pic/start/swordSkin0.png", "res/pic/start/swordSkin1.png", "res/pic/start/swordSkin2.png", "res/pic/start/swordSkin3.png", "res/pic/start/swordSkin4.png"};
	public static final String[] shieldPath = {"res/pic/start/shieldSkin0.png", "res/pic/start/shieldSkin1.png", "res/pic/start/shieldSkin2.png", "res/pic/start/shieldSkin3.png", "res/pic/start/shieldSkin4.png"};
	
	public static final String playerSkinStayTexturePath = "res/sprites/player/player_stay.png";
	public static final String playerSkinMoveTexturePath = "res/sprites/player/player_move.png";
	public static final String playerSwordTexturePath = "res/sprites/player/playerSword.png";
	public static final String playerShieldTexturePath = "res/sprites/player/playerShield.png";
	public static final String[] hudIconsPath = {"res/sprites/hud/lifeBar.png", "res/sprites/hud/coinBar.png"};
	public static final String[] itemIconsPath = {"res/sprites/item/lifeItem.png", "res/sprites/item/coinItem.png", "res/sprites/item/trapItem.png"};
	public static final String[] enemySkinMoveTexturePath = {"res/sprites/enemy/enemy0.png", "res/sprites/enemy/enemy123.png", "res/sprites/enemy/enemy4.png", "res/sprites/enemy/enemySword0.png", "res/sprites/enemy/enemySword4.png"};
	
	public static final String[] musicPath = {"res/sound/music/erokia__elementary-wave-11.mp3", "res/sound/music/foolboymedia_sky-loop.mp3"};
	public static final String[] soundPath = {"res/sound/button/broumbroum__sf3-sfx-menu-validate.wav", "res/sound/button/broumbroum__sf3-sfx-menu-back.wav" , "res/sound/button/broumbroum__sf3-sfx-menu-select.wav"};
	public static final String[] playSoundPath = {"res/sound/game/flick3r_hardkick-6.mp3", "res/sound/game/attack/smokebomb99_sword-slash-1_playersword.mp3"};
	public static final String[] playItemSoundPath = {"res/sound/game/item/lloydevans09_jump2_coin.mp3", "res/sound/game/item/cman634_jump-sound-or-power-up-sound_medickit.mp3"};
	public static final String[] playPlayerSoundPath = {"res/sound/game/player/coby12388_enerjump_jump.mp3", "res/sound/game/player/kuzyaburst_painful-sounding-punches_punchbyEnemy.mp3"};
	public static final String[] playEnemySoundPath = {"res/sound/game/enemy/kuzyaburst_painful-sounding-punches_punchbyPlayer.mp3", "res/sound/game/enemy/michel88_paind_die.mp3"};
	
	public static final int[] playerHealth = {100, 125, 150, 175, 200};
	public static final int[] playerDamage = {25, 50, 75, 100, 125};
	public static final float[] playerStamina = {1.5f, 2, 3, 4, 5};
	public static final int[] playerDefense = {10, 20, 30, 40, 50};
	public static final int[] playerBodyMass = {10, 15, 20, 5, 1};
	public static final int[] playerSwordMass = {5, 7, 10, 3, 1};
	public static final int[] playerShieldMass = {5, 7, 10, 3, 1};
	public static final String[] equipmentName = {"wood", "iron", "gold", "diamond", "sketch"};
	public static final int[] skinPrice = {0, 25, 50, 100, 200};
	public static final int[] swordPrice = {0, 25, 50, 100, 200};
	public static final int[] shieldPrice = {0, 25, 50, 100, 200};
	
	public static final int[] enemyHealth = {25, 75, 150, 200, 500};
	public static final int[] enemyDamage = {0, 15, 25, 35, 50};
	public static final int[] enemyBodyMass = {1, 2, 3, 4, 5};
	
	public static final String[] tellATale = {
		"archie, the robot\none day reach\na huge forest...",
		"archie lost, he can't\nfind the way out\nfrom the forest...",
		"archie go\ndeeper into\nthe dark forest...",
		"archie finally see\nthe end of\nthe forest...",
		"this is the end?\nhe hopes so..."
		};
}
