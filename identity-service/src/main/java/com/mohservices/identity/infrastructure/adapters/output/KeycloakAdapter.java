package com.mohservices.identity.infrastructure.adapters.output;

import com.mohservices.identity.application.ports.output.AuthOutputPort;
import com.mohservices.identity.domain.exceptions.IdentityServiceException;
import com.mohservices.identity.domain.models.User;
import com.mohservices.identity.infrastructure.adapters.input.dtos.AuthUser;
import com.mohservices.identity.infrastructure.adapters.input.dtos.Role;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.requests.AuthLoginRequest;
import com.mohservices.identity.infrastructure.adapters.input.rest.data.responses.LoginResponse;
import com.mohservices.identity.infrastructure.adapters.output.customizedExceptions.UserExistsException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class KeycloakAdapter implements AuthOutputPort {
    private final Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.admin.clientId}")
    private String client;

    @Value("${app.keycloak.admin.clientSecret}")
    private String secret;

    @Value("${app.keycloak.token.uri}")
    private String tokenUri;

    @Transactional
    @Override
    public Response createUser(User authUser) {
        UserRepresentation userRepresentation = mapFrom(authUser);
        List<CredentialRepresentation> credentials = getCredentialRepresentations(authUser);
        userRepresentation.setCredentials(credentials);
        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);


        try {
            String userId = usersResource.search(authUser.getEmail()).get(0).getId();
//            assignRole(userRepresentation.getEmail(), Role.USER);
//            sendVerificationEmail(userId);
        } catch (Exception e) {
            throw new IdentityServiceException(e.getMessage());
        }
        return response;

    }

    private UsersResource getUsersResource(){
        return keycloak.realm(realm).users();
    }

    public void sendVerificationEmail(String userId){
        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    public void deleteUser(String email){
        UsersResource usersResource = getUsersResource();
        String userId = usersResource.search(email).getFirst().getId();
        usersResource.delete(userId);
    }

    public void forgotPassword(String email){
        //PUT MAPPING and ignore the url
        UsersResource usersResource = getUsersResource();
        UserRepresentation userRepresentations = usersResource.searchByEmail(email, true).getFirst();
        UserResource userResource = usersResource.get(userRepresentations.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));


    }

    public void assignRole(String email, Role role){
        UserResource userResource = getUserResource(email);
        RolesResource rolesResource = getRolesResource();
        RoleRepresentation roleRepresentation = rolesResource.get(String.valueOf(role)).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
    }

    private UserResource getUserResource(String email){
        UsersResource usersResource = getUsersResource();
        String id = usersResource.search(email).getFirst().getId();
        return usersResource.get(id);
    }

    public AuthUser getUser(String email){
        UsersResource usersResource = getUsersResource();
        String id = usersResource.search(email).getFirst().getId();
        UserRepresentation representation = usersResource.get(id).toRepresentation();
        return AuthUser.builder()
                .firstName(representation.getFirstName())
                .lastName(representation.getLastName())
                .username(representation.getUsername())
                .email(representation.getEmail())
                .build();
    }

    public RolesResource getRolesResource(){
        return keycloak.realm(realm).roles();
    }

    public LoginResponse loginUser(AuthLoginRequest loginRequest) {
        RestTemplate template = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", client);
        params.add("client_secret", secret);
        params.add("username", loginRequest.getUsername());
        params.add("password", loginRequest.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<LoginResponse> response = template.postForEntity(tokenUri, request, LoginResponse.class);
            log.info("Response body: " + response.getBody());
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("Error during login: "+ex.getMessage());
            throw new IdentityServiceException("Authentication failed"+ ex);
        }
    }

    private static List<CredentialRepresentation> getCredentialRepresentations(User authUser) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(authUser.getPassword());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        List<CredentialRepresentation> credentials = new ArrayList<>();
        credentials.add(credentialRepresentation);
        return credentials;
    }

    private static UserRepresentation mapFrom(User authUser) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(authUser.getFirstName());
        userRepresentation.setLastName(authUser.getLastName());
        userRepresentation.setUsername(authUser.getEmail());
        userRepresentation.setEmail(authUser.getEmail());
        return userRepresentation;
    }
}
