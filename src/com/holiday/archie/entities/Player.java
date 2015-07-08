package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class Player {
	private Body body;
	private int playerSkinNumber;
	private boolean alive;
	private boolean arrow;
	private boolean move;
	
	private TextureRegion stayFrame;
	private TextureRegion stayFrameMirrored;
	
	private TextureRegion[][] regionsMove;
	
	private Animation moveAnimation;
	
	private TextureRegion currentFrame;

	private float stateTime;
	
	public Player(Body body, int playerSkinNumber){
		this.body = body;
		this.playerSkinNumber = playerSkinNumber;
		alive = true;
		arrow = true;
		move = false;
		
		stayFrame = new TextureRegion(new Texture(Gdx.files.internal(ArchieConstantVariables.playerSkinStayTexturePath)), getStayStartX(), 0, 32, 64);
		stayFrameMirrored = new TextureRegion(new Texture(Gdx.files.internal(ArchieConstantVariables.playerSkinStayTexturePath)), getStayStartX(), 0, 32, 64);
		stayFrameMirrored.flip(true, false);
		
		regionsMove = TextureRegion.split(new Texture(Gdx.files.internal(ArchieConstantVariables.playerSkinMoveTexturePath)), 32, 64);

		moveAnimation = new Animation(0.2f, regionsMove[playerSkinNumber][0], regionsMove[playerSkinNumber][1], regionsMove[playerSkinNumber][2]);
        
        stateTime = 0f; 
	}
	
	private int getStayStartX(){
		int x = 0;
		switch(playerSkinNumber){
		case 0:
			x = 0;
			break;
		case 1:
			x = 32;
			break;
		case 2:
			x = 64;
			break;
		case 3:
			x = 96;
			break;
		case 4:
			x = 128;
			break;
		}
		return x;
	}
	
	public Body getBody(){
		return body;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public boolean getArrow(){
		return arrow;
	}
	
	public boolean isMove(){
		return move;
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public void setArrow(boolean arrow){
		this.arrow = arrow;
	}
	
	public void setMove(boolean move){
		this.move = move;
	}
	
	public void render(OrthogonalTiledMapRenderer tmr){
		stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = moveAnimation.getKeyFrame(stateTime, true);
        
		tmr.getBatch().begin();
		if(move){
			tmr.getBatch().draw(currentFrame, (body.getPosition().x * ArchieConstantVariables.PPM) - 16f, (body.getPosition().y * ArchieConstantVariables.PPM) - 32f, 32f, 64f);
		} else {		
			if(arrow){
				tmr.getBatch().draw(stayFrame, (body.getPosition().x * ArchieConstantVariables.PPM) - 16f, (body.getPosition().y * ArchieConstantVariables.PPM) - 32f, 32f, 64f);
			} else {
				tmr.getBatch().draw(stayFrameMirrored, (body.getPosition().x * ArchieConstantVariables.PPM) - 16f, (body.getPosition().y * ArchieConstantVariables.PPM) - 32f, 32f, 64f);
			}
		}
		tmr.getBatch().end();
	}
}
