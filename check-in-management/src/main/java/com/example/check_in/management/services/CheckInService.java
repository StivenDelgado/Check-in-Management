package com.example.check_in.management.services;

import com.example.check_in.management.dto.CheckInDTO;
import com.example.check_in.management.dto.CheckInCreateDTO;
import com.example.check_in.management.models.CheckIn;
import com.example.check_in.management.models.CheckInType;
import com.example.check_in.management.repositories.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    public List<CheckIn> findAll() {
        return checkInRepository.findAll();
    }

    public Optional<CheckIn> findById(Long id) {
        return checkInRepository.findById(id);
    }

    public CheckIn save(CheckIn checkIn) {
        return checkInRepository.save(checkIn);
    }

    public Optional<CheckIn> update(Long id, CheckIn checkInDetails) {
        Optional<CheckIn> existingCheckIn = checkInRepository.findById(id);
        if (existingCheckIn.isPresent()) {
            CheckIn updatedCheckIn = existingCheckIn.get();
            return Optional.of(checkInRepository.save(updatedCheckIn));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        checkInRepository.deleteById(id);
    }
}