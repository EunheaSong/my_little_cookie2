package com.mlc.my_little_cookie2.common.user.domain;

import com.mlc.my_little_cookie2.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.mlc.my_little_cookie2.common.CustomUUID.uuid;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    public String id = uuid();

    public String name;

    public String mobileNo;

    public LocalDate birthDay;

    public String nickname;

    public String email;

    public String password;

    public String image;
}
