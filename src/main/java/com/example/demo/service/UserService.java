package com.example.demo.service;


import com.example.demo.domain.Manager;
import com.example.demo.domain.User;
import com.example.demo.dto.request.user.UserAddDto;
import com.example.demo.dto.response.user.UserInfoDto;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //계정 생성
    public void userAdd(@NonNull UserAddDto userAddDto) {

        //패스워드 암호화
        //미구현

        //-->
        User user = User.builder()
                .userId(userAddDto.getId())
                .userPw(userAddDto.getPw())
                .userName(userAddDto.getName())
                .userNickName(userAddDto.getNickName()).build();


        userRepository.save(user);
    }

    //아이디 중복 확인
    public String userIdCheck(String userId) {

        User user = userRepository.findByUserId(userId);

        return (user != null) ? "중복된 아이디" : "사용가능한 아이디";

    }

    //닉네임 중복 확인
    public String nickNameCheck(String userNickName) {

        User user = userRepository.findByUserNickName(userNickName);

        return (user != null) ? "중복된 닉네임" : "사용가능한 닉네임";
    }

    //로그인
    public boolean loginCheck(@NonNull UserAddDto userAddDto) {

        User user = userRepository.findByUserIdAndUserPw(userAddDto.getId(), userAddDto.getPw());

        return user != null;
    }


    //정보 조회
    public UserInfoDto infoCheck(String userId) {

        User user = userRepository.findByUserId(userId);

            Manager manager = user.getManagerNo();
            log.info(manager.toString());


//            UserInfoDto infoResult = UserInfoDto.builder().userId().userName().userNickName().trainingCount().managerNo().build();

        return null;
    }
}
