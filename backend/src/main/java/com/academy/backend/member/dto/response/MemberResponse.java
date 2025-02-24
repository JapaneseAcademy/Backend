package com.academy.backend.member.dto.response;

import com.academy.backend.member.domain.Member;
import com.academy.backend.member.domain.Role;
import com.academy.backend.exception.member.UserMappingException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MemberResponse {

    private Long id;
    private String name;
    private String phone;
    private LocalDate birth;
    private Role role;

    public static MemberResponse of(Member member) {
        try {
            return MemberResponse.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .birth(member.getBirth())
                    .role(member.getRole())
                    .build();
        } catch (Exception e) {
            throw new UserMappingException();
        }
    }
}
