package com.mlc.my_little_cookie2.common.user.domain;

import com.mlc.my_little_cookie2.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.mlc.my_little_cookie2.common.CustomUUID.uuid;

@Entity
@Getter
@NoArgsConstructor
public class Pet extends BaseEntity {

    @Id
    public String id = uuid();

    public String name;

    public int age;

    public PetType type;

    public String image;

    public Boolean isRainbowBridge;

    @ManyToOne(fetch = FetchType.LAZY)
    public User user;

}
