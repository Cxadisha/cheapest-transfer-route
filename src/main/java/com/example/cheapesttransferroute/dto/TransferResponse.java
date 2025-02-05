package com.example.cheapesttransferroute.dto;

import com.example.cheapesttransferroute.model.Transfer;

import java.util.List;

public class TransferResponse {

    private final List<Transfer> selectedTransfers;
    private final int totalCost;
    private final int totalWeight;

    public TransferResponse(List<Transfer> selectedTransfers, int totalCost, int totalWeight) {

        if (totalCost < 0 || totalWeight < 0) {
            throw new IllegalArgumentException("totalCost and totalWeight must be non-negative values.");
        }

        this.selectedTransfers = selectedTransfers;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
    }

    public List<Transfer> getSelectedTransfers() {
        return selectedTransfers;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}
