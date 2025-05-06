package com.example.check_in.management.services;

import com.example.check_in.management.dto.CreateCheckInDTO;
import com.example.check_in.management.dto.UpdateCheckInDTO;
import com.example.check_in.management.models.CheckIn;
import com.example.check_in.management.models.CheckInType;
import com.example.check_in.management.repositories.CheckInRepository;
import com.example.check_in.management.repositories.CheckInTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<CheckInUserDTO> findByUserId(Long id, String date) {
        System.out.println("id: " + date);
        return  checkInRepository.findByUserIdAndDate(id, date).stream().map(checkIn -> checkInMapper.toUserDTO(checkIn)).collect(Collectors.toList());
    }

    public CheckInDTO save(CreateCheckInDTO checkIn) {
        CheckInType checkInType = checkInTypeRepository.findById(checkIn.getCheckInTypeId())
                .orElseThrow(() -> new IllegalArgumentException("CheckInType not found with id: " + checkIn.getCheckInTypeId()));
        CheckIn checkInEntity = checkInMapper.toEntity(checkIn);
        checkInEntity.setCheckInType(checkInType);
        return checkInMapper.toCheckInDTO(checkInRepository.save(checkInEntity));
    }

    public CheckInOutDTO updateCheckIn(Long id, UpdateCheckInDTO checkInDetails) {
        Optional<CheckIn> existingCheckIn = checkInRepository.findById(id);
        if (existingCheckIn.isPresent()) {
            CheckIn checkIn = existingCheckIn.get();
            checkIn.setEndTime(checkInDetails.getEndTime());
            return checkInMapper.tCheckInOutDTO(checkInRepository.save(checkIn));
        }
        return null;
    }

}