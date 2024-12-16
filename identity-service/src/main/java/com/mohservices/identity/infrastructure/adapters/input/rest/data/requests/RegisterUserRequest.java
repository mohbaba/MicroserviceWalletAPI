package com.mohservices.identity.infrastructure.adapters.input.rest.data.requests;

import com.mohservices.identity.infrastructure.adapters.input.dtos.Role;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
