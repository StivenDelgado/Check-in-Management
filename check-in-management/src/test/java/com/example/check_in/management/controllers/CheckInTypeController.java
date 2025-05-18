package com.example.check_in.management.controllers;

import com.example.check_in.management.dto.CheckInTypeDTO;
import com.example.check_in.management.services.CheckInTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CheckInTypeController.class)
class CheckInTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckInTypeService checkInTypeService;

    private List<CheckInTypeDTO> mockList;

    @BeforeEach
    void setUp() {
        // Setup mock data before each test
        List<CheckInTypeDTO> mockList = new ArrayList<>();
        CheckInTypeDTO checkInType1 = new CheckInTypeDTO();
        checkInType1.setCheckInTypeId(1L);
        checkInType1.setDescription("Entrada");
        CheckInTypeDTO checkInType2 = new CheckInTypeDTO();
        checkInType2.setCheckInTypeId(2L);
        checkInType2.setDescription("Salida");
        mockList.add(checkInType1);
        mockList.add(checkInType2);
        // Mock the service layer response
        when(checkInTypeService.findAll()).thenReturn(mockList);
    }

    @Test
    @DisplayName("GET /check-in-type should return list of CheckInTypeDTO")
    void testGetAllCheckInTypes() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/check-in-type")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].checkInTypeId").value(1))
            .andExpect(jsonPath("$[0].description").value("Entrada"))
            .andExpect(jsonPath("$[1].checkInTypeId").value(2))
            .andExpect(jsonPath("$[1].description").value("Salida"));
    }
}
