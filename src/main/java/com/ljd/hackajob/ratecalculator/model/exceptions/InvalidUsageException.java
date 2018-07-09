package com.ljd.hackajob.ratecalculator.model.exceptions;

public class InvalidUsageException extends RateCalculatorException {
    private static final long serialVersionUID = 1L;

    private final String usage;

    public InvalidUsageException(String usage) {
        this.usage = usage;
    }

    @Override
    public String getExitMessage() {
        return usage;
    }

    @Override
    public int getExitCode() {
        return ExitCodes.INVALID_USAGE;
    }

}
