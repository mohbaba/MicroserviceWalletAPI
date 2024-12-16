package org.payment_services.wallet.infrastucture.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import org.payment_services.wallet.application.data.responses.TransactionResponse;
import org.payment_services.wallet.application.ports.input.transactionusecases.GetAllTransactionsUseCase;
import org.payment_services.wallet.application.ports.input.transactionusecases.GetTransactionUseCase;
import org.payment_services.wallet.infrastucture.adapters.input.rest.mappers.TransactionRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionRestAdapter {
    private final GetTransactionUseCase getTransactionUseCase;
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;
    private final TransactionRestMapper transactionRestMapper;
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable("transactionId") String transactionId) {
        return new ResponseEntity<>(transactionRestMapper.toTransactionResponse(getTransactionUseCase.getTransaction(transactionId)), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(){
        return new ResponseEntity<>(transactionRestMapper.toTransactionResponseList(getAllTransactionsUseCase.getAllTransactions()), HttpStatus.OK);
    }

    @GetMapping("user/{email}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(transactionRestMapper.toTransactionResponseList(getAllTransactionsUseCase.getAllTransactionsFor(email)), HttpStatus.OK);
    }
}
