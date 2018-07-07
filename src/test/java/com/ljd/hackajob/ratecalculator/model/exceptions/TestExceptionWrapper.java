package com.ljd.hackajob.ratecalculator.model.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestExceptionWrapper {

    private RateCalculatorException cause;
    private ExceptionWrapper exceptionWrapper;
    
    @Before
    public void beforeEach() {
        cause = new MarketDataLoadingException("foo");
        exceptionWrapper = new ExceptionWrapper(cause);
    }
    
    @Test
    public void testGetException() {
        assertEquals(cause, exceptionWrapper.getException());
    }
    
}
