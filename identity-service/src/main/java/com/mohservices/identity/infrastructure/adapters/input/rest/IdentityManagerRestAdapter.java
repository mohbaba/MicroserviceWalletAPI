package com.mohservices.identity.infrastructure.adapters.input.rest;

import com.mohservices.identity.application.ports.input.identity_manager_usecases.*;
import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.requests.AuthLoginRequest;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.requests.RegisterUserRequest;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.responses.UserResponse;
import com.mohservices.identity.infrastructure.adapters.input.rest.mappers.UserRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class IdentityManagerRestAdapter {
    private final UserRestMapper userRestMapper;
    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final AssignRoleUseCase assignRoleUseCase;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request){
        User user = userRestMapper.toUser(request);
        user = registerUserUseCase.createUser(user);
        return new ResponseEntity<>(userRestMapper.toUserResponse(user), HttpStatus.CREATED);

    }


    @GetMapping("/get/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email){
        UserResponse user = userRestMapper.toUserResponse(getUserUseCase.getUser(email));
        return new ResponseEntity<>(user, HttpStatus.FOUND);

    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        deleteUserUseCase.deleteUser(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@RequestParam String email){
        forgotPasswordUseCase.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthLoginRequest loginRequest){
        return new ResponseEntity<>(loginUserUseCase.loginUser(loginRequest), HttpStatus.OK);
    }
}
