package com.greenfin.user.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUser {

    private String firstName;

    private String lastName;

    private String contactNo;

    private String emailId;

    private String password;
}
