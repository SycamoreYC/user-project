package com.java.userproject.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {

    private LocalDateTime timestamp;

    private String error;

    private Integer status;

    private String message;
}
