package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class EnemySword {
	private Body body;
	private int identity;
	private boolean arrow;
	private int width;
	private int height;
	private Texture swordTexture;

	public EnemySword(Body body, int identity, int type, boolean arrow){
		this.body = body;
		this.identity = identity;
		this.arrow = arrow;
		if(type == 4){
			swordTexture = new Texture(Gdx.files.internal(ArchieConstantVariables.enemySkinMoveTexturePath[4]));
			width = 128;
			height = 128;
		} else {
			swordTexture = new Texture(Gdx.files.internal(ArchieConstantVariables.enemySkinMoveTexturePath[3]));
			width = 64;
			height = 10;
		}
	}
	
	public Body getBody(){
		return body;
	}
	
	public int getIdentity(){
		return identity;
	}
	
	public void render(OrthogonalTiledMapRenderer tmr){
		tmr.getBatch().begin();
		if(arrow){
			tmr.getBatch().draw(swordTexture, (body.getPosition().x * ArchieConstantVariables.PPM) + width / 2 - 10, (int) (body.getPosition().y * ArchieConstantVariables.PPM) - height/2, 20, height, 0, 0, 20, height, true, false);
		} else {
			tmr.getBatch().draw(swordTexture, (body.getPosition().x * ArchieConstantVariables.PPM) - width / 2 - 10, (int) (body.getPosition().y * ArchieConstantVariables.PPM) - height/2, 20, height);
		}
		tmr.getBatch().end();
	}
}
