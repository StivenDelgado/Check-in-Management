package com.example.check_in.management.services;

import com.example.check_in.management.dto.CreateCheckInDTO;
import com.example.check_in.management.dto.UpdateCheckInDTO;
import com.example.check_in.management.models.CheckIn;
import com.example.check_in.management.models.CheckInType;
import com.example.check_in.management.repositories.CheckInRepository;
import com.example.check_in.management.repositories.CheckInTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.check_in.management.client.ApiClient;
import com.example.check_in.management.dto.CheckInDTO;
import com.example.check_in.management.dto.CheckInOutDTO;
import com.example.check_in.management.dto.CheckInUserDTO;
import com.example.check_in.management.mapper.CheckInMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private CheckInTypeRepository checkInTypeRepository;

    @Autowired
    private CheckInMapper checkInMapper;

    @Autowired
    private ApiClient apiClient;

    public List<CheckInUserDTO> findByUserId(Long id, String date) {
        System.out.println("id: " + date);
        return  checkInRepository.findByUserIdAndDate(id, date).stream().map(checkIn -> checkInMapper.toUserDTO(checkIn)).collect(Collectors.toList());
    }

    public CheckInDTO save(CreateCheckInDTO checkIn, String authorization) {
        CheckInType checkInType = checkInTypeRepository.findById(checkIn.getCheckInTypeId())
                .orElseThrow(() -> new IllegalArgumentException("CheckInType not found with id: " + checkIn.getCheckInTypeId()));

        CheckIn checkInEntity = checkInMapper.toEntity(checkIn);

        System.out.println("checkInEntity: " + authorization);

        Boolean employeeExists = apiClient.employeeExists(checkIn.getUserId(), authorization);
        if (employeeExists == null || !employeeExists) {
            throw new IllegalArgumentException("Employee does not exist or request failed");
        }

        checkInEntity.setCheckInType(checkInType);
        return checkInMapper.toCheckInDTO(checkInRepository.save(checkInEntity));

    }

    public CheckInOutDTO updateCheckIn(Long id, UpdateCheckInDTO checkInDetails) {
        Optional<CheckIn> existingCheckIn = checkInRepository.findById(id);
        if (existingCheckIn.isPresent()) {
            CheckIn checkIn = existingCheckIn.get();
            checkIn.setTime(checkInDetails.getTime());
            return checkInMapper.tCheckInOutDTO(checkInRepository.save(checkIn));
        }
        return null;
    }

}