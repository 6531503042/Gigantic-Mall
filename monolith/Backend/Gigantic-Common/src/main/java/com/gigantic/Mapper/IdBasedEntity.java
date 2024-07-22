package com.gigantic.Mapper;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

    @MappedSuperclass
    public abstract class IdBasedEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        protected Long id;

        protected void setId(Long id) {
            this.id = id;
        }

        protected Long getId() {
            return id;
        }
    }
