package com.holiday.archie.elements;

public enum Level {
    ARCHIE_LOST(0, "archie lost", "archie, the robot\none day reach\na huge forest...", "levels/level0.tmx"),
    THE_FOREST(1, "the forest", "archie lost, he can't\nfind the way out\nfrom the forest...", "levels/level1.tmx"),
    INTO_THE_DEEP(2, "into\nthe deep", "archie go\ndeeper into\nthe dark forest...", "levels/level2.tmx"),
    NEAR_THE_END(3, "near\nthe end", "archie finally see\nthe end of\nthe forest...", "levels/level3.tmx"),
    THE_END(4, "the end", "this is the end?\nhe hopes so...", "levels/level4.tmx");

    public final int id;
    public final String name;
    public final String description;
    public final String mapPath;

    Level(int id, String name, String description, String mapPath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mapPath = mapPath;
    }

    public static Level getById(int id) {
        for (Level level : values()) {
            if (level.id == id) {
                return level;
            }
        }
        throw new RuntimeException(String.format("Level does not exist! id: [%d]", id));
    }
}
