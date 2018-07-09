package com.ljd.hackajob.ratecalculator.model;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * 
 * @author leodavison
 *
 */
public class TestLoanPart {

    /**
     * monthly: 14.89
     * total:   532.80
     */
    @Test
    public void testLoanPart() {
        
        LenderOffer offer = new  LenderOffer("Jane", 0.069, 480);
        LoanPart part = new LoanPart(offer, 36, BigDecimal.ONE);
        
        LenderOffer offer2 = new  LenderOffer("Jane", 0.071, 520);
        LoanPart part2 = new LoanPart(offer2, 36, BigDecimal.ONE);
                
        System.out.println("Monthly: " + part.getMonthlyRepayment());
        System.out.println("Total  : " + part.getTotalRepayable());
        System.out.println("---------");
        System.out.println("Monthly: " + part2.getMonthlyRepayment());
        System.out.println("Total  : " + part2.getTotalRepayable());
        
        BigDecimal totalMonthly = part.getMonthlyRepayment().add(part2.getMonthlyRepayment());
        BigDecimal total = part.getTotalRepayable().add(part2.getTotalRepayable());
        
        System.out.println("--------");
        System.out.println("Monthly: " + totalMonthly);
        System.out.println("Total  : " + total);
        
        System.out.println("--------");
        BigDecimal r1 = part.getScaledRate(BigDecimal.valueOf(1000));
        BigDecimal r2 = part2.getScaledRate(BigDecimal.valueOf(1000));
        System.out.println("rate:" + r1.add(r2).setScale(3, BigDecimal.ROUND_HALF_UP)); 
    }
}
