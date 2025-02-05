package com.example.cheapesttransferroute.service;

import com.example.cheapesttransferroute.model.Transfer;
import com.example.cheapesttransferroute.dto.TransferResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {

    public TransferResponse getSelectedTransfers(int maxWeight, List<Transfer> availableTransfers) {

        try {
            if (maxWeight < 0) { throw new IllegalArgumentException("maxWeight cannot be negative."); }
            if (availableTransfers == null || availableTransfers.isEmpty()) { throw new IllegalArgumentException("availableTransfers must not be null or empty."); }

            List<Transfer> selectedTransfers = new ArrayList<>();
            int n = availableTransfers.size();

            int maxCost = findBestCombination(n - 1, maxWeight, availableTransfers, selectedTransfers);
            int totalWeight = selectedTransfers.stream().mapToInt(Transfer::getWeight).sum();
            return new TransferResponse(selectedTransfers, maxCost, totalWeight);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate selected transfers: " + e.getMessage());
        }

    }

    private int findBestCombination(int index, int remainingWeight, List<Transfer> availableTransfers, List<Transfer> selectedTransfers) {

        if (index < 0 || remainingWeight <= 0) {
            return 0;
        }

        Transfer currentTransfer = availableTransfers.get(index);

        if (currentTransfer.getWeight() > remainingWeight) {
            return findBestCombination(index - 1, remainingWeight, availableTransfers, selectedTransfers);
        }

        List<Transfer> included = new ArrayList<>(selectedTransfers);
        included.add(currentTransfer);
        int includeCost = currentTransfer.getCost() + findBestCombination(index - 1, remainingWeight - currentTransfer.getWeight(), availableTransfers, included);

        List<Transfer> excluded = new ArrayList<>(selectedTransfers);
        int excludeCost = findBestCombination(index - 1, remainingWeight, availableTransfers, excluded);

        if (includeCost > excludeCost) {
            selectedTransfers.clear();
            selectedTransfers.addAll(included);
            return includeCost;
        } else {
            selectedTransfers.clear();
            selectedTransfers.addAll(excluded);
            return excludeCost;
        }
    }
}
