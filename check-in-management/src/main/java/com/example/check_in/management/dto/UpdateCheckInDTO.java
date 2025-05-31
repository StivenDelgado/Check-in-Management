package com.example.check_in.management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCheckInDTO {
    
    @NotBlank(message = "End time is required")
    private String time;
}
