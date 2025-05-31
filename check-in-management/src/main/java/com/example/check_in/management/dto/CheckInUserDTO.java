package com.example.check_in.management.dto;

import lombok.Data;

@Data
public class CheckInUserDTO {
    private Long checkInId;
    private String checkInType;
    private String time;
}
