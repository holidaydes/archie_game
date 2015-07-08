package com.holiday.archie.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.holiday.archie.core.Archie;

public class ArchieBackgroundAnimation {
	
	private TextureRegion background;
	private OrthographicCamera gameCamera;
	private float scale;
	
	private float x;
	private float y;
	private int drawX;
	private int drawY;
	
	public ArchieBackgroundAnimation(TextureRegion background, OrthographicCamera gameCamera, float scale) {
		this.background = background;
		this.gameCamera = gameCamera;
		this.scale = scale;
		drawX = Archie.WIDTH / background.getRegionWidth() + 1;
		drawY = Archie.HEIGHT / background.getRegionHeight() + 1;
	}

	public void render(SpriteBatch batch) {
		
		float x = ((this.x + gameCamera.viewportWidth / 2 - gameCamera.position.x) * scale) % background.getRegionWidth();
		float y = ((this.y + gameCamera.viewportHeight / 2 - gameCamera.position.y) * scale) % background.getRegionHeight();
		
		batch.begin();
		
		int columnOffset;
		int rowOffset;
		
		if(x > 0){
			columnOffset = -1;
		} else {
			columnOffset = 0;
		}
		
		if(y > 0){
			rowOffset = -1;
		} else {
			rowOffset = 0;
		}
		
		for(int row = 0; row < drawY; row++) {
			for(int column = 0; column < drawX; column++) {
				batch.draw(background, x + (column + columnOffset) * background.getRegionWidth(), y + (rowOffset + row) * background.getRegionHeight());
			}
		}
		
		batch.end();
		
	}
	
}
