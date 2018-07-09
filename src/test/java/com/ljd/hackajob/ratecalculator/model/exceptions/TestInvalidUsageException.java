package com.ljd.hackajob.ratecalculator.model.exceptions;

import com.ljd.hackajob.ratecalculator.RateCalculator;

public class TestInvalidUsageException extends TestRateCalculatorException {

    @Override
    public int getExpectedExitCode() {
        return ExitCodes.INVALID_USAGE;
    }

    @Override
    public String getExpectedExitMessage() {
        return RateCalculator.USAGE;
    }

    @Override
    public RateCalculatorException getException() {
        return new InvalidUsageException(RateCalculator.USAGE);
    }

}
