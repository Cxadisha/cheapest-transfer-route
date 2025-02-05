package com.example.cheapesttransferroute.dto;

import com.example.cheapesttransferroute.model.Transfer;

import java.util.List;

public class TransferRequest {

    private final int maxWeight;
    private final List<Transfer> availableTransfers;

    public TransferRequest(int maxWeight, List<Transfer> availableTransfers) {

        if (maxWeight < 0) {
            throw new IllegalArgumentException("maxWeight cannot be negative.");
        }
        if (availableTransfers == null || availableTransfers.isEmpty()) {
            throw new IllegalArgumentException("availableTransfers must not be null or empty.");
        }

        this.maxWeight = maxWeight;
        this.availableTransfers = availableTransfers;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Transfer> getAvailableTransfers() {
        return availableTransfers;
    }
}
