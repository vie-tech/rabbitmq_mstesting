package com.favourcode.customer.dto;


import org.jetbrains.annotations.NotNull;


public record CustomerRequest(@NotNull String firstname, String lastname, @NotNull String email
                             /* @NotNull String password*/) {
}
