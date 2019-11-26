package com.holiday.archie.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ArchieContactListener implements ContactListener{

	private int groundContact;
	private int coins;
	private int playerHealth;
	private int playerDefense;
	
	private int coinIdentity;
	private int medickitIdentity;
	
	private int enemyIdentity;
	private int sensorIdentity;
	private int stopBitIdentity;
	
	private boolean[] enemyGroundContact;
	private int enemyGroundIdentity;
	
	private int enemyObstaclesIdentity;
	private boolean obstacles;
	private boolean sensorOn;
	private boolean stopBit;
	
	public ArchieContactListener(int equipedShield, int enemyIdentitySize){
		super();
		coins = 0;
		playerHealth = 0;
		playerDefense = ArchieConstantVariables.playerDefense[equipedShield];
		
		enemyIdentity = 0;
		sensorIdentity = 0;
		stopBitIdentity = 0;
		enemyGroundIdentity = 0;
		enemyObstaclesIdentity = 0;
		
		enemyGroundContact = new boolean[enemyIdentitySize];
		
		coinIdentity = 0;
		medickitIdentity = 0;
		
		obstacles = false;
		sensorOn = false;
		stopBit = false;
	}
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		//damage by player
		if(fa.getUserData() != null && fa.getUserData().equals("sword")){
			if(fb.getUserData().toString().substring(0, 5).equals("enemy")){
				enemyIdentity = Integer.parseInt(fb.getUserData().toString().substring(7));
			}
		}
		//ground contact with enemy
		if(fa.getUserData() != null && fa.getUserData().equals("foot")){
			if(fb.getUserData().toString().substring(0, 5).equals("enemy")){
				groundContact++;
			}
		}
		//ground contact by player
		if(fa.getUserData().equals("ground") && fb.getUserData() != null && fb.getUserData().equals("foot")) {
			groundContact++;
		}
		//player contact with trap
		if(fb.getUserData() != null && fb.getUserData().equals("trap")) {
			groundContact++;
			playerHealth = - 25;
		}
		//coin contact by player
		if(fa.getUserData() != null && fa.getUserData().equals("player") && !fb.getUserData().equals("trap") && !fb.getUserData().equals("foot")){		
			if(fb.getUserData() != null && fb.getUserData().toString().substring(0, 4).equals("coin")) {
				coinIdentity = Integer.parseInt(fb.getUserData().toString().substring(4));
				coins++;
			}
		}
		//medickit contact by player
		if(fa.getUserData() != null && fa.getUserData().equals("player") && fb.getUserData().toString().length() > 8){
			if(fb.getUserData() != null && fb.getUserData().toString().substring(0, 8).equals("medickit")) {
				medickitIdentity = Integer.parseInt(fb.getUserData().toString().substring(8));
				playerHealth = 25;
			}
		}
		//contact by enemy
		if(fa.getUserData() != null && fa.getUserData().equals("player") || fa.getUserData().equals("shield")){	
			if(fb.getUserData().toString().length() > 4){
				if(fb.getUserData().toString().substring(0, 5).equals("enemy")){
					//get enemyType
					int type = Integer.parseInt(fb.getUserData().toString().substring(5, 6));
					int damage = 0;
					if(fa.getUserData().equals("shield")){
						if(type == 0){
							damage = playerDefense + -20;
						}
						if(type == 1 || type == 2 || type == 3){
							damage = playerDefense + - 5;
						}
						if(type == 4){
							damage = playerDefense + - 10;
						}
						if(damage < 0){
							playerHealth = damage;
						}
					} else {
						if(type == 0){
							playerHealth = - 20;
						}
						if(type == 1 || type == 2 || type == 3){
							playerHealth = - 5;
						}
						if(type == 4){
							playerHealth = - 10;
						}
					}
				}
			}
		}
		//player contact with enemy sensor
		if(fa.getUserData() != null && fa.getUserData().equals("player") && !fb.getUserData().toString().substring(0, 4).equals("coin") && !fb.getUserData().equals("trap")){
			if(fb.getUserData().toString().substring(0, 6).equals("sensor")){
				sensorIdentity = Integer.parseInt(fb.getUserData().toString().substring(6));
				sensorOn = true;
			}
		}
		//player contact with enemy, return stopBit true
		if(fa.getUserData() != null && fa.getUserData().equals("player")){
			if(fb.getUserData().toString().length() > 5){
				if(fb.getUserData().toString().substring(0, 5).equals("enemy")){
					if(Integer.parseInt(fb.getUserData().toString().substring(5, 6)) != 0){
						stopBitIdentity = Integer.parseInt(fb.getUserData().toString().substring(7));
						stopBit = true;
					}
				}
			}
		}
		
		//enemyfoot contact to ground
		if(fb.getUserData() != null && fb.getUserData().toString().length() > 9 && fa.getUserData().equals("ground")) {
			if(fb.getUserData().toString().substring(0,9).equals("footenemy")){
				enemyGroundIdentity = Integer.parseInt(fb.getUserData().toString().substring(9));
				enemyGroundContact[enemyGroundIdentity] = true;
			}
		}
		//enemy contact with ground
		if(fa.getUserData().equals("ground") && fb.getUserData().toString().length() > 5){
			if(fb.getUserData().toString().substring(0, 5).equals("enemy")){
				enemyObstaclesIdentity = Integer.parseInt(fb.getUserData().toString().substring(7));
				obstacles = true;		
			}
		}
		
		//damage by enemy
		if(fa.getUserData() != null && fa.getUserData().equals("player") || fa.getUserData().equals("shield")){
			if(fb.getUserData().toString().length() > 12){
				if(fb.getUserData().toString().substring(0, 12).equals("swordByEnemy")){
					if(fa.getUserData().equals("player")){
						playerHealth = - ArchieConstantVariables.enemyDamage[Integer.parseInt(fb.getUserData().toString().substring(12))];
					}
					if(fa.getUserData().equals("shield")){
						int damage = 0;
						damage = playerDefense + - ArchieConstantVariables.enemyDamage[Integer.parseInt(fb.getUserData().toString().substring(12))];
						if(damage < 0){
							playerHealth = damage;
						}
					}
				}
			}
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		//end contact with ground
		if(fa.getUserData().equals("ground") && fb.getUserData() != null && fb.getUserData().equals("foot")) {
			groundContact--;
		}
		//end contact with enemy, not allow player move
		if(fa.getUserData() != null && fa.getUserData().equals("foot")){
			if(fb.getUserData().toString().substring(0, 5).equals("enemy")){
				groundContact--;
			}
		}
		//end contact with enemy sensor
		if(fa.getUserData() != null && fa.getUserData().equals("player") && !fb.getUserData().toString().substring(0, 4).equals("coin") && !fb.getUserData().equals("trap")){
			if(fb.getUserData().toString().substring(0, 6).equals("sensor")){
				sensorIdentity = Integer.parseInt(fb.getUserData().toString().substring(6));
				sensorOn = false;
			}
		}
		//end contact with enemy, stopBit set false
		if(fa.getUserData() != null && fa.getUserData().equals("player")){
			if(fb.getUserData().toString().length() > 5){
				if(fb.getUserData().toString().substring(0, 5).equals("enemy")){
					if(Integer.parseInt(fb.getUserData().toString().substring(5, 6)) != 0){
						stopBitIdentity = Integer.parseInt(fb.getUserData().toString().substring(7));
						stopBit = false;
					}
				}
			}
		}
		//end contact with trap
		if(fb.getUserData() != null && fb.getUserData().equals("trap")) {
			groundContact--;
		}
		//end of enemy contact to ground
		if(fb.getUserData() != null && fb.getUserData().toString().length() > 9 && fa.getUserData().equals("ground")) {
			if(fb.getUserData().toString().substring(0,9).equals("footenemy")){	
				enemyGroundIdentity = Integer.parseInt(fb.getUserData().toString().substring(9));
				enemyGroundContact[enemyGroundIdentity] = false;
			}
		}
	}

	public boolean playerAllowToMove() {
		return groundContact > 0;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public int getCoinIdentity(){
		int currentCoinIdentity = coinIdentity;
		coinIdentity = 0;
		return currentCoinIdentity;
	}
	
	public int getMedickitIdentity(){
		int currentMedickitIdentity = medickitIdentity;
		medickitIdentity = 0;
		return currentMedickitIdentity;
	}
	
	public int getHealth() {
		int currentHealth = playerHealth;
		playerHealth = 0;
		return currentHealth;
	}
	
	public int getDamagedByPlayer() {
		int currentIdentity = enemyIdentity;
		enemyIdentity = 0;
		return currentIdentity;
	}
	
	public int getSensorIdentity() {
		int currentSensorIdentity = sensorIdentity;
		sensorIdentity = 0;
		return currentSensorIdentity;
	}
	
	public boolean getSensorData() {
		boolean currentSensorData = sensorOn;
		sensorOn = false;
		return currentSensorData;
	}
	
	public int getStopBitIdentity() {
		int currentStopBitIdentity = stopBitIdentity;
		stopBitIdentity = 0;
		return currentStopBitIdentity;
	}
	
	public boolean getStopBit() {
		boolean currentStopBit = stopBit;
		stopBit = false;
		return currentStopBit;
	}
	
	public boolean enemyAllowToMove(int identity) {
		return enemyGroundContact[identity];
	}
	
	public boolean enemyBlocked() {
		boolean currentEnemyBlock = obstacles;
		obstacles = false;
		return currentEnemyBlock;
	}
	
	public int getEnemyBlockedIdentity() {
		int currentEnemyBlockedIdentity = enemyObstaclesIdentity;
		enemyObstaclesIdentity = 0;
		return currentEnemyBlockedIdentity;
	}
	
	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
	}
	
}
