package com.holiday.archie.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class ArchieBoundedCamera extends OrthographicCamera {

    private float xMin;
    private float xMax;
    private float yMin;
    private float yMax;

    public ArchieBoundedCamera() {
        setBounds(0, 0, 0, 0);
    }

    public void setBounds(float xMin, float xMax, float yMin, float yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void setPosition(float x, float y) {
        position.set(x, y, 0);
        fixBounds();
    }

    private void fixBounds() {
        if (position.x < xMin + viewportWidth / 2) {
            position.x = xMin + viewportWidth / 2;
        }
        if (position.x > xMax - viewportWidth / 2) {
            position.x = xMax - viewportWidth / 2;
        }
        if (position.y < yMin + viewportHeight / 2) {
            position.y = yMin + viewportHeight / 2;
        }
        if (position.y > yMax - viewportHeight / 2) {
            position.y = yMax - viewportHeight / 2;
        }
    }

}
