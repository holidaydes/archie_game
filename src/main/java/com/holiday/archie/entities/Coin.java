package com.holiday.archie.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Coin extends ItemEntity{

	public Coin(Body body, int identity){
		super(body, identity, true, 1);
	}
}
