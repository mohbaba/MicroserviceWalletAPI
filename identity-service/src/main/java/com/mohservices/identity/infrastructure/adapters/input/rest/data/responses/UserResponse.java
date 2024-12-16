package com.mohservices.identity.infrastructure.adapters.input.rest.data.responses;

import com.mohservices.identity.infrastructure.adapters.input.dtos.Role;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
