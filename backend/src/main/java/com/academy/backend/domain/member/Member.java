package com.academy.backend.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Member {

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
}
