package com.banking.loan.controller;

import com.banking.loan.config.LoanServiceConfig;
import com.banking.loan.model.Customer;
import com.banking.loan.model.Loans;
import com.banking.loan.model.Properties;
import com.banking.loan.repo.LoanRepo;
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
    private final LoanServiceConfig loanServiceConfig;

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loanServiceConfig.getMsg(), loanServiceConfig.getBuildVersion(), loanServiceConfig.getMailDetails(), loanServiceConfig.getActiveBranches());
        String jsonProperties = objectWriter.writeValueAsString(properties);
        return jsonProperties;
    }

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if (loans != null) {
            log.info("inside getLoanDetails : " + loans.toString());
            return loans;
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/{customerId}")
    public List<Loans> getLoansDetailsByCustomerId(@PathVariable Long customerId) {
        log.debug("inside loan-service : fetching loan details for customerId: {}", customerId);
        return List.of(loanRepository.findById(customerId).get());
    }

}
