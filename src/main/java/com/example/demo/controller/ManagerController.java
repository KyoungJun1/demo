package com.example.demo.controller;

import com.example.demo.dto.request.manager.ManagerAddDto;
import com.example.demo.service.ManagerService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/add")
    public void managerAdd(@RequestBody ManagerAddDto managerAddDto){

        managerService.managerAdd(managerAddDto);
    }


}
