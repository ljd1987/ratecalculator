package com.ljd.hackajob.ratecalculator.model.exceptions;

public class TestMarketDataLoadingException extends TestRateCalculatorException {

    private String error = "bad data";
    
    @Override
    public int getExpectedExitCode() {
        return ExitCodes.MARKET_DATA_LOAD_ERROR;
    }

    @Override
    public String getExpectedExitMessage() {
        return String.format("Couldn't load market data.  There was an error: %s.", error);
    }

    @Override
    public RateCalculatorException getException() {
        return new MarketDataLoadingException(error);
    }

}
