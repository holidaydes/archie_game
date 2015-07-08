package com.holiday.archie.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class ArchieBoundedCamera extends OrthographicCamera {

    private float xmin;
    private float xmax;
    private float ymin;
    private float ymax;

    public ArchieBoundedCamera() {
    	setBounds(0, 0, 0, 0);
    }

    public void setBounds(float xmin, float xmax, float ymin, float ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public void setPosition(float x, float y) {
    	position.set(x, y, 0);
        fixBounds();
    }

    private void fixBounds() {
        if(position.x < xmin + viewportWidth / 2) {
            position.x = xmin + viewportWidth / 2;
        }
        if(position.x > xmax - viewportWidth / 2) {
            position.x = xmax - viewportWidth / 2;
        }
        if(position.y < ymin + viewportHeight / 2) {
            position.y = ymin + viewportHeight / 2;
        }
        if(position.y > ymax - viewportHeight / 2) {
            position.y = ymax - viewportHeight / 2;
        }
    }

}
