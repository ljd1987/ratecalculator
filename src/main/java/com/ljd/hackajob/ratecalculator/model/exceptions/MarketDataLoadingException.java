package com.ljd.hackajob.ratecalculator.model.exceptions;

public class MarketDataLoadingException extends RateCalculatorException {
    private static final long serialVersionUID = -1298505674015323993L;

    private final String error;

    public MarketDataLoadingException(String error) {
        this.error = error;
    }

    @Override
    public String getExitMessage() {
        return String.format("Couldn't load market data.  There was an error: %s.", error);
    }

    @Override
    public int getExitCode() {
        return ExitCodes.MARKET_DATA_LOAD_ERROR;
    }
}
