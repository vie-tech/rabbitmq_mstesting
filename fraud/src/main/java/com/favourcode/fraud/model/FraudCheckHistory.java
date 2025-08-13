package com.favourcode.fraud.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fraud_check_history")
public class FraudCheckHistory {

    @jakarta.persistence.Id
    @SequenceGenerator(name = "fraud_id_sequence", sequenceName = "fraud_id_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "fraud_id_sequence")
    private long Id;
    private String customerPublicId;
    private boolean isFraudster;
    private String email;

}
