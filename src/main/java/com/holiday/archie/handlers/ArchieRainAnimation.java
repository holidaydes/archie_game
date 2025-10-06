package com.holiday.archie.handlers;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class ArchieRainAnimation {

    private final int textureWidth;
    private final int textureHeight;
    private final int screenWidth;
    private final int screenHeight;
    private final int speed;
    private final char orient;
    private final Texture texture;
    private final Array<Rectangle> drops;
    private long lastDropTime;
    private final long dropTime;

    public ArchieRainAnimation(String textureName, int textureWidth, int textureHeight, int speed, char orient, int screenWidth, int screenHeight, long dropTime) {
        texture = new Texture(Gdx.files.internal(textureName));
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.speed = speed;
        this.orient = orient;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.dropTime = dropTime;

        drops = new Array<>();
        spawnDrops();
    }

    public void animate(SpriteBatch batch) {
        batch.begin();
        for (Rectangle drop : drops) {
            batch.draw(texture, drop.x, drop.y);
        }
        batch.end();

        if (TimeUtils.nanoTime() - lastDropTime > dropTime) spawnDrops();

        Iterator<Rectangle> iter = drops.iterator();
        while (iter.hasNext()) {
            Rectangle dropRectangle = iter.next();
            switch (orient) {
                case 'x':
                    dropRectangle.x -= speed * Gdx.graphics.getDeltaTime();
                    if (dropRectangle.x + textureWidth < 0) {
                        iter.remove();
                    }
                    break;
                case 'y':
                    dropRectangle.y -= speed * Gdx.graphics.getDeltaTime();
                    if (dropRectangle.y + textureHeight < 0) {
                        iter.remove();
                    }
                    break;
            }
        }
    }

    public void spawnDrops() {
        Rectangle dropRectangle = new Rectangle();
        switch (orient) {
            case 'x':
                dropRectangle.x = screenWidth;
                dropRectangle.y = MathUtils.random(0, screenHeight);
                break;
            case 'y':
                dropRectangle.x = MathUtils.random(0, screenWidth);
                dropRectangle.y = screenHeight;
                break;
        }

        dropRectangle.width = textureWidth;
        dropRectangle.height = textureHeight;
        drops.add(dropRectangle);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void dispose() {
        texture.dispose();
    }
}
