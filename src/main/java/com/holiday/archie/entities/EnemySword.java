package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.elements.EnemySwordBody;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class EnemySword {
	private final Body body;
	private final int identity;
	private final boolean arrow;
	private final int width;
	private final int height;
	private final Texture swordTexture;

	public EnemySword(Body body, int identity, EnemySwordBody skin, boolean arrow){
		this.body = body;
		this.identity = identity;
		this.arrow = arrow;
		swordTexture = new Texture(Gdx.files.internal(skin.texturePath));
		width = skin.width;
		height = skin.height;
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
			tmr.getBatch().draw(swordTexture, (body.getPosition().x * ArchieConstantVariables.PPM) + width / 2f - 10f, (int) (body.getPosition().y * ArchieConstantVariables.PPM) - height / 2f, 20, height, 0, 0, 20, height, true, false);
		} else {
			tmr.getBatch().draw(swordTexture, (body.getPosition().x * ArchieConstantVariables.PPM) - width / 2f - 10f, (int) (body.getPosition().y * ArchieConstantVariables.PPM) - height / 2f, 20, height);
		}
		tmr.getBatch().end();
	}
}
