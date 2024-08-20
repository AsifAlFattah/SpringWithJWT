package com.codeprophet.springwithjwt.dtos;


import com.codeprophet.springwithjwt.enums.UserRole;

public record SignUpDto(String login, String password, UserRole role) {



}
