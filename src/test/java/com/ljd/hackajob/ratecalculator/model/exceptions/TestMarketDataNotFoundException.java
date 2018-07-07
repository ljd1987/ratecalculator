package com.ljd.hackajob.ratecalculator.model.exceptions;

public class TestMarketDataNotFoundException extends TestRateCalculatorException {

    private String filePath = "/some/file";
    
    @Override
    public int getExpectedExitCode() {
        return ExitCodes.MARKET_DATA_NOT_FOUND;
    }

    @Override
    public String getExpectedExitMessage() {
        return String.format("Couldn't load market data.  The file '%s' couldn't be found.", filePath);
    }

    @Override
    public RateCalculatorException getException() {
        return new MarketDataNotFoundException(filePath);
    }

}
