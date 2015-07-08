package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class Trap {
	private Body body;
	private Texture trapTexture;

	public Trap(Body body){
		this.body = body;
		trapTexture = new Texture(Gdx.files.internal(ArchieConstantVariables.itemIconsPath[2]));
	}
	
	public void render(OrthogonalTiledMapRenderer tmr){
		tmr.getBatch().begin();
		tmr.getBatch().draw(trapTexture, (body.getPosition().x * ArchieConstantVariables.PPM)-32/2, (int) (body.getPosition().y * ArchieConstantVariables.PPM)-32/2, 32, 32);
		tmr.getBatch().end();
	}
}
