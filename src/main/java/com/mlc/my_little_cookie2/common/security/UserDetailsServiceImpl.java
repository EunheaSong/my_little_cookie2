package com.mlc.my_little_cookie2.common.security;

import com.mlc.my_little_cookie2.common.user.domain.Member;
import com.mlc.my_little_cookie2.common.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(""));
        return new UserDetailsImpl(user);
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        Member user =  userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException(""));
        return new UserDetailsImpl(user);
    }
}
