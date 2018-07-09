package com.ljd.hackajob.ratecalculator.model;

import static com.ljd.hackajob.ratecalculator.utils.TestUtils.RAND;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TestQuote {
    private BigDecimal loanAmount;
    private BigDecimal rate;
    private BigDecimal monthlyPayment;
    private BigDecimal totalRepayment;
    
    private Quote offer;
    
    @Before
    public void beforeEach() {
        loanAmount = BigDecimal.valueOf(RAND.nextLong()*1000.0).setScale(2, BigDecimal.ROUND_HALF_UP);
        rate = BigDecimal.valueOf(RAND.nextLong()*1000.0).setScale(3, BigDecimal.ROUND_HALF_UP);
        monthlyPayment = BigDecimal.valueOf(RAND.nextLong()*1000.0).setScale(2, BigDecimal.ROUND_HALF_UP);
        totalRepayment= BigDecimal.valueOf(RAND.nextLong()*1000.0).setScale(2, BigDecimal.ROUND_HALF_UP);
        
        offer = new Quote(loanAmount, rate, monthlyPayment, totalRepayment);
    }
    
    @Test
    public void testLoanAmount() {
        assertEquals(loanAmount, offer.getLoanAmount());
    }
    
    @Test
    public void testRate() {
        assertEquals(rate, offer.getRate());
    }
    
    @Test
    public void testMonthlyPayment() {
        assertEquals(monthlyPayment, offer.getMonthlyPayment());
    }
    
    @Test
    public void testTotalRepayment() {
        assertEquals(totalRepayment, offer.getTotalRepayment());
    }
}
