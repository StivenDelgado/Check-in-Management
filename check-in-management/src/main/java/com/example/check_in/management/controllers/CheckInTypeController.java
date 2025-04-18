package com.example.check_in.management.controllers;

import com.example.check_in.management.models.CheckInType;
import com.example.check_in.management.services.CheckInTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("check-in-type")
public class CheckInTypeController {

    @Autowired
    private CheckInTypeService checkInTypeService;

    @GetMapping
    public List<CheckInType> getAllCheckInTypes() {
        return checkInTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckInType> getCheckInTypeById(@PathVariable Long id) {
        Optional<CheckInType> checkInType = checkInTypeService.findById(id);
        return checkInType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CheckInType createCheckInType(@RequestBody CheckInType checkInType) {
        return checkInTypeService.save(checkInType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckInType> updateCheckInType(@PathVariable Long id, @RequestBody CheckInType checkInTypeDetails) {
        Optional<CheckInType> updatedCheckInType = checkInTypeService.update(id, checkInTypeDetails);
        return updatedCheckInType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckInType(@PathVariable Long id) {
        if (checkInTypeService.findById(id).isPresent()) {
            checkInTypeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}