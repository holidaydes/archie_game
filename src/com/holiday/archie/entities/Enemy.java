package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class Enemy {
	private Body body;
	private float starterX;
	private float starterY;
	private int health;
	private int enemyType;
	private int identity;
	private boolean alive;
	private boolean enemyArrow;
	private boolean sword;
	private boolean sensorOn;
	private boolean stopBit;
	private boolean allowMove;
	private boolean blocked;
	private float speedX;
	
	private TextureRegion[][] regionsMove;
	private Texture buffer;
	private Animation moveAnimation;
	private int dimension = 0;
	
	private TextureRegion currentFrame;

	private float stateTime;

	public Enemy(Body body, int enemyType, int identity, float x, float y){
		this.body = body;
		this.enemyType = enemyType;
		this.identity = identity;
		starterX = x;
		starterY = y;
		alive = true;
		enemyArrow = false;
		health = ArchieConstantVariables.enemyHealth[enemyType];
		sword = false;
		sensorOn = false;
		stopBit = false;
		allowMove = true;
		blocked = false;
		
		getSkin();
        
        stateTime = 0f; 
	}
	
	public int getType(){
		return enemyType;
	}
	
	public int getIdentity(){
		return identity;
	}
	
	public boolean isSword(){
		return sword;
	}
	
	public void setSword(boolean alive){
		sword = alive;
	}
	
	public Body getBody(){
		return body;
	}
	
	public int getHealth(){
		return health;
	}

	public boolean getArrow(){
		return enemyArrow;
	}
	
	public int getBodyDimension(){
		return dimension;
	}
	
	public float getStarterX(){
		return starterX;
	}
	
	public float getStarterY(){
		return starterY;
	}
	
	public void setArrow(boolean arrow){
		enemyArrow = arrow;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void setHealth(int health){
		this.health += health;
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public boolean isSensorOn(){
		return sensorOn;
	}
	
	public void setSensorOff(boolean sensorOff){
		sensorOn = sensorOff;
	}
	
	public boolean getStopBit(){
		return stopBit;
	}
	
	public void setStopBit(boolean stopBit){
		this.stopBit = stopBit;
	}
	
	public float getSpeedX(){
		return speedX;
	}

	public boolean isAllowMove(){
		return allowMove;
	}
	
	public void setAllowMove(boolean allowMove){
		this.allowMove = allowMove;
	}
	
	public boolean isBlocked(){
		return blocked;
	}
	
	public void setBlocked(boolean blocked){
		this.blocked = blocked;
	}
	
	public void render(OrthogonalTiledMapRenderer tmr){
		stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = moveAnimation.getKeyFrame(stateTime, true);
        
		tmr.getBatch().begin();
		tmr.getBatch().draw(currentFrame, (body.getPosition().x * ArchieConstantVariables.PPM) - dimension / 2, (body.getPosition().y * ArchieConstantVariables.PPM) - dimension / 2, dimension, dimension);
		tmr.getBatch().end();
	}
	
	private void getSkin(){

		int firstIndex = 0;
		
		if(enemyType == 0){
			buffer = new Texture(Gdx.files.internal(ArchieConstantVariables.enemySkinMoveTexturePath[0]));
			dimension = 32;
			speedX = 3;
			firstIndex = 0;
		}
		if(enemyType == 1 || enemyType == 2 || enemyType == 3){
			buffer = new Texture(Gdx.files.internal(ArchieConstantVariables.enemySkinMoveTexturePath[1]));
			dimension = 64;
			speedX = 1;
			firstIndex = enemyType - 1;
		}
		if(enemyType == 4){
			buffer = new Texture(Gdx.files.internal(ArchieConstantVariables.enemySkinMoveTexturePath[2]));
			dimension = 128;
			speedX = 1.25f;
			firstIndex = 0;
		}
		regionsMove = TextureRegion.split(buffer, dimension, dimension);

		moveAnimation = new Animation(0.2f, regionsMove[firstIndex][0], regionsMove[firstIndex][1], regionsMove[firstIndex][2]);
	}
}
