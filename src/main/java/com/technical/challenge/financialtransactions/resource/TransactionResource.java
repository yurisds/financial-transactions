package com.technical.challenge.financialtransactions.resource;

import com.technical.challenge.financialtransactions.mapper.TransactionMapper;
import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;
import com.technical.challenge.financialtransactions.resource.response.TransactionResponse;
import com.technical.challenge.financialtransactions.service.TransactionService;
import com.technical.challenge.financialtransactions.service.impl.TransactionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {

    private final TransactionService transactionService;

    public TransactionResource(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/filter")
    public List<TransactionResponse> findAllByCriteria( @RequestParam(required = false) String description,
                                                @RequestParam(required = false) PaymentMethod paymentMethod,
                                                @RequestParam(required = false) String customerName){
        return transactionService.findTransactionsByCriteria(description, paymentMethod, customerName);
    }

    @GetMapping("/{transactionId}")
    public TransactionResponse findByTransactionId(@PathVariable("transactionId") String transactionId) {
        return TransactionMapper.toTransactionResponse(transactionService.findByTransactionId(transactionId));
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody @Valid TransactionRequest transaction) {
        return TransactionMapper.toTransactionResponse(transactionService.createTransaction(transaction));
    }

}
