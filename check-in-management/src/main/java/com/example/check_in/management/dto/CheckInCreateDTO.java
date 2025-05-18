package com.example.check_in.management.dto;
import lombok.Data;


@Data
public class CheckInCreateDTO {
    private Long userId;
    private Long checkInTypeId; // ID del tipo de check-in
    private String startTime;
    private String endTime;
    private String weekDay;
    private int dailyHours;

}