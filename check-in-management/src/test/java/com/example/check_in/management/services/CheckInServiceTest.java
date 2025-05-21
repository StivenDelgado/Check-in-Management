package com.example.check_in.management.services;

import com.example.check_in.management.client.ApiClient;
import com.example.check_in.management.dto.*;
import com.example.check_in.management.mapper.CheckInMapper;
import com.example.check_in.management.models.CheckIn;
import com.example.check_in.management.models.CheckInType;
import com.example.check_in.management.repositories.CheckInRepository;
import com.example.check_in.management.repositories.CheckInTypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckInServiceTest {

    @InjectMocks
    private CheckInService checkInService;

    @Mock
    private CheckInRepository checkInRepository;

    @Mock
    private CheckInTypeRepository checkInTypeRepository;

    @Mock
    private ApiClient apiClient;

    @Mock
    private CheckInMapper checkInMapper;

    private AutoCloseable closeable;

    private String authorization;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        authorization = "Bearer token";
    }

    @Test
    void testFindByUserId_ReturnsCheckInUserDTOList() {
        Long userId = 1L;
        String date = "2024-05-15";
        CheckIn checkIn = new CheckIn();
        CheckInUserDTO dto = new CheckInUserDTO();

        when(checkInRepository.findByUserIdAndDate(userId, date)).thenReturn(List.of(checkIn));
        when(checkInMapper.toUserDTO(checkIn)).thenReturn(dto);

        List<CheckInUserDTO> result = checkInService.findByUserId(userId, date);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(checkInRepository).findByUserIdAndDate(userId, date);
    }

    @Test
    void testSave_Success() {
        CreateCheckInDTO createDto = new CreateCheckInDTO();
        createDto.setCheckInTypeId(1L);
        createDto.setUserId(1L);

        CheckInType type = new CheckInType();
        CheckIn entity = new CheckIn();
        CheckIn saved = new CheckIn();
        CheckInDTO dto = new CheckInDTO();

        when(checkInTypeRepository.findById(1L)).thenReturn(Optional.of(type));
        when(apiClient.employeeExists(1L, authorization)).thenReturn(true);
        when(checkInMapper.toEntity(createDto)).thenReturn(entity);
        when(checkInRepository.save(entity)).thenReturn(saved);
        when(checkInMapper.toCheckInDTO(saved)).thenReturn(dto);

        CheckInDTO result = checkInService.save(createDto, authorization);

        assertNotNull(result);
        verify(checkInRepository).save(entity);
        verify(apiClient).employeeExists(1L, authorization);
    }

    @Test
    void testSave_EmployeeNotExists_ThrowsException() {
        CreateCheckInDTO createDto = new CreateCheckInDTO();
        createDto.setCheckInTypeId(1L);
        createDto.setUserId(1L);

        when(checkInTypeRepository.findById(1L)).thenReturn(Optional.of(new CheckInType()));
        when(apiClient.employeeExists(1L, authorization)).thenReturn(false);
        when(checkInMapper.toEntity(createDto)).thenReturn(new CheckIn());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                checkInService.save(createDto, authorization));

        assertEquals("Employee does not exist or request failed", exception.getMessage());
    }

    @Test
    void testSave_CheckInTypeNotFound_ThrowsException() {
        CreateCheckInDTO createDto = new CreateCheckInDTO();
        createDto.setCheckInTypeId(99L);

        when(checkInTypeRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                checkInService.save(createDto, authorization));

        assertTrue(exception.getMessage().contains("CheckInType not found"));
    }

    @Test
    void testUpdateCheckIn_ReturnsUpdatedDTO() {
        Long id = 1L;
        UpdateCheckInDTO updateDto = new UpdateCheckInDTO();
        updateDto.setEndTime("17:00");

        CheckIn checkIn = new CheckIn();
        checkIn.setCheckInId(id);

        CheckInOutDTO dto = new CheckInOutDTO();

        when(checkInRepository.findById(id)).thenReturn(Optional.of(checkIn));
        when(checkInRepository.save(checkIn)).thenReturn(checkIn);
        when(checkInMapper.tCheckInOutDTO(checkIn)).thenReturn(dto);

        CheckInOutDTO result = checkInService.updateCheckIn(id, updateDto);

        assertNotNull(result);
        verify(checkInRepository).save(checkIn);
    }

    @Test
    void testUpdateCheckIn_NotFound_ReturnsNull() {
        when(checkInRepository.findById(1L)).thenReturn(Optional.empty());
        assertNull(checkInService.updateCheckIn(1L, new UpdateCheckInDTO()));
    }

}
