package com.example.check_in.management.services;

import com.example.check_in.management.dto.CheckInTypeDTO;
import com.example.check_in.management.mapper.CheckInTypeMapper;
import com.example.check_in.management.models.CheckInType;
import com.example.check_in.management.repositories.CheckInTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckInTypeServiceTest {

    @InjectMocks
    private CheckInTypeService checkInTypeService;

    @Mock
    private CheckInTypeRepository checkInTypeRepository;

    @Mock
    private CheckInTypeMapper checkInTypeMapper;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_ReturnsDTOList() {
        // Arrange
        CheckInType entity = new CheckInType();
        CheckInTypeDTO dto = new CheckInTypeDTO();

        when(checkInTypeRepository.findAll()).thenReturn(List.of(entity));
        when(checkInTypeMapper.toCheckInTypeDTO(entity)).thenReturn(dto);

        // Act
        List<CheckInTypeDTO> result = checkInTypeService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(checkInTypeRepository).findAll();
        verify(checkInTypeMapper).toCheckInTypeDTO(entity);
    }
}
