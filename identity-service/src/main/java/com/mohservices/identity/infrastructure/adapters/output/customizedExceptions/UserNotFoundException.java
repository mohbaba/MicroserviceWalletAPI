package com.mohservices.identity.infrastructure.adapters.output.customizedExceptions;

import com.mohservices.identity.domain.exceptions.IdentityServiceException;

public class UserNotFoundException extends IdentityServiceException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
