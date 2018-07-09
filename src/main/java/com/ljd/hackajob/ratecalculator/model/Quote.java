package com.ljd.hackajob.ratecalculator.model;

import java.math.BigDecimal;
import java.util.List;

import com.ljd.hackajob.ratecalculator.model.exceptions.QuoteNotPossibleException;

/**
 * 
 * @author leodavison
 *
 */
public class Quote {
    private BigDecimal loanAmount;
    private BigDecimal rate;
    private BigDecimal monthlyPayment;
    private BigDecimal totalRepayment;

    Quote(BigDecimal loanAmount, BigDecimal rate, BigDecimal monthlyPayment, BigDecimal totalRepayment) {
        this.loanAmount = loanAmount;
        this.rate = rate;
        this.monthlyPayment = monthlyPayment;
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }
    
    public static Quote getQuote(double requestedAmount, int term, List<LenderOffer> market) throws QuoteNotPossibleException {
        return new QuoteBuilder(requestedAmount, term, market).build();
    }
}
