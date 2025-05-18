package com.example.check_in.management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.check_in.management.dto.CreateCheckInDTO;
import com.example.check_in.management.dto.UpdateCheckInDTO;
import com.example.check_in.management.dto.CheckInDTO;
import com.example.check_in.management.dto.CheckInOutDTO;
import com.example.check_in.management.dto.CheckInUserDTO;
import com.example.check_in.management.models.CheckIn;

@Mapper(componentModel = "spring")
public interface CheckInMapper {

    @Mapping(source = "checkInType.description", target = "checkInType")
    CheckInUserDTO toUserDTO(CheckIn checkIn);

    CheckIn toEntity(CreateCheckInDTO dto);

    CheckIn updateToEntity(UpdateCheckInDTO dto);
    
    @Mapping(source = "checkInType.description", target = "checkInType")
    CheckInDTO toCheckInDTO(CheckIn checkIn);

    @Mapping(source = "checkInType.description", target = "checkInType")
    CheckInOutDTO tCheckInOutDTO(CheckIn checkIn);
}
