package com.example.demo.dto.request.manager;


import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ManagerAddDto {


    @Valid
    private String id;

    @Valid
    private String pw;

    @Valid
    private String name;

    @Valid
    private String nickName;


}
