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
	
	public static final String[] level = {"levels/level0.tmx", "levels/level1.tmx", "levels/level2.tmx", "levels/level3.tmx", "levels/level4.tmx"};
	public static final String[] levelName = {"archie lost", "the forest", "into\nthe deep", "near\nthe end", "the end"};
	public static final String[] facePath = {"pic/start/archieFace0.png", "pic/start/archieFace1.png", "pic/start/archieFace2.png", "pic/start/archieFace3.png", "pic/start/archieFace4.png"};
	public static final String[] swordPath = {"pic/start/swordSkin0.png", "pic/start/swordSkin1.png", "pic/start/swordSkin2.png", "pic/start/swordSkin3.png", "pic/start/swordSkin4.png"};
	public static final String[] shieldPath = {"pic/start/shieldSkin0.png", "pic/start/shieldSkin1.png", "pic/start/shieldSkin2.png", "pic/start/shieldSkin3.png", "pic/start/shieldSkin4.png"};
	
	public static final String playerSkinStayTexturePath = "sprites/player/player_stay.png";
	public static final String playerSkinMoveTexturePath = "sprites/player/player_move.png";
	public static final String playerSwordTexturePath = "sprites/player/playerSword.png";
	public static final String playerShieldTexturePath = "sprites/player/playerShield.png";
	public static final String[] hudIconsPath = {"sprites/hud/lifeBar.png", "sprites/hud/coinBar.png"};
	public static final String[] itemIconsPath = {"sprites/item/lifeItem.png", "sprites/item/coinItem.png", "sprites/item/trapItem.png"};
	public static final String[] enemySkinMoveTexturePath = {"sprites/enemy/enemy0.png", "sprites/enemy/enemy123.png", "sprites/enemy/enemy4.png", "sprites/enemy/enemySword0.png", "sprites/enemy/enemySword4.png"};
	
	public static final String[] musicPath = {"sound/music/erokia__elementary-wave-11.mp3", "sound/music/foolboymedia_sky-loop.mp3"};
	public static final String[] soundPath = {"sound/button/broumbroum__sf3-sfx-menu-validate.wav", "sound/button/broumbroum__sf3-sfx-menu-back.wav" , "sound/button/broumbroum__sf3-sfx-menu-select.wav"};
	public static final String[] playSoundPath = {"sound/game/flick3r_hardkick-6.mp3", "sound/game/attack/smokebomb99_sword-slash-1_playersword.mp3"};
	public static final String[] playItemSoundPath = {"sound/game/item/lloydevans09_jump2_coin.mp3", "sound/game/item/cman634_jump-sound-or-power-up-sound_medickit.mp3"};
	public static final String[] playPlayerSoundPath = {"sound/game/player/coby12388_enerjump_jump.mp3", "sound/game/player/kuzyaburst_painful-sounding-punches_punchbyEnemy.mp3"};
	public static final String[] playEnemySoundPath = {"sound/game/enemy/kuzyaburst_painful-sounding-punches_punchbyPlayer.mp3", "sound/game/enemy/michel88_paind_die.mp3"};
	
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
