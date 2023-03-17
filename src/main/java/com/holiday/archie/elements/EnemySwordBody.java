package com.holiday.archie.elements;

public enum EnemySwordBody {
    OTHER(0, "sprites/enemy/enemySword0.png", 64, 10),
    BIG_ONE(4, "sprites/enemy/enemySword4.png", 128, 128);

    public final int id;
    public final String texturePath;
    public final int width;
    public final int height;

    EnemySwordBody(int id, String texturePath, int width, int height) {
        this.id = id;
        this.texturePath = texturePath;
        this.width = width;
        this.height = height;
    }

    public static EnemySwordBody getById(int id) {
        if (id == 4) {
            return BIG_ONE;
        }
        return OTHER;
    }
}
