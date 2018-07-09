package com.ljd.hackajob.ratecalculator.model;

import java.math.BigDecimal;
import java.util.List;

import com.ljd.hackajob.ratecalculator.model.exceptions.QuoteNotPossibleException;

public class QuoteBuilder {
    private int term;
    private BigDecimal requestedAmount;
    private BigDecimal loanAmount;
    private BigDecimal rate;
    private BigDecimal monthlyPayment;
    private BigDecimal totalRepayment;

    public QuoteBuilder(double requestedAmount, int term, List<LenderOffer> market) {
        this.term = term;
        this.requestedAmount = BigDecimal.valueOf(requestedAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.loanAmount = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.rate = BigDecimal.ZERO.setScale(3, BigDecimal.ROUND_HALF_UP);
        this.monthlyPayment = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalRepayment = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        for (LenderOffer offer : market) {
            if (withLenderOffer(offer).isSatisfied()) {
                break;
            }
        }
    }
    
    private QuoteBuilder withLenderOffer(LenderOffer offer) {
        if (!isSatisfied()) {
            // if we include all of this offers available amount, 
            // how much more would we need to source to fulfil the loan?
            BigDecimal remainder = requestedAmount.subtract(loanAmount.add(offer.getAvailalbe()));
            
            // what portion of this offer do we need?
            BigDecimal portion = null;
            if (remainder.compareTo(BigDecimal.ZERO) < 0) {
                // this offer more than satisfies the remaining amount
                // we need to source, and so we only need a portion of it
                portion = offer.getAvailalbe()
                        .setScale(6, BigDecimal.ROUND_HALF_UP) // need greater scale, or the rounding will be off
                        .add(remainder)
                        .divide(offer.getAvailalbe(), BigDecimal.ROUND_HALF_UP);
            } else {
                // the entirety of this offer is either exactly the amount we need, or not
                // enough.  In wither case, we need all of it.
                portion = BigDecimal.ONE;
            }
            
            LoanPart part = new LoanPart(offer, term, portion);
            
            loanAmount = loanAmount.add(part.getAmount());
            rate = rate.add(part.getScaledRate(requestedAmount)).setScale(3, BigDecimal.ROUND_HALF_UP);
            monthlyPayment = monthlyPayment.add(part.getMonthlyRepayment());
            totalRepayment = totalRepayment.add(part.getTotalRepayable());
        }            
        return this;
    }

    private boolean isSatisfied() {
        return requestedAmount.equals(loanAmount);
    }

    public Quote build() throws QuoteNotPossibleException {
        if (!isSatisfied()) {
            throw new QuoteNotPossibleException();
        }
        return new Quote(loanAmount, rate, monthlyPayment, totalRepayment);
    }
}
