package com.example.cheapesttransferroute.model;

public class Transfer {

    private final int weight;
    private final int cost;

    public Transfer(int weight, int cost) {

        if (weight < 0 || cost < 0) { throw new IllegalArgumentException("Weight and cost cannot be negative"); }

        this.weight = weight;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }
}
