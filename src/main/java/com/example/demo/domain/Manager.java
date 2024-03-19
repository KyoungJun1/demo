package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@Table(name = "MANAGERS")
@AllArgsConstructor
@NoArgsConstructor
public class Manager {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long managerNo;

    @Column(nullable = false, unique = true)
    private String managerId;

    @Column(nullable = false)
    private String managerPw;

    @Column(nullable = false)
    private String managerName;

    @Column(nullable = false, unique = true)
    private String mangerNickName;


}
