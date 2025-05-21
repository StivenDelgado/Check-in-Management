package com.example.check_in.management.services;

import com.example.check_in.management.dto.CheckInTypeDTO;
import com.example.check_in.management.mapper.CheckInTypeMapper;
import com.example.check_in.management.repositories.CheckInTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInTypeService {

    @Autowired
    private CheckInTypeRepository checkInTypeRepository;

    @Autowired
    private CheckInTypeMapper checkInTypeMapper;

    public List<CheckInTypeDTO> findAll() {
        return checkInTypeRepository.findAll().stream()
                .map(checkInTypeMapper::toCheckInTypeDTO)
                .toList();
    }

}