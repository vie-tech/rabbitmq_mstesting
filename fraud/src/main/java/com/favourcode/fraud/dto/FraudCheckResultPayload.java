package com.favourcode.fraud.dto;

public record FraudCheckResultPayload(String userId, boolean isFraudulent, String email) {
}
