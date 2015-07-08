package com.holiday.archie.handlers;

import com.badlogic.gdx.InputProcessor;
import com.holiday.archie.states.Play;
import com.holiday.archie.states.Play.State;

public class ArchieInputProcessor implements InputProcessor{
	
	private int leftCode;
	private int rightCode;
	private int jumpCode;
	private int runCode;
	private int swordCode;
	private int shieldCode;
	private int pauseCode;
	private int exitCode;
	
	Play play;
	
	public ArchieInputProcessor(Play player, int[] keyboard){
		play = player;
		
		leftCode = keyboard[0];
		rightCode = keyboard[1];
		jumpCode = keyboard[2];
		runCode = keyboard[3];
		swordCode = keyboard[4];
		shieldCode = keyboard[5];
		pauseCode = keyboard[6];
		exitCode = keyboard[7];
	}
	@Override
	public boolean keyDown(int keycode) {
		if(leftCode == keycode){
			play.leftMove = true;
		}
		if(rightCode == keycode){
			play.rightMove = true;
		}
		if(jumpCode == keycode){
			play.jump = true;
		}
		if(runCode == keycode){
			play.runMove = true;
		}
		if(swordCode == keycode){
			play.makeSword(true);
		}
		if(shieldCode == keycode){
			play.makeShield(true);
		}
		if(pauseCode == keycode){
        	if(play.state == State.Running){
        		play.state = State.Paused;
        	} else if(play.state == State.Paused){
        		play.state = State.Running;
        	}
		}
		if(exitCode == keycode){
			play.state = State.Quit;
		}
        return true;
	}

	@Override
	public boolean keyTyped(char keyName) {
		if(play.cheatModeOn){
			play.charsec = keyName;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(leftCode == keycode){
			play.leftMove = false;
		}
		if(rightCode == keycode){
			play.rightMove = false;
		}
		if(jumpCode == keycode){
			play.jump = false;
		}
		if(runCode == keycode){
			play.runMove = false;
		}
		if(swordCode == keycode){
			play.makeSword(false);
		}
		if(shieldCode == keycode){
			play.makeShield(false);
		}
        return true;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
