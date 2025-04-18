package com.example.check_in.management.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CheckInDTO {
    private Long checkInId;
    private Long userId;
    private String checkInTypeName; // Nombre del tipo de check-in
    private String startTime;
    private String endTime;
    private String weekDay;
    private int dailyHours;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}