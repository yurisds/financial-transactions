package com.technical.challenge.financetransactions.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionResource {


    @GetMapping
    public Integer createTransaction(){
        return 1;
    }
}
