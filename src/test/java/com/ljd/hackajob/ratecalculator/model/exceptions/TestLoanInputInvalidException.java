package com.ljd.hackajob.ratecalculator.model.exceptions;

public class TestLoanInputInvalidException extends TestRateCalculatorException {

    private String input = "foobar";

    @Override
    public int getExpectedExitCode() {
        return ExitCodes.INVALID_LOAN_INPUT;
    }

    @Override
    public String getExpectedExitMessage() {
        return String.format("The loanAmount [%s] is not valid.", input);
    }

    @Override
    public RateCalculatorException getException() {
        return new LoanInputInvalidException(input);
    }

}
