package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponse {

    private Long id;
    private int quantity;
    private int reorderLevel;
    private Long productId;

    public InventoryResponse() {}

    public InventoryResponse(Long id, int quantity,
                             int reorderLevel, Long productId) {
        this.id = id;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
        this.productId = productId;
    }
}
