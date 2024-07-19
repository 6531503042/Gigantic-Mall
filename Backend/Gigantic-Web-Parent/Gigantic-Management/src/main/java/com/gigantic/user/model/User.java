package com.gigantic.user.model;

import javax.persistence.Id;

import javax.persistence.Table;

@Table(name = "users")
public record User (
    @Id
    Integer id,
    String firstName,
    String lastName,
    String email,
    String password,
    String photos,
    String phoneNumber,
    boolean status) {
    }
