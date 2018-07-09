package com.ljd.hackajob.ratecalculator.model.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class TestInvalidLoanAmountException {

    public abstract int getExpectedExitCode();
    public abstract String getExpectedExitMessage();
    public abstract RateCalculatorException getException();
    
    @Test
    public void testExitCode() {
        assertEquals(getExpectedExitCode(), getException().getExitCode());
    }
    
    @Test
    public void testExitMessage() {
        assertEquals(getExpectedExitMessage(), getException().getExitMessage());
    }
}
