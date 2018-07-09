package com.ljd.hackajob.ratecalculator.model.exceptions;

public class TestQuoteNotPossibleException extends TestRateCalculatorException {

    @Override
    public int getExpectedExitCode() {
        return ExitCodes.QUOTE_NOT_POSSIBLE;
    }

    @Override
    public String getExpectedExitMessage() {
        return "Sorry, it was not possible to provide a quote at this time.";
    }

    @Override
    public RateCalculatorException getException() {
        return new QuoteNotPossibleException();
    }

}
