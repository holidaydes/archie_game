package com.holiday.archie.elements;

public enum PlayerBody {
    WOOD(0, "wood", 100, 1.5f, 10f, 0, "pic/start/archieFace0.png"),
    IRON(1, "iron", 125, 2f, 15f, 25, "pic/start/archieFace1.png"),
    GOLD(2, "gold", 150, 3f, 20f, 50, "pic/start/archieFace2.png"),
    DIAMOND(3, "diamond", 175, 4f, 5f, 100, "pic/start/archieFace3.png"),
    SKETCH(4, "sketch", 200, 5f, 1f, 200, "pic/start/archieFace4.png");

    public final int id;
    public final String name;
    public final int health;
    public final float stamina;
    public final float mass;
    public final int price;
    public final String texturePath;

    PlayerBody(int id, String name, int health, float stamina, float mass, int price, String texturePath) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.stamina = stamina;
        this.mass = mass;
        this.price = price;
        this.texturePath = texturePath;
    }

    public static PlayerBody getById(int id) {
        for (PlayerBody body : PlayerBody.values()) {
            if (body.id == id) {
                return body;
            }
        }
        throw new RuntimeException(String.format("Player body does not exist! id: [%d]", id));
    }
}
