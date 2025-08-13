package com.favourcode.fraud.service;



import com.favourcode.fraud.model.FraudCheckHistory;
import com.favourcode.fraud.producer.FraudProducerService;
import com.favourcode.fraud.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FraudService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    private final FraudProducerService fraudProducerService;


    public void isFraudulentCustomer (String customerPublicId, String email){

        FraudCheckHistory fraudCheck = FraudCheckHistory.builder()
                .isFraudster(true)
                .customerPublicId(customerPublicId)
                .email(email)
                .build();

        fraudCheckHistoryRepository.saveAndFlush(fraudCheck);
        fraudProducerService.publishUserFraudResult(fraudCheck.isFraudster(), fraudCheck.getCustomerPublicId(), fraudCheck.getEmail());

    }

}
