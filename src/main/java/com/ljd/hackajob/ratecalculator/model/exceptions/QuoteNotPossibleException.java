package com.ljd.hackajob.ratecalculator.model.exceptions;

public class QuoteNotPossibleException extends RateCalculatorException {
    private static final long serialVersionUID = -9198490744898050665L;

    @Override
    public String getExitMessage() {
        return "Sorry, it was not possible to provide a quote at this time.";
    }

    @Override
    public int getExitCode() {
        return ExitCodes.QUOTE_NOT_POSSIBLE;
    }

}
