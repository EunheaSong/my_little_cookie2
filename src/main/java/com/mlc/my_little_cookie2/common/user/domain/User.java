package com.mlc.my_little_cookie2.common.user.domain;

import com.mlc.my_little_cookie2.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class User extends BaseEntity {

    @Id
    public String id;

    public String name;

    public String mobileNo;

    public LocalDate birthDay;

    public String nickname;

    public String email;

    public String password;

    public String image;
}
