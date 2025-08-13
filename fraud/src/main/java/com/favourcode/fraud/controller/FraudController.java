package com.favourcode.fraud.controller;


import com.favourcode.fraud.dto.FraudCheckResponse;
import com.favourcode.fraud.service.FraudService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/fraud")
public class FraudController {

    private final FraudService fraudService;

//    @GetMapping("/fraud_check/{customerId}")
//    public FraudCheckResponse isCustomerFraudster(@PathVariable(name = "customerId") String customerId){
//        boolean isFraudulentCustomer = fraudService.isFraudulentCustomer(customerId);
//        return new FraudCheckResponse(isFraudulentCustomer);
//    }
}
