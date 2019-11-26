package com.holiday.archie.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Trap  extends ItemEntity{

	public Trap(Body body){
		super(body, 0, true, 2);
	}
}
