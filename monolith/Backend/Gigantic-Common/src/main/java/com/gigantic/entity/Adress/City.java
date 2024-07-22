package com.gigantic.entity.Adress;

import com.gigantic.Mapper.IdBasedEntity;

import javax.persistence.Column;

public class City extends IdBasedEntity {

    @Column(name = "name", length = 300, nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private int code;

    //Constructor
    public City() {
        //Default Constructor
    }

    public City(String name, int code) {
        this.name = name;
        this.code = code;
    }

    //Getter & Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
