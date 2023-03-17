package com.holiday.archie.elements;

public enum EnemyBody {
    GHOST(0, 25, 0, 16, 32, 1f, 3f, "enemy_ghost", "sprites/enemy/enemy0.png", null),
    IRON(1, 75, 15, 32, 64, 2f, 1f, "enemy_iron", "sprites/enemy/enemy123.png", EnemySwordBody.OTHER),
    GOLD(2, 150, 25, 32, 64, 3f, 1f, "enemy_gold", "sprites/enemy/enemy123.png", EnemySwordBody.OTHER),
    DIAMOND(3, 200, 35, 32, 64, 4f, 1f, "enemy_diamond", "sprites/enemy/enemy123.png", EnemySwordBody.OTHER),
    BIG_ONE(4,500, 50, 64, 128, 5f, 1.25f, "enemy_big_one", "sprites/enemy/enemy4.png", EnemySwordBody.BIG_ONE);

    public final int id;
    public final int health;
    public final int damage;
    public final int size;
    public final int dimension;
    public final float mass;
    public final float speedX;
    public final String userData;
    public final String bodyTexturePath;
    public final EnemySwordBody sword;

    EnemyBody(int id, int health, int damage, int size, int dimension, float mass, float speedX, String userData, String bodyTexturePath, EnemySwordBody sword) {
        this.id = id;
        this.health = health;
        this.damage = damage;
        this.size = size;
        this.dimension = dimension;
        this.mass = mass;
        this.speedX = speedX;
        this.userData = userData;
        this.bodyTexturePath = bodyTexturePath;
        this.sword = sword;
    }

    public static int getDamage(int id) {
        for (EnemyBody enemyBody : values()) {
            if (enemyBody.id == id) {
                return enemyBody.damage;
            }
        }
        return 0;
    }
}
