package com.gigantic.common.enumeration;

public enum RoleEnum {
    ADMIN(1, 1),
    SALE(2, 2),
    EDITOR(3, 3),
    SHIPPER(4, 4),
    ASSIST(5, 5);

    private final int id;
    private final int level;

    RoleEnum(int id, int level) {
        this.id = id;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
