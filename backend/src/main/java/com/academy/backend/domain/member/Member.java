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
@Table(indexes = {
        @Index(name = "idxMemberRole", columnList = "role")
})
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private Boolean isActive;

    @PrePersist
    public void prePersist() {
        if (isActive == null) {
            isActive = true;
        }
    }

    @Builder
    public Member(String loginId, String name, String phone, LocalDate birth, Role role) {
        this.loginId = loginId;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.role = role;
    }
}
