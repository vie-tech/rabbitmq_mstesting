package com.favourcode.customer.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Customer {
    @jakarta.persistence.Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName =
            "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator =
            "customer_id_sequence")
    private long Id;
    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    private String status;

    @Column(unique = true, nullable = false)
    private String publicId;

    @PrePersist
    public void prePersist() {
        if (publicId == null) {
            publicId = UUID.randomUUID().toString();
        }


    }
}
