package com.holiday.archie.handlers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.holiday.archie.states.Play;
import com.holiday.archie.states.Play.State;

public class ArchieControllerProcessor {
	
	private int cleftCode;
	private int crightCode;
	private int cjumpCode;
	private int crunCode;
	private int cswordCode;
	private int cshieldCode;
	private int cpauseCode;
	private int cexitCode;
	
	Play play;
	
	public ArchieControllerProcessor(Play player, int[] controller){
		play = player;
		
		cleftCode = controller[0];
		crightCode = controller[1];
		cjumpCode = controller[2];
		crunCode = controller[3];
		cswordCode = controller[4];
		cshieldCode = controller[5];
		cpauseCode = controller[6];
		cexitCode = controller[7];
		
		Controllers.addListener(new ControllerAdapter() {
			public boolean buttonDown (Controller controller, int keycode) {
				if(cleftCode == keycode){
					play.leftMove = true;
				}
				if(crightCode == keycode){
					play.rightMove = true;
				}
				if(cjumpCode == keycode){
					play.jump = true;
				}
				if(crunCode == keycode){
					play.runMove = true;
				}
				if(cswordCode == keycode){
					play.makeSword(true);
				}
				if(cshieldCode == keycode){
					play.makeShield(true);
				}
				if(cpauseCode == keycode){
		        	if(play.state == State.Running){
		        		play.state = State.Paused;
		        	} else if(play.state == State.Paused){
		        		play.state = State.Running;
		        	}
				}
				if(cexitCode == keycode){
					play.state = State.Quit;
				}
				return false;
			}
			
			public boolean buttonUp (Controller controller, int keycode) {
				if(cleftCode == keycode){
					play.leftMove = false;
				}
				if(crightCode == keycode){
					play.rightMove = false;
				}
				if(cjumpCode == keycode){
					play.jump = false;
				}
				if(crunCode == keycode){
					play.runMove = false;
				}
				if(cswordCode == keycode){
					play.makeSword(false);
				}
				if(cshieldCode == keycode){
					play.makeShield(false);
				}
				return false;
			}
		});
	}
}
