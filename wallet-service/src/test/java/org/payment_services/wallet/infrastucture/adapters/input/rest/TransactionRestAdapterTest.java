package org.payment_services.wallet.infrastucture.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
class TransactionRestAdapterTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getTransaction() throws Exception {
        mockMvc.perform(get("/api/v1/transactions/b0d4a4e2-c2c7-41ec-b8f6-d3ae48fce1f0")
                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + response.getAccessToken())
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getAllTransactions() throws Exception {
        mockMvc.perform(get("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + response.getAccessToken())
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getTransactionsByEmail() throws Exception {
        mockMvc.perform(get("/api/v1/transactions/user/john.doe@example.com")
                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + response.getAccessToken())
        ).andExpect(status().isOk()).andDo(print());
    }
}