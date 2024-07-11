package com.holiday.archie.elements;

public enum ItemType {
    COIN("coins", "sprites/item/coinItem.png"),
    MEDIC_KIT("medickit", "sprites/item/lifeItem.png"),
    TRAP("traps", "sprites/item/trapItem.png");

    public final String layerName;
    public final String texturePath;

    ItemType(String layerName, String texturePath) {
        this.layerName = layerName;
        this.texturePath = texturePath;
    }
}
