package com.ljd.hackajob.ratecalculator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ljd.hackajob.ratecalculator.model.LenderOffer;
import com.ljd.hackajob.ratecalculator.model.Quote;
import com.ljd.hackajob.ratecalculator.model.ModelLoader;
import com.ljd.hackajob.ratecalculator.model.exceptions.QuoteNotPossibleException;
import com.ljd.hackajob.ratecalculator.model.exceptions.RateCalculatorException;

public class QuoteBuilderTest {

    private static List<LenderOffer> marketData;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @BeforeClass
    public static void beforeAll() throws RateCalculatorException {
        String path = QuoteBuilderTest.class.getClassLoader().getResource("lenderdata.csv").getPath();
        marketData = ModelLoader.loadMarketModelFromCSV(path);
    }

    /**
     * All tests use the src/test/resources/lenderdata.csv
     * 
     * Lender  Rate    Available   Monthly Total Repayable
     * Jane    0.069   480         14.8    532.80
     * Fred    0.071   520         16.08   578.88
     * Angela  0.071   60          1.86    66.96
     * Dave    0.074   140         4.35    156.60
     * Bob     0.075   640         19.91   716.76
     * John    0.081   320         10.04   361.44
     * Mary    0.104   170         5.52    198.72
     */

    /**
     * Simplest test case is where a loan can be fully satisfied by the cheapest
     * lender, and the requested amount exactly matches the available amount
     * for that lender.
     * 
     * Eg:
     * 
     * Cheapest lender: Jane - 480 @ 6.9%
     * Loan request   : 480
     * term           : 36 months
     * 
     * P = A / D
     * 
     * 
     * n = 36           (36 monthly payments)
     * i = 0.00575      (rate / 12)
     * D = 32.43        (((1+i) ^ n) -1 ) / (i * (1+i) ^ n)
     * 
     * P = 14.80        (A / D)
     * 
     * Result:
     * 
     * rate :   6.9%
     * monthly: 14.80
     * total:   532.80
     * @throws QuoteNotPossibleException 
     * 
     */
    @Test
    public void singleLenderTest() throws QuoteNotPossibleException {
        double loanAmount = 480;
        Quote actualOffer = Quote.getQuote(loanAmount, RateCalculator.DEFAULT_TERM_36_MONTHS, marketData);
        assertEquals(BigDecimal.valueOf(loanAmount).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getLoanAmount());
        assertEquals(BigDecimal.valueOf(0.069).setScale(3, BigDecimal.ROUND_HALF_UP), actualOffer.getRate());
        assertEquals(BigDecimal.valueOf(14.80).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getMonthlyPayment());
        assertEquals(BigDecimal.valueOf(532.80).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getTotalRepayment());
    }
    
    @Test
    public void singlePartialLenderTest() throws QuoteNotPossibleException {
        double loanAmount = 230;
        Quote actualOffer = Quote.getQuote(loanAmount, RateCalculator.DEFAULT_TERM_36_MONTHS, marketData);
        assertEquals(BigDecimal.valueOf(loanAmount).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getLoanAmount());
        assertEquals(BigDecimal.valueOf(0.069).setScale(3, BigDecimal.ROUND_HALF_UP), actualOffer.getRate());
        assertEquals(BigDecimal.valueOf(7.09).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getMonthlyPayment());
        assertEquals(BigDecimal.valueOf(255.24).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getTotalRepayment());
    }

    /**
     * Next case is where a loan can be fully satisfied by the two cheapest lenders
     * 
     * Lender   Rate    Available   i               d       p
     *  Jane    0.069   480         0.005750000     £32.43  £14.80
     *  Fred    0.071   520         0.005916667     £32.34  £16.08
     *  
     *  loan amount = 1000
     *  
     *  48% satisfied by the first lender
     *  53$ satisfied by the second lender
     *  
     * rate :   7.0%
     * monthly: 30.88
     * total:   1111.68
     * @throws QuoteNotPossibleException 
     *  
     */
    @Test
    public void twoFullLenders() throws QuoteNotPossibleException { 
        double loanAmount = 1000;
        Quote actualOffer = Quote.getQuote(loanAmount, RateCalculator.DEFAULT_TERM_36_MONTHS, marketData);
        assertEquals(BigDecimal.valueOf(loanAmount).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getLoanAmount());
        assertEquals(BigDecimal.valueOf(0.070).setScale(3, BigDecimal.ROUND_HALF_UP), actualOffer.getRate());
        assertEquals(BigDecimal.valueOf(30.88).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getMonthlyPayment());
        assertEquals(BigDecimal.valueOf(1111.68).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getTotalRepayment());
    }
    
    @Test
    public void multilender() throws QuoteNotPossibleException {
        double loanAmount = 1300;
        Quote actualOffer = Quote.getQuote(loanAmount, RateCalculator.DEFAULT_TERM_36_MONTHS, marketData);
        assertEquals(BigDecimal.valueOf(loanAmount).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getLoanAmount());
        assertEquals(BigDecimal.valueOf(0.072).setScale(3, BigDecimal.ROUND_HALF_UP), actualOffer.getRate());
        assertEquals(BigDecimal.valueOf(40.20).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getMonthlyPayment());
        assertEquals(BigDecimal.valueOf(1447.20).setScale(2, BigDecimal.ROUND_HALF_UP), actualOffer.getTotalRepayment());
    }
    
    @Test
    public void testQuoteNotPossible() throws QuoteNotPossibleException {
        double loanAmount = 987000;
        exception.expect(QuoteNotPossibleException.class);
        Quote.getQuote(loanAmount, RateCalculator.DEFAULT_TERM_36_MONTHS, marketData);
    }
}
