package com.mohservices.identity.infrastructure.adapters.output.customizedExceptions;

import com.mohservices.identity.domain.exceptions.IdentityServiceException;

public class UserExistsException extends IdentityServiceException {
    public UserExistsException(String message) {
        super(message);
    }
}
