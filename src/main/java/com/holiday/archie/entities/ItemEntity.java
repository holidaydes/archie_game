package com.holiday.archie.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.holiday.archie.elements.ItemType;
import com.holiday.archie.handlers.ArchieConstantVariables;

public class ItemEntity {
    private final Body body;
    private final int identity;
    private boolean isAlive;
    private final Texture texture;

    public ItemEntity(Body body, int identity, ItemType itemType) {
        this.body = body;
        this.identity = identity;
        this.isAlive = true;
        texture = new Texture(Gdx.files.internal(itemType.texturePath));
    }

    public ItemEntity(Body body, ItemType itemType) {
        this(body, 0, itemType);
    }

    public Body getBody() {
        return body;
    }

    public int getIdentity() {
        return identity;
    }

    public boolean isDead() {
        return !isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void render(OrthogonalTiledMapRenderer tmr) {
        tmr.getBatch().begin();
        tmr.getBatch().draw(texture, (body.getPosition().x * ArchieConstantVariables.PPM) - 32f / 2f, (int) (body.getPosition().y * ArchieConstantVariables.PPM) - 32f / 2f, 32, 32);
        tmr.getBatch().end();
    }
}
