package com.holiday.archie.elements;

public enum PlayerShield {
    WOOD(0, "wood", 10, 5f, 0, "pic/start/shieldSkin0.png"),
    IRON(1, "iron", 20, 7f, 25, "pic/start/shieldSkin1.png"),
    GOLD(2, "gold", 30, 10f, 50, "pic/start/shieldSkin2.png"),
    DIAMOND(3, "diamond", 40, 3f, 100, "pic/start/shieldSkin3.png"),
    SKETCH(4, "sketch", 50, 1f, 200, "pic/start/shieldSkin4.png");

    public final int id;
    public final String name;
    public final int defense;
    public final float mass;
    public final int price;
    public final String texturePath;

    PlayerShield(int id, String name, int defense, float mass, int price, String texturePath) {
        this.id = id;
        this.name = name;
        this.defense = defense;
        this.mass = mass;
        this.price = price;
        this.texturePath = texturePath;
    }

    public static PlayerShield getById(int id) {
        for (PlayerShield shield : PlayerShield.values()) {
            if (shield.id == id) {
                return shield;
            }
        }
        throw new RuntimeException(String.format("Player shield does not exist! id: [%d]", id));
    }
}
