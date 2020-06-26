package com.java.userproject.service;

import com.java.userproject.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailFallback implements EmailService {
    @Override
    public Email getEmailByUserId(Long id) {
        return new Email("Hystrix@thoughtworks.com");
    }
}
