package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {
    private int quantity;
    private int reorderLevel;
    private Long productId;

    public InventoryRequest() {
    }

    public InventoryRequest(int quantity, int reorderLevel, Long productId) {
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.productId = productId;
    }
}
