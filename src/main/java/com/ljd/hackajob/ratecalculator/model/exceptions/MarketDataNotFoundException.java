package com.ljd.hackajob.ratecalculator.model.exceptions;

public class MarketDataNotFoundException extends RateCalculatorException {
    private static final long serialVersionUID = -1298505674015323993L;

    private final String filePath;

    public MarketDataNotFoundException(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getExitMessage() {
        return String.format("Couldn't load market data.  The file '%s' couldn't be found.", filePath);
    }

    @Override
    public int getExitCode() {
        return ExitCodes.MARKET_DATA_NOT_FOUND;
    }
}
