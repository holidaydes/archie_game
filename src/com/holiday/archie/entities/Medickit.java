package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class Medickit {
	private Body body;
	private int identity;
	private boolean isAlive;
	private Texture medickitTexture;

	public Medickit(Body body, int identity){
		this.body = body;
		this.identity = identity;
		isAlive = true;
		medickitTexture = new Texture(Gdx.files.internal(ArchieConstantVariables.itemIconsPath[0]));
	}
	
	public Body getBody(){
		return body;
	}
	
	public int getIdentity(){
		return identity;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void setAlive(boolean isAlive){
		this.isAlive = isAlive;
	}
	
	public void render(OrthogonalTiledMapRenderer tmr){
		tmr.getBatch().begin();
		tmr.getBatch().draw(medickitTexture, (body.getPosition().x * ArchieConstantVariables.PPM)-32/2, (int) (body.getPosition().y * ArchieConstantVariables.PPM)-32/2, 32, 32);
		tmr.getBatch().end();
	}
}
