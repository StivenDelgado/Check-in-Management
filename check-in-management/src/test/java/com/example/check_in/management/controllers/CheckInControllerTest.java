package com.example.check_in.management.controllers;

import com.example.check_in.management.controllers.CheckInController;
import com.example.check_in.management.dto.*;
import com.example.check_in.management.services.CheckInService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CheckInController.class)
public class CheckInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckInService checkInService;

    @Autowired
    private ObjectMapper objectMapper;

    private CreateCheckInDTO validCreateCheckInDTO;
    private UpdateCheckInDTO validUpdateCheckInDTO;
    private CheckInDTO mockCheckInDTO;
    private CheckInOutDTO mockCheckInOutDTO;
    String authorization;

    @BeforeEach
    void setUp() {
        validCreateCheckInDTO = new CreateCheckInDTO();
        validCreateCheckInDTO.setUserId(1L);
        validCreateCheckInDTO.setCheckInTypeId(2L);
        validCreateCheckInDTO.setTime("08:00");
        validCreateCheckInDTO.setWeekDay("Monday");

        validUpdateCheckInDTO = new UpdateCheckInDTO();
        validUpdateCheckInDTO.setTime("17:00");

        mockCheckInDTO = new CheckInDTO();
        mockCheckInDTO.setCheckInId(10L);
        mockCheckInDTO.setCheckInType("Entrada");
        mockCheckInDTO.setTime("08:00");
        mockCheckInDTO.setWeekDay("Monday");

        mockCheckInOutDTO = new CheckInOutDTO();
        mockCheckInOutDTO.setCheckInId(10L);
        mockCheckInOutDTO.setCheckInType("Salida");
        mockCheckInOutDTO.setTime("17:00");
        mockCheckInOutDTO.setWeekDay("Monday");

        authorization = "Bearer token";
    }

    @Test
    void testGetCheckInByUserId_ReturnsOk() throws Exception {
        Long userId = 1L;
        String date = "2025-05-01";

        List<CheckInUserDTO> mockList = Arrays.asList(new CheckInUserDTO());
        Mockito.when(checkInService.findByUserId(userId, date)).thenReturn(mockList);

        mockMvc.perform(get("/check-in/{id}/{date}", userId, date))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCheckInByUserId_ReturnsNotFound() throws Exception {
        Mockito.when(checkInService.findByUserId(1L, "2025-05-01")).thenReturn(null);

        mockMvc.perform(get("/check-in/{id}/{date}", 1L, "2025-05-01"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCheckIn_ReturnsOk() throws Exception {
        Mockito.when(checkInService.save(any(CreateCheckInDTO.class), anyString())).thenReturn(mockCheckInDTO);

        mockMvc.perform(post("/check-in/registerCheckIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
                        .content(objectMapper.writeValueAsString(validCreateCheckInDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCheckIn_ReturnsNotFound() throws Exception {
        Mockito.when(checkInService.save(any(CreateCheckInDTO.class), anyString())).thenReturn(null);

        mockMvc.perform(post("/check-in/registerCheckIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
                        .content(objectMapper.writeValueAsString(validCreateCheckInDTO)))
                        
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateCheckIn_ReturnsOk() throws Exception {
        Mockito.when(checkInService.updateCheckIn(eq(1L), any(UpdateCheckInDTO.class)))
                .thenReturn(mockCheckInOutDTO);

        mockMvc.perform(put("/check-in/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUpdateCheckInDTO)))
                .andExpect(status().isOk());
    }
}
