package com.favourcode.customer.dto;

public record FraudCheckResultPayload(String userId, boolean isFraudulent, String email) {
}
