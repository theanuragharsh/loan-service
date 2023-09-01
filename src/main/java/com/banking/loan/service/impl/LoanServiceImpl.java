package com.banking.loan.service.impl;

import com.banking.loan.model.Loans;
import com.banking.loan.repo.LoanRepo;
import com.banking.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepo loanRepository;

    @Override
    public List<Loans> getLoansDetailsByCustomerId(String correlationId, Long customerId) {
        log.debug("correlation-id : {}", correlationId);
        log.debug("inside loan-service : fetching loan details for customerId: {}", customerId);
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }
}
