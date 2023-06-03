package com.mlc.my_little_cookie2.common.user.domain;

import com.mlc.my_little_cookie2.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pet extends BaseEntity {

    @Id
    public String id;

    public String name;

    public int age;

    public PetType type;

    public String image;

    public Boolean isRainbowBridge;

    @ManyToOne(fetch = FetchType.LAZY)
    public User user;

}
