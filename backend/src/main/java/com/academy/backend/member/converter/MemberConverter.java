package com.academy.backend.member.converter;

import com.academy.backend.exception.member.UserMappingException;
import com.academy.backend.member.domain.Member;
import com.academy.backend.member.dto.response.MemberResponse;

public class MemberConverter {

    public static MemberResponse toMemberResponse(Member member) {
        try {
            return MemberResponse.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .phone(member.getPhone())
                    .birth(member.getBirth())
                    .build();
        } catch (Exception e) {
            throw new UserMappingException();
        }
    }
}
