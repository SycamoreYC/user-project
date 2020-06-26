package com.java.userproject.service;

import com.java.userproject.model.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "email-center", fallback = EmailFallback.class)
public interface EmailService {
    @GetMapping("/emails/{id}")
    Email getEmailByUserId(@PathVariable("id") Long id);
}
