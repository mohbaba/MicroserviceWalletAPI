package com.mohservices.identity.infrastructure.adapters.input.rest.data.requests;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthLoginRequest {
    private String username;
    private String password;
    private String grantType;
    private String clientId;

}
