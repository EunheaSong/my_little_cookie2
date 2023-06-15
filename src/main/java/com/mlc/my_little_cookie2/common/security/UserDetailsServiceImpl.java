package com.mlc.my_little_cookie2.common.security;

import com.mlc.my_little_cookie2.common.user.domain.User;
import com.mlc.my_little_cookie2.common.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(""));
        return new UserDetailsImpl(user);
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        User user =  userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException(""));
        return new UserDetailsImpl(user);
    }
}
