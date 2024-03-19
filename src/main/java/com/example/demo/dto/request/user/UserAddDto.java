package com.example.demo.dto.request.user;


import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class UserAddDto {


    @Valid
    private String id;

    @Valid
    private String pw;

    @Valid
    private String name;

    @Valid
    private String nickName;


}
