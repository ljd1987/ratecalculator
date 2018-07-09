package com.ljd.hackajob.ratecalculator.model.exceptions;

import java.math.BigDecimal;

public class InvalidLoanAmount extends RateCalculatorException {
    private static final long serialVersionUID = -4354806895712661464L;

    private final BigDecimal loanRequest;
    private final BigDecimal minLoanAmount;
    private final BigDecimal maxLoanAmount;
    private final BigDecimal loanIncrements;

    public InvalidLoanAmount(BigDecimal loanRequest, BigDecimal minLoanAmount, BigDecimal maxLoanAmount, BigDecimal loanIncrements) {
        this.loanRequest = loanRequest;
        this.minLoanAmount = minLoanAmount;
        this.maxLoanAmount = maxLoanAmount;
        this.loanIncrements = loanIncrements;
    }

    @Override
    public String getExitMessage() {
        return String.format("The loan amount of £%s is not supported.  Loans must be between £%s and £%s in increments of £%s", loanRequest, minLoanAmount, maxLoanAmount, loanIncrements);
    }
    @Override
    public int getExitCode() {
        return ExitCodes.INVALID_LOAN_AMOUNT;
    }

}
