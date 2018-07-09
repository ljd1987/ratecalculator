package com.ljd.hackajob.ratecalculator.model.exceptions;

/**
 * 
 * @author leodavison
 *
 */
public class ExitCodes {
    private ExitCodes() {
        // not meant to be instantiated.
    }

    public static final int SUCCESS                 = 0;
    public static final int MARKET_DATA_NOT_FOUND   = 1;  
    public static final int MARKET_DATA_LOAD_ERROR  = 2;
    public static final int QUOTE_NOT_POSSIBLE      = 3;
    public static final int INVALID_USAGE           = 4;
    public static final int INVALID_LOAN_AMOUNT     = 5;
}
