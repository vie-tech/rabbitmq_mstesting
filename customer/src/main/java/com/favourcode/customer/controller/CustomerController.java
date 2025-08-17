package com.favourcode.customer.controller;
import com.favourcode.customer.dto.CustomerRequest;
import com.favourcode.customer.dto.OrderPlacementPayload;
import com.favourcode.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerRequest request){
        customerService.registerCustomer(request);
        return ResponseEntity.ok(Map.of("success", true));
    }

    @PostMapping("/place_order")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderPlacementPayload request){
        customerService.processCreatedOrder(request);
        return ResponseEntity.ok(Map.of("success", true, "message", "order placed successfully"));
    }

}
