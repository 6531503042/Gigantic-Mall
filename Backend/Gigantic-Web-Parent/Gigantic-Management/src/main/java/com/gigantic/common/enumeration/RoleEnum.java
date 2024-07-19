package com.gigantic.common.enumeration;

public enum RoleEnum {
    ADMIN(1),
    SALE(2),
    EDITOR(3),
    SHIPPER(4),
    ASSIST(5);

    private final int id;


    //Constructor
    RoleEnum(int id) {
        this.id = id;
    }

    //Getter
    public int getId() {
        return id;
    }
}
