package com.ljd.hackajob.ratecalculator.model;

import java.math.BigDecimal;

public class LoanPart {
    private final LenderOffer lenderOffer;
    private final int term;
    private final BigDecimal portion;
    
    private final BigDecimal amount;
    private final BigDecimal monthlyRepayment;
    private final BigDecimal totalRepayable;

    public LoanPart(LenderOffer lenderOffer, int term, BigDecimal portion) {
        this.lenderOffer = lenderOffer;
        this.term = term;
        this.portion = portion;
        this.monthlyRepayment = calculateMonthlyRepayment();
        this.totalRepayable = monthlyRepayment.multiply(BigDecimal.valueOf(term)).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.amount = lenderOffer.getAvailalbe().multiply(portion).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    private BigDecimal calculateMonthlyRepayment() {
        BigDecimal r = getRate().setScale(6, BigDecimal.ROUND_HALF_UP);
        BigDecimal i = r.divide(BigDecimal.valueOf(12), BigDecimal.ROUND_HALF_UP);        
        BigDecimal divisor = i.add(BigDecimal.ONE).pow(term).multiply(i);
        
        BigDecimal d = i
                .add(BigDecimal.ONE)
                .pow(term)
                .subtract(BigDecimal.ONE)
                .divide(divisor, BigDecimal.ROUND_HALF_UP);
       
        return lenderOffer.getAvailalbe().multiply(portion).divide(d, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getRate() {
        return lenderOffer.getRate();
    }

    /**
     * P = A / D
     *
     * n = term in months
     * i = rate / 12 months
     * D = (((1+i) ^ n) -1 ) / (i * (1+i) ^ n)
     *
     * P = (A / D)
     * @return
     */
    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public BigDecimal getTotalRepayable() {
        return totalRepayable;
    }
    
    public BigDecimal getScaledRate(BigDecimal loanAmount) {
        return lenderOffer.getAvailalbe().multiply(portion).divide(loanAmount, BigDecimal.ROUND_HALF_UP).multiply(getRate());
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
}
