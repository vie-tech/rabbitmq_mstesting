package com.favourcode.customer.service;


import com.favourcode.customer.dto.CustomerRequest;
import com.favourcode.customer.dto.OrderPlacementPayload;
import com.favourcode.customer.model.Customer;
import com.favourcode.customer.producer.CustomerProducerService;
import com.favourcode.customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerProducerService customerProducerService;

    @Transactional
    public void registerCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .status("active")
                .build();

        customerRepository.saveAndFlush(customer);
        customerProducerService.publishUserCreatedEvent(customer);

    }

    @Transactional
    public void banFraudulentUser(String publicId) {
        customerRepository.findByPublicId(publicId).ifPresentOrElse(
                user -> {
                    user.setStatus("blocked");
                    log.info("blocked user with mail {}", user.getEmail());
                    customerProducerService.publishUserBlockedNotification(user.getEmail());
                },
                () -> {
                    //Create an event to Add in backlog for retry logic
                    throw new EntityNotFoundException("User not found public " +
                            "id: " + publicId);
                });


    }

    public void processCreatedOrder(OrderPlacementPayload request){
        customerRepository.findByPublicId(request.userPublicId()).ifPresentOrElse(
                (user)->{
                    customerProducerService.publishOrderPlacedEvent(request);
                },

                ()->{
                    throw new IllegalStateException("Could not find user");
                }
        );
    }
}
