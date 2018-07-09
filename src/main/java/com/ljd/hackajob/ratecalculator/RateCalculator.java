package com.ljd.hackajob.ratecalculator;

import java.math.BigDecimal;
import java.util.List;

import com.ljd.hackajob.ratecalculator.model.LenderOffer;
import com.ljd.hackajob.ratecalculator.model.ModelLoader;
import com.ljd.hackajob.ratecalculator.model.Quote;
import com.ljd.hackajob.ratecalculator.model.exceptions.InvalidLoanAmount;
import com.ljd.hackajob.ratecalculator.model.exceptions.InvalidUsageException;
import com.ljd.hackajob.ratecalculator.model.exceptions.LoanInputInvalidException;
import com.ljd.hackajob.ratecalculator.model.exceptions.RateCalculatorException;

public class RateCalculator {
    public static final int DEFAULT_TERM_36_MONTHS = 36;
    public static final BigDecimal MIN_LOAN_AMOUNT = BigDecimal.valueOf(1000L);
    public static final BigDecimal MAX_LOAN_AMOUNT = BigDecimal.valueOf(15000L);
    public static final BigDecimal LOAN_INCREMENT  = BigDecimal.valueOf(100L);
    
    public static final String USAGE = "java -jar ratecalculator-1.0.0.jar [pathToMarketData] [loanAmount]";

    private static String marketDataPath = null;
    private static Double loanAmount = null;

    private static void processArguments(String...args) throws InvalidUsageException, LoanInputInvalidException, InvalidLoanAmount {
        if (args.length != 2) {
            throw new InvalidUsageException(USAGE);
        }

        marketDataPath = args[0];

        String loanAmountStr = args[1];

        try {
            loanAmount = Double.parseDouble(loanAmountStr);
            
            BigDecimal la = BigDecimal.valueOf(loanAmount).setScale(0);
            
            boolean valid = MAX_LOAN_AMOUNT.compareTo(la) >= 0 && MIN_LOAN_AMOUNT.compareTo(la) <= 0 && la.remainder(LOAN_INCREMENT).equals(BigDecimal.ZERO);
            
            if (!valid) {
                throw new InvalidLoanAmount(la, MIN_LOAN_AMOUNT, MAX_LOAN_AMOUNT, LOAN_INCREMENT);
            }
        } catch (NumberFormatException e) {
            throw new LoanInputInvalidException(loanAmountStr);
        }
    }

    public static void main(String...args) {        
        try {
            processArguments(args);
            List<LenderOffer> marketData = ModelLoader.loadMarketModelFromCSV(marketDataPath);
            Quote quote = Quote.getQuote(loanAmount, DEFAULT_TERM_36_MONTHS, marketData);

            // don't really want to set up customer Logger just for this exercise, when we want the output to go to stdout.
            System.out.println(String.format("Requested amount: £%s", quote.getLoanAmount().setScale(0, BigDecimal.ROUND_HALF_UP))); //NOSONAR
            System.out.println(String.format("Rate: %s%%", quote.getRate().multiply(BigDecimal.valueOf(100.0)).setScale(1, BigDecimal.ROUND_HALF_UP))); //NOSONAR
            System.out.println(String.format("Monthly repayment: £%s", quote.getMonthlyPayment())); //NOSONAR
            System.out.println(String.format("Total repayment: £%s", quote.getTotalRepayment())); //NOSONAR
        } catch (RateCalculatorException e) {
            System.out.println(e.getExitMessage()); //NOSONAR
            System.exit(e.getExitCode());
        }
    }
}
