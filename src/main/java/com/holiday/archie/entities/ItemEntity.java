package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class ItemEntity {
	private Body body;
	private int identity;
	private boolean isAlive;
	private Texture texture;
	
	public ItemEntity(Body body, int identity, boolean isAlive, int texturePath){
		this.body = body;
		this.identity = identity;
		this.isAlive = isAlive;
		texture = new Texture(Gdx.files.internal(ArchieConstantVariables.itemIconsPath[texturePath]));
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
		tmr.getBatch().draw(texture, (body.getPosition().x * ArchieConstantVariables.PPM)-32/2, (int) (body.getPosition().y * ArchieConstantVariables.PPM)-32/2, 32, 32);
		tmr.getBatch().end();
	}
}
