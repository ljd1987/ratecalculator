package com.ljd.hackajob.ratecalculator.model.exceptions;

import java.math.BigDecimal;

public class TestInvalidLoanAmount extends TestRateCalculatorException {

    private final BigDecimal loanRequest = BigDecimal.valueOf(50);
    private final BigDecimal minLoanAmount = BigDecimal.valueOf(100);
    private final BigDecimal maxLoanAmount = BigDecimal.valueOf(1000);
    private final BigDecimal loanIncrements = BigDecimal.valueOf(100);
    
    @Override
    public int getExpectedExitCode() {
        return ExitCodes.INVALID_LOAN_AMOUNT;
    }

    @Override
    public String getExpectedExitMessage() {
        return String.format("The loan amount of £%s is not supported.  Loans must be between £%s and £%s in increments of £%s", loanRequest, minLoanAmount, maxLoanAmount, loanIncrements);
    }

    @Override
    public RateCalculatorException getException() {
        return new InvalidLoanAmount(loanRequest, minLoanAmount, maxLoanAmount, loanIncrements);
    }

}
