package com.technical.challenge.financialtransactions.resource;

import com.technical.challenge.financialtransactions.model.Statement;
import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.resource.response.StatementResponse;
import com.technical.challenge.financialtransactions.service.StatementServiceImpl;
import com.technical.challenge.financialtransactions.service.StatementService;
import com.technical.challenge.financialtransactions.service.StatementTransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/statement")
public class StatementResource {

    private final StatementService statementService;
    private final StatementTransactionService statementTransactionService;


    public StatementResource(StatementServiceImpl statementService,
                             StatementTransactionService statementTransactionService) {
        this.statementService = statementService;
        this.statementTransactionService = statementTransactionService;
    }

    @GetMapping("/{transactionId}")
    public Statement findStatementByTransactionId(@PathVariable("transactionId") String transactionId) {
        return statementService.findStatementByTransactionId(transactionId);
    }

    @GetMapping("/transaction/{transactionId}")
    public StatementResponse getStatementWithTransaction(@PathVariable("transactionId") String transactionId) {
        return statementTransactionService.getStatementWithTransaction(transactionId);
    }

    @GetMapping()
    public List<Statement> getAllStatement(@RequestParam(value = "paymentDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paymentDate,
                                                          @RequestParam(value = "status", required = false) Status status) {
        return statementService.findStatementsByCriteria(paymentDate, status);
    }

    @GetMapping("/transaction")
    public List<StatementResponse> getAllStatementWithTransaction(@RequestParam(value = "paymentDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paymentDate,
                                                          @RequestParam(value = "status", required = false) Status status) {
        return statementTransactionService.getAllStatementWithTransaction(paymentDate, status);
    }
}
