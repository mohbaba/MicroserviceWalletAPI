package com.mohservices.identity.domain.models;

import com.mohservices.identity.infrastructure.adapters.input.dtos.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String phoneNumber;
    private String lastName;
    private Role role;
}
