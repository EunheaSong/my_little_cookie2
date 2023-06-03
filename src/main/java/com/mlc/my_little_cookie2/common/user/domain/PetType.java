package com.mlc.my_little_cookie2.common.user.domain;

public enum PetType {
    CAT("고양이"),
    DOG("개");

    private String type;

    PetType(String type) {
        this.type = type;
    }
}
