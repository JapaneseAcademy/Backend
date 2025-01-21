package com.academy.backend.config.auth;

import com.academy.backend.exception.member.UserNotFoundException;
import com.academy.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new PrincipalDetails(memberRepository.findByLoginId(username).orElseThrow(
                () -> new UserNotFoundException(username)
        ));
    }
}
