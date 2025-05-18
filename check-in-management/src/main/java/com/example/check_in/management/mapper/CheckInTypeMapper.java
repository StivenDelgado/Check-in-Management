package com.example.check_in.management.mapper;

import org.mapstruct.Mapper;

import com.example.check_in.management.dto.CheckInTypeDTO;
import com.example.check_in.management.models.CheckInType;

@Mapper(componentModel = "spring")
public interface CheckInTypeMapper {

    CheckInTypeDTO toCheckInTypeDTO(CheckInType checkInType);
    
}
