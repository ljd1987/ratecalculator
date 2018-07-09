package com.ljd.hackajob.ratecalculator.model.exceptions;

public class LoanInputInvalidException extends RateCalculatorException {
    private static final long serialVersionUID = -5858192175034575655L;

    private final String input;

    public LoanInputInvalidException(String input) {
        this.input = input;
    }

    @Override
    public String getExitMessage() {
        return String.format("The loanAmount [%s] is not valid.", input);
    }

    @Override
    public int getExitCode() {
        return ExitCodes.INVALID_LOAN_INPUT;
    }

}
