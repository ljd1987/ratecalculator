package com.ljd.hackajob.ratecalculator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.ljd.hackajob.ratecalculator.model.exceptions.ExitCodes;

public class RateCalculatorTest {
    @Rule
    public ExpectedSystemExit exit = ExpectedSystemExit.none();
    
    @Rule
    public SystemOutRule sysout = new SystemOutRule().enableLog();
    
    @Before
    public void beforeEach() {
        sysout.clearLog();
    }

    @Test
    public void testOutput() {
        String path = RateCalculatorTest.class.getClassLoader().getResource("lenderdata.csv").getPath();
        String loanRequest = "1000";
        
        String expectedOutput = "Requested amount: £1000" +  System.lineSeparator() +
                "Rate: 7.0%" +  System.lineSeparator() + 
                "Monthly repayment: £30.88" +  System.lineSeparator() + 
                "Total repayment: £1111.68" +  System.lineSeparator();                
        
        RateCalculator.main(path, loanRequest);
        assertEquals(expectedOutput, sysout.getLog());
    }
    
    @Test
    public void testErrorOutputFileNotFound() {
        String path = "foobar";
        String loanRequest = "1000";
        String exptectedOutput = String.format("Couldn't load market data.  The file '%s' couldn't be found.%s",path,System.lineSeparator()); 
        
        exit.expectSystemExitWithStatus(ExitCodes.MARKET_DATA_NOT_FOUND);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                assertEquals(exptectedOutput, sysout.getLog());
            }
            
        });
        RateCalculator.main(path, loanRequest);
    }
    
    @Test
    public void testErrorOutputNotANumber() {
        String path = "foobar";
        String loanRequest = "loads";
        String exptectedOutput = String.format("The loanAmount [%s] is not valid.%s",loanRequest,System.lineSeparator()); 
        
        exit.expectSystemExitWithStatus(ExitCodes.INVALID_LOAN_INPUT);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                assertEquals(exptectedOutput, sysout.getLog());
            }
            
        });
        RateCalculator.main(path, loanRequest);
    }
    
    @Test
    public void testInvalidUsage() {        
        String loanRequest = null;
        String exptectedOutput = RateCalculator.USAGE+System.lineSeparator(); 
        
        exit.expectSystemExitWithStatus(ExitCodes.INVALID_USAGE);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                assertEquals(exptectedOutput, sysout.getLog());
            }
            
        });
        RateCalculator.main(loanRequest);
    }
    
    @Test
    public void testLoanAmountBelowRnge() {        
        String path = RateCalculatorTest.class.getClassLoader().getResource("lenderdata.csv").getPath();
        String loanRequest = "900";
        String exptectedOutput = String.format("The loan amount of £%s is not supported.  Loans must be between £%s and £%s in increments of £%s%s", new BigDecimal(loanRequest), RateCalculator.MIN_LOAN_AMOUNT, RateCalculator.MAX_LOAN_AMOUNT, RateCalculator.LOAN_INCREMENT, System.lineSeparator()); 
        
        exit.expectSystemExitWithStatus(ExitCodes.INVALID_LOAN_AMOUNT);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                assertEquals(exptectedOutput, sysout.getLog());
            }
            
        });
        RateCalculator.main(path, loanRequest);
    }
    
    @Test
    public void testLoanAmountAboveRnge() {        
        String path = RateCalculatorTest.class.getClassLoader().getResource("lenderdata.csv").getPath();
        String loanRequest = "15001";
        String exptectedOutput = String.format("The loan amount of £%s is not supported.  Loans must be between £%s and £%s in increments of £%s%s", new BigDecimal(loanRequest), RateCalculator.MIN_LOAN_AMOUNT, RateCalculator.MAX_LOAN_AMOUNT, RateCalculator.LOAN_INCREMENT, System.lineSeparator()); 
        
        exit.expectSystemExitWithStatus(ExitCodes.INVALID_LOAN_AMOUNT);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                assertEquals(exptectedOutput, sysout.getLog());
            }
            
        });
        RateCalculator.main(path, loanRequest);
    }
    
    @Test
    public void testLoanAmountHasCorrectIncrement() {        
        String path = RateCalculatorTest.class.getClassLoader().getResource("lenderdata.csv").getPath();
        String loanRequest = "1005";
        String exptectedOutput = String.format("The loan amount of £%s is not supported.  Loans must be between £%s and £%s in increments of £%s%s", new BigDecimal(loanRequest), RateCalculator.MIN_LOAN_AMOUNT, RateCalculator.MAX_LOAN_AMOUNT, RateCalculator.LOAN_INCREMENT, System.lineSeparator()); 
        
        exit.expectSystemExitWithStatus(ExitCodes.INVALID_LOAN_AMOUNT);
        exit.checkAssertionAfterwards(new Assertion() {
            @Override
            public void checkAssertion() throws Exception {
                assertEquals(exptectedOutput, sysout.getLog());
            }
            
        });
        RateCalculator.main(path, loanRequest);
    }
}
