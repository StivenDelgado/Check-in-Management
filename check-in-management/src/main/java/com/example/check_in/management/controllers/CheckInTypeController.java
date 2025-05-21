package com.example.check_in.management.controllers;

import com.example.check_in.management.dto.CheckInTypeDTO;
import com.example.check_in.management.services.CheckInTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("check-in-type")
public class CheckInTypeController {

    @Autowired
    private CheckInTypeService checkInTypeService;

    @GetMapping
    public List<CheckInTypeDTO> getAllCheckInTypes() {
        return checkInTypeService.findAll();
    }
}