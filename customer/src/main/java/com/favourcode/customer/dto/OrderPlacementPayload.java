package com.favourcode.customer.dto;

import jakarta.validation.constraints.NotNull;

public record OrderPlacementPayload(@NotNull String userPublicId,
                                    @NotNull String productId,
                                    @NotNull int amountPaid,
                                    @NotNull int quantity) {
}
