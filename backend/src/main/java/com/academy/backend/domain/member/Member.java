package com.academy.backend.domain.member;

import com.academy.backend.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String name;
    private String phone;
    private LocalDate birth;

    // TODO: Index 처리해주기
    @Enumerated(EnumType.STRING)
    @Column(name = "memberRole")
    private Role role;

    @Column(nullable = false)
    private boolean isActive = true;

    @Builder
    public Member(String loginId, String name, String phone, LocalDate birth, Role role) {
        this.loginId = loginId;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.role = role;
    }
}
