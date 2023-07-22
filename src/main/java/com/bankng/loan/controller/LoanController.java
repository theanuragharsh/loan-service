package com.bankng.loan.controller;

import java.util.List;

import com.bankng.loan.model.Customer;
import com.bankng.loan.model.Loans;
import com.bankng.loan.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Anurag Harsh
 */

@RestController
public class LoanController {

    @Autowired
    private LoanRepo loanRepository;

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }

}
