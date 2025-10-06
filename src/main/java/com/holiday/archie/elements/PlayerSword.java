package com.holiday.archie.elements;

public enum PlayerSword {
    WOOD(0, "wood", 25, 5f, 0, "pic/start/swordSkin0.png"),
    IRON(1, "iron", 50, 7f, 25, "pic/start/swordSkin1.png"),
    GOLD(2, "gold", 75, 10f, 50, "pic/start/swordSkin2.png"),
    DIAMOND(3, "diamond", 100, 3f, 100, "pic/start/swordSkin3.png"),
    SKETCH(4, "sketch", 125, 1f, 200, "pic/start/swordSkin4.png");

    public final int id;
    public final String name;
    public final int damage;
    public final float mass;
    public final int price;
    public final String texturePath;

    PlayerSword(int id, String name, int damage, float mass, int price, String texturePath) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.mass = mass;
        this.price = price;
        this.texturePath = texturePath;
    }

    public static PlayerSword getById(int id) {
        for (PlayerSword sword : PlayerSword.values()) {
            if (sword.id == id) {
                return sword;
            }
        }
        throw new RuntimeException(String.format("Player sword does not exist! id: [%d]", id));
    }
}
