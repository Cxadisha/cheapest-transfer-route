package com.example.cheapesttransferroute.service;

import com.example.cheapesttransferroute.model.Transfer;
import com.example.cheapesttransferroute.dto.TransferResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransferServiceTest {

    @InjectMocks
    private TransferService transferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSelectedTransfersBasicCase() {
        int maxWeight = 15;
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(8, 15)
        );

        TransferResponse response = transferService.getSelectedTransfers(maxWeight, transfers);

        assertNotNull(response);
        assertEquals(2, response.getSelectedTransfers().size());
        assertEquals(30, response.getTotalCost());
        assertEquals(15, response.getTotalWeight());
    }

    @Test
    void testGetSelectedTransfersTooHeavy() {
        List<Transfer> transfers = List.of(
                new Transfer(16, 20),
                new Transfer(17, 25)
        );

        TransferResponse response = transferService.getSelectedTransfers(15, transfers);

        assertTrue(response.getSelectedTransfers().isEmpty());
        assertEquals(0, response.getTotalCost());
        assertEquals(0, response.getTotalWeight());
    }

    @Test
    void testGetSelectedTransfersSingleFit() {
        List<Transfer> transfers = List.of(
                new Transfer(6, 10),
                new Transfer(7, 15),
                new Transfer(8, 20)
        );

        TransferResponse response = transferService.getSelectedTransfers(7, transfers);

        assertEquals(1, response.getSelectedTransfers().size());
        assertEquals(15, response.getTotalCost());
        assertEquals(7, response.getTotalWeight());
    }

    @Test
    void testGetSelectedTransfersEmpty() {
        int maxWeight = 10;
        List<Transfer> transfers = List.of();

        Exception exception = assertThrows(RuntimeException.class, () -> transferService.getSelectedTransfers(maxWeight, transfers));
        assertEquals("Failed to calculate selected transfers: availableTransfers must not be null or empty.", exception.getMessage());
    }

    @Test
    void testGetSelectedTransfersNull() {
        int maxWeight = 10;
        List<Transfer> transfers = null;

        Exception exception = assertThrows(RuntimeException.class, () -> transferService.getSelectedTransfers(maxWeight, transfers));
        assertEquals("Failed to calculate selected transfers: availableTransfers must not be null or empty.", exception.getMessage());
    }

    @Test
    void testGetSelectedTransferNegativeWeight() {
        int maxWeight = -10;
        List<Transfer> transfers = List.of(
                new Transfer(6, 10),
                new Transfer(7, 15),
                new Transfer(8, 20)
        );

        Exception exception = assertThrows(RuntimeException.class, () -> transferService.getSelectedTransfers(maxWeight, transfers));
        assertEquals("Failed to calculate selected transfers: maxWeight cannot be negative.", exception.getMessage());
    }

}
