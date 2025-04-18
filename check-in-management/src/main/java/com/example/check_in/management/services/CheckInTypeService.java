package com.example.check_in.management.services;

import com.example.check_in.management.models.CheckInType;
import com.example.check_in.management.repositories.CheckInTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckInTypeService {

    @Autowired
    private CheckInTypeRepository checkInTypeRepository;

    public List<CheckInType> findAll() {
        return checkInTypeRepository.findAll();
    }

    public Optional<CheckInType> findById(Long id) {
        return checkInTypeRepository.findById(id);
    }

    public CheckInType save(CheckInType checkInType) {
        return checkInTypeRepository.save(checkInType);
    }

    public Optional<CheckInType> update(Long id, CheckInType checkInTypeDetails) {
        Optional<CheckInType> existingCheckInType = checkInTypeRepository.findById(id);
        if (existingCheckInType.isPresent()) {
            CheckInType updatedCheckInType = existingCheckInType.get();
            return Optional.of(checkInTypeRepository.save(updatedCheckInType));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        checkInTypeRepository.deleteById(id);
    }
}