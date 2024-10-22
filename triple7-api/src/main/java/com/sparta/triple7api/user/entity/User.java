package com.sparta.triple7api.user.entity;

import com.sparta.triple7api.common.entity.Timestamped;
import com.sparta.triple7api.subscribe.entity.Following;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 256, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean userStatus = true; // 유저 상태 (true: 활성, false: 탈퇴)

    @OneToMany(mappedBy = "followingUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Following> followingList = new ArrayList<>();

    // 생성자: 필수 필드만 포함
    private User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // 정적 팩토리 메서드
    public static User of(String email, String password, String name) {
        return new User(email, password, name);
    }

    // 비밀번호 변경 기능
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // 유저 탈퇴 처리
    public void withdrawUser() {
        this.userStatus = false;
    }
}