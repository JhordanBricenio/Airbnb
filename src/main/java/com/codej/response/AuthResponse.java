package com.codej.response;

import lombok.Data;

@Data
public class AuthResponse {
    String jwt;
    boolean status;
    String message;
}
