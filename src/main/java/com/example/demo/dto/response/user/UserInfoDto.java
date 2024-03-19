package com.example.demo.dto.response.user;


import com.example.demo.domain.Manager;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

    private Manager managerNo;

    private String userId;

    private String userPw;

    private String userName;

    private String userNickName;

    private Integer trainingCount;

}
