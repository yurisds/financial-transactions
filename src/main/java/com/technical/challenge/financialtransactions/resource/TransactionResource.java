package com.technical.challenge.financialtransactions.resource;

import com.technical.challenge.financialtransactions.model.PaymentMethod;
import com.technical.challenge.financialtransactions.model.Transaction;
import com.technical.challenge.financialtransactions.resource.request.TransactionRequest;
import com.technical.challenge.financialtransactions.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {

    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/filter")
    public List<Transaction> findAllByCriteria( @RequestParam(required = false) String description,
                                                @RequestParam(required = false) PaymentMethod paymentMethod,
                                                @RequestParam(required = false) String customerName){
        return transactionService.findTransactionsByCriteria(description, paymentMethod, customerName);
    }

    @GetMapping("/{transactionId}")
    public Transaction findAllByCriteria( @PathVariable("transactionId") String transactionId) {
        return transactionService.findByTransactionId(transactionId);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionRequest transaction) {
        return transactionService.createTransaction(transaction);
    }
}
