package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.elements.EnemyBody;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class Enemy {
    private final Body body;
    private final float starterX;
    private final float starterY;
    private int health;
    private final EnemyBody skin;
    private final int identity;
    private boolean alive;
    private boolean enemyArrow;
    private boolean sword;
    private boolean sensorOn;
    private boolean stopBit;
    private boolean allowMove;
    private boolean blocked;
    private float speedX;

    private Animation<TextureRegion> moveAnimation;
    private int dimension = 0;

    private float stateTime;

    public Enemy(Body body, EnemyBody skin, int identity, float x, float y) {
        this.body = body;
        this.skin = skin;
        this.identity = identity;
        starterX = x;
        starterY = y;
        alive = true;
        enemyArrow = false;
        health = skin.health;
        sword = false;
        sensorOn = false;
        stopBit = false;
        allowMove = true;
        blocked = false;

        getSkinTexture();

        stateTime = 0f;
    }

    public EnemyBody getSkin() {
        return skin;
    }

    public int getIdentity() {
        return identity;
    }

    public boolean isSword() {
        return sword;
    }

    public void setSword(boolean alive) {
        sword = alive;
    }

    public Body getBody() {
        return body;
    }

    public int getHealth() {
        return health;
    }

    public boolean getArrow() {
        return enemyArrow;
    }

    public int getBodyDimension() {
        return dimension;
    }

    public float getStarterX() {
        return starterX;
    }

    public void setArrow(boolean arrow) {
        enemyArrow = arrow;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setHealth(int health) {
        this.health += health;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isSensorOn() {
        return sensorOn;
    }

    public void setSensorOff(boolean sensorOff) {
        sensorOn = sensorOff;
    }

    public boolean getStopBit() {
        return stopBit;
    }

    public void setStopBit(boolean stopBit) {
        this.stopBit = stopBit;
    }

    public float getSpeedX() {
        return speedX;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void render(OrthogonalTiledMapRenderer tmr) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = moveAnimation.getKeyFrame(stateTime, true);

        tmr.getBatch().begin();
        tmr.getBatch().draw(currentFrame, (body.getPosition().x * ArchieConstantVariables.PPM) - dimension / 2f, (body.getPosition().y * ArchieConstantVariables.PPM) - dimension / 2f, dimension, dimension);
        tmr.getBatch().end();
    }

    private void getSkinTexture() {
        int firstIndex = 0;
        //the animation sprite is in the same file
        if (skin == EnemyBody.IRON || skin == EnemyBody.GOLD || skin == EnemyBody.DIAMOND) {
            firstIndex = skin.id - 1;
        }
        dimension = skin.dimension;
        speedX = skin.speedX;
        Texture buffer = new Texture(Gdx.files.internal(skin.bodyTexturePath));
        TextureRegion[][] regionsMove = TextureRegion.split(buffer, dimension, dimension);

        moveAnimation = new Animation<>(0.2f, regionsMove[firstIndex][0], regionsMove[firstIndex][1], regionsMove[firstIndex][2]);
    }
}
