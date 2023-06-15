package com.mlc.my_little_cookie2.common.user.repository;

import com.mlc.my_little_cookie2.common.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByName(String name);


}
