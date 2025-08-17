package com.favourcode.customer.dto;


import org.jetbrains.annotations.NotNull;


public record CustomerRequest(@NotNull String firstname, @NotNull String lastname, @NotNull String email) {
}
