package com.ljd.hackajob.ratecalculator.model.exceptions;

public class ExceptionWrapper extends RuntimeException {
    private static final long serialVersionUID = -2525594160814749787L;

    public ExceptionWrapper(RateCalculatorException cause) {
        super(cause);
    }
    
    public RateCalculatorException getException() {
        return (RateCalculatorException)getCause();
    }
}
