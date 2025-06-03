package com.example.check_in.management.dto;

import lombok.Data;

@Data
public class CheckInDTO {
    
    Long checkInId;
    String CheckInType;
    String time;
    String weekDay;
}
