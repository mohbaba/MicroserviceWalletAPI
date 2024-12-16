package org.payment_services.wallet.infrastucture.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.payment_services.wallet.application.data.requests.UpdatePinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
class WalletRestAdapterTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getWallet() throws Exception {
        mockMvc.perform(get("/api/v1/wallet/e6d07fc3-5f50-4c6c-a9f1-1b7e43444744")
                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + response.getAccessToken())
        ).andExpect(status().isOk()).andDo(print());
    }


    @Test
    void getBalance()throws Exception {
        mockMvc.perform(get("/api/v1/wallet/balance/john.doe@example.com")
                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + response.getAccessToken())
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void testUpdatePassword()throws Exception{
        UpdatePinRequest updatePinRequest = new UpdatePinRequest();
        updatePinRequest.setOldPassword("hashedpassword1");
        updatePinRequest.setNewPassword("newpassword");
        mockMvc.perform(patch("/api/v1/wallet/updatePin/e6d07fc3-5f50-4c6c-a9f1-1b7e43444744")
//                .header("Authorization", "Bearer " + response.getAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatePinRequest))

        ).andExpect(status().isOk()).andDo(print());
    }@Test
    void testUpdatePasswordWithWrongPassword()throws Exception{
        UpdatePinRequest updatePinRequest = new UpdatePinRequest();
        updatePinRequest.setOldPassword("wrongpassword");
        updatePinRequest.setNewPassword("newpassword");
        mockMvc.perform(patch("/api/v1/wallet/updatePin/e6d07fc3-5f50-4c6c-a9f1-1b7e43444744")
//                .header("Authorization", "Bearer " + response.getAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatePinRequest))

        ).andExpect(status().is4xxClientError()).andDo(print());
    }



}