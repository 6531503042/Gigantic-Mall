package com.gigantic.Mapper;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

    @Getter
    @Setter
    @MappedSuperclass
    public abstract class IdBasedEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        protected Long id;
}
