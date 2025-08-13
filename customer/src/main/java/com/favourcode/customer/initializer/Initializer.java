package com.favourcode.customer.initializer;

import com.favourcode.customer.model.Customer;
import com.favourcode.customer.repository.CustomerRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class Initializer implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final static Faker FAKER = new Faker();

    @Override
    public void run(String... args) throws Exception {
        if(customerRepository.count() ==0){
            createUsers();
        }
    }

    public void createUsers() {
        List<Customer> users = new ArrayList<>(1500);
        for (int i = 0; i < 1000; i++) {
            Customer user = Customer.builder()
                    .firstname(FAKER.name().firstName())
                    .lastname(FAKER.name().lastName())
                    .email(FAKER.internet().emailAddress())
                    .status("active")
                    .build();
            users.add(user);
        }

        customerRepository.saveAll(users);
    }
}
