package com.example.demo.controller;


import com.example.demo.dto.request.user.UserAddDto;
import com.example.demo.dto.response.user.UserInfoDto;
import com.example.demo.dto.response.user.UserStringDto;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    //계정 생성
    @PostMapping("/add")
    public void userAdd(@RequestBody UserAddDto userAddDto){
        userService.userAdd(userAddDto);
    }

    //아이디 중복 확인
    @GetMapping("/idCheck/{userId}")
    public UserStringDto userIdCheck(@PathVariable String userId){

       String data =  userService.userIdCheck(userId);

        return UserStringDto.builder().result(data).build();
    }
    
    //닉네임 중복 확인
    @GetMapping("/nickNameCheck/{userNickName}")
    public UserStringDto nickNameCheck(@PathVariable String userNickName){

        String data =  userService.nickNameCheck(userNickName);

        return UserStringDto.builder().result(data).build();
    }

    //로그인
    @PostMapping("/login")
    public UserStringDto loginCheck(@RequestBody UserAddDto userAddDto){

        boolean data = userService.loginCheck(userAddDto);

        if(data)//조건 여부에 따라 세션 처리
            return UserStringDto.builder().result("true").build();
        else
            return UserStringDto.builder().result("false").build();

    }

    //회원정보 조회
    @GetMapping("/info/{userId}")
    public UserInfoDto infoCheck(@PathVariable String userId){

        UserInfoDto info = userService.infoCheck(userId);

        return UserInfoDto.builder().build();

    }
    //회원정보 수정

    //회원 탈퇴


    //패스워드 암호화



}
