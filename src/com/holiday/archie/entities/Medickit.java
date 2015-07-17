package com.holiday.archie.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Medickit extends ItemEntity{

	public Medickit(Body body, int identity){
		super(body, identity, true, 0);
	}

}
