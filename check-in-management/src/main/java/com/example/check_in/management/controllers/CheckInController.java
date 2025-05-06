package com.example.check_in.management.controllers;


import com.example.check_in.management.services.CheckInService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.check_in.management.dto.CreateCheckInDTO;
import com.example.check_in.management.dto.UpdateCheckInDTO;
import com.example.check_in.management.dto.CheckInDTO;
import com.example.check_in.management.dto.CheckInOutDTO;
import com.example.check_in.management.dto.CheckInUserDTO;

import java.util.List;

@RestController
@RequestMapping("check-in")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @GetMapping("/{id}/{date}") 
    public  ResponseEntity<List<CheckInUserDTO>> getCheckInByUserId(@PathVariable Long id, @PathVariable String date) {
        List<CheckInUserDTO> checkIn = checkInService.findByUserId(id, date);
        return checkIn != null ? ResponseEntity.ok(checkIn) : ResponseEntity.notFound().build();
    }

    @PostMapping("/registerCheckIn")
    public  ResponseEntity<CheckInDTO> createCheckIn(@Valid @RequestBody CreateCheckInDTO checkIn) {
        return  checkInService.save(checkIn) != null ? ResponseEntity.ok(checkInService.save(checkIn)) : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public CheckInOutDTO updateCheckIn(@PathVariable Long id, @RequestBody UpdateCheckInDTO checkInDetails) {
        return checkInService.updateCheckIn(id, checkInDetails);
    }

}