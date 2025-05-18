package com.example.check_in.management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateCheckInDTO {

    @Positive(message = "User id must be a positive number")
    private Long userId;
    
    @NotNull(message = "Check-in type is required")
    private Long checkInTypeId;
    
    @NotNull(message = "Start time is required")
    private String startTime;

    @NotNull(message = "Week day is required")
    private String weekDay;

}