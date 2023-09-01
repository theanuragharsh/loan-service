package com.banking.loan.controller;

import com.banking.loan.config.LoanServiceConfig;
import com.banking.loan.model.Customer;
import com.banking.loan.model.Loans;
import com.banking.loan.model.Properties;
import com.banking.loan.repo.LoanRepo;
import com.banking.loan.service.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Collections;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Anurag Harsh
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan-service")
public class LoanController {

    private final LoanRepo loanRepository;
    private final LoanService loanService;
    private final LoanServiceConfig loanServiceConfig;

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loanServiceConfig.getMsg(), loanServiceConfig.getBuildVersion(), loanServiceConfig.getMailDetails(), loanServiceConfig.getActiveBranches());
        String jsonProperties = objectWriter.writeValueAsString(properties);
        return jsonProperties;
    }

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestHeader("correlation-id") String correlationId, @RequestBody Customer customer) {
        log.debug("correlation-id : {}", correlationId);
        log.info("inside loan service..");
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if (loans != null) {
            log.info("inside getLoanDetails : " + loans.toString());
            return loans;
        } else {
            return Collections.emptyList();
        }
    }

    //    This method is being used by customer-service
    @GetMapping("/{customerId}")
    public List<Loans> getLoansDetailsByCustomerId(@RequestHeader("correlation-id") String correlationId, @PathVariable Long customerId) {
        return loanService.getLoansDetailsByCustomerId(correlationId,customerId);
    }

}
