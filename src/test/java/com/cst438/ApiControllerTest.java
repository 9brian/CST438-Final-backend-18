package com.cst438;

import com.cst438.Domain.FlightRepository;
import com.cst438.Domain.SegmentRepository;
import com.cst438.controller.AmadeusAPIClient;
import com.cst438.controller.ApiController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ApiControllerTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private SegmentRepository segmentRepository;

    @InjectMocks
    private ApiController apiController;

    public ApiControllerTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFlights(){
        when(AmadeusAPIClient.flightOffers(anyString())).thenReturn("{\"data\": []}");

        apiController.flights("OG", "DEST", "2023-12-01", "2", "1000");

        verify(segmentRepository, times(0)).save(any());

        verify(segmentRepository, times(0)).save(any());
        verify(flightRepository, times(0)).save(any());
    }


}
