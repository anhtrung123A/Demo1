package com.train.aimforthehead.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int id;
    @Column(name = "username", unique = true)
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "role")
    String role;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "pfp")
    String pfp;
    @Column(name = "address")
    String address;
    @Column(name = "nickname")
    String nickname;
}
