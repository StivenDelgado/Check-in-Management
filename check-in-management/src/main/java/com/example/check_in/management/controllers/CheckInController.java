package com.example.check_in.management.controllers;

import com.example.check_in.management.models.CheckIn;
import com.example.check_in.management.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("check-in")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @GetMapping
    public List<CheckIn> getAllCheckIns() {
        return checkInService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> getCheckInById(@PathVariable Long id) {
        Optional<CheckIn> checkIn = checkInService.findById(id);
        return checkIn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CheckIn createCheckIn(@RequestBody CheckIn checkIn) {
        return checkInService.save(checkIn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckIn> updateCheckIn(@PathVariable Long id, @RequestBody CheckIn checkInDetails) {
        Optional<CheckIn> updatedCheckIn = checkInService.update(id, checkInDetails);
        return updatedCheckIn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable Long id) {
        if (checkInService.findById(id).isPresent()) {
            checkInService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}