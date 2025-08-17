package com.favourcode.order.dto;

public record OrderPlacementPayload(String userPublicId, String productId, int amountPaid, int quantity) {
}
