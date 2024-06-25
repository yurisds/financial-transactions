package com.technical.challenge.financialtransactions.job;

import com.technical.challenge.financialtransactions.model.Status;
import com.technical.challenge.financialtransactions.service.StatementService;
import com.technical.challenge.financialtransactions.service.StatementServiceImpl;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/statement")
public class StatementJob {

    private final StatementService statementService;

    public StatementJob(StatementServiceImpl statementService) {
        this.statementService = statementService;
    }

    @PutMapping("/job")
    public void getAllStatementWithTransaction() {
        statementService.updateStatusByCriteria(LocalDate.now(), Status.WAITING_FUNDS);
    }
}
