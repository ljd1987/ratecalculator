package com.ljd.hackajob.ratecalculator.model;

import java.math.BigDecimal;

/**
 * 
 * @author leodavison
 *
 */
public class LenderOffer {
    private final String lender;
    private final BigDecimal rate;
    private final BigDecimal availalbe;

    public LenderOffer(String lender, double rate, double available) {
        this.lender = lender;
        this.rate = BigDecimal.valueOf(rate);
        this.availalbe = BigDecimal.valueOf(available).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getLender() {
        return lender;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAvailalbe() {
        return availalbe;
    }        

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + availalbe.hashCode();
        result = prime * result + ((lender == null) ? 0 : lender.hashCode());
        result = prime * result + rate.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LenderOffer other = (LenderOffer) obj;

        if (lender == null) {
            if (other.lender != null) {
                return false;
            }
        } else if (!lender.equals(other.lender)) {
            return false;
        }

        if (!availalbe.equals(other.availalbe)) {
            return false;
        }

        return rate.equals(other.rate);
    }
}
