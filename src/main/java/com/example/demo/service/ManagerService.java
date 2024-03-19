package com.example.demo.service;

import com.example.demo.domain.Manager;
import com.example.demo.dto.request.manager.ManagerAddDto;
import com.example.demo.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public void managerAdd(ManagerAddDto managerAddDto) {

        Manager manager = Manager.builder()
                .managerId(managerAddDto.getId())
                .managerPw(managerAddDto.getPw())
                .managerName(managerAddDto.getName()).build();

        managerRepository.save(manager);

    }
}
