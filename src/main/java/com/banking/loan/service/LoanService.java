package com.banking.loan.service;

import com.banking.loan.model.Loans;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LoanService {
    List<Loans> getLoansDetailsByCustomerId(String correlationId, @PathVariable Long customerId);

}
