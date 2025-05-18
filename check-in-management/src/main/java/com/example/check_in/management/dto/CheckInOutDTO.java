package com.example.check_in.management.dto;

import lombok.Data;

@Data
public class CheckInOutDTO {
    
    Long checkInId;
    String CheckInType;
    String endTime;
    String weekDay;

}
