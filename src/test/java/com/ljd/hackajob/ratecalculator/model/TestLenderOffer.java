package com.ljd.hackajob.ratecalculator.model;

import static com.ljd.hackajob.ratecalculator.utils.TestUtils.RAND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class TestLenderOffer {
    private String lenderName;
    private double rate;
    private double available;
    
    private LenderOffer offer;

    @Before
    public void beforeEach() {
        lenderName = UUID.randomUUID().toString();
        rate = RAND.nextDouble();
        available = 10.0 + (RAND.nextDouble() * 1000);
        offer = new LenderOffer(lenderName, rate, available);
    }
    
    @Test
    public void testGetLender() {
        assertEquals(lenderName, offer.getLender());
    }
    
    @Test
    public void testGetRate() {
        assertEquals(BigDecimal.valueOf(rate), offer.getRate());
    }
    
    @Test
    public void testGetAvailable() {
        assertEquals(BigDecimal.valueOf(available).setScale(2, BigDecimal.ROUND_HALF_UP), offer.getAvailalbe());
    }
    
    @Test
    public void testHashCodeEquals() {
        LenderOffer other = new LenderOffer(lenderName, rate, available);
        assertEquals(offer.hashCode(), other.hashCode());
    }
    
    @Test
    public void testHashCodeNotEquals() {
        LenderOffer other = new LenderOffer(UUID.randomUUID().toString(), rate+0.1, available+10.0);
        assertNotEquals(offer.hashCode(), other.hashCode());
    }
    
    @Test
    public void testHashCodeNotEqualsNullName() {
        LenderOffer other = new LenderOffer(null, rate, available);
        assertNotEquals(offer.hashCode(), other.hashCode());
    }
    
    @Test
    public void testEquals() {
        LenderOffer other = new LenderOffer(lenderName, rate, available);
        assertEquals(offer, other);
    }
    
    @Test
    public void testEqualsNullNames() {
        LenderOffer a = new LenderOffer(null, rate, available);
        LenderOffer b = new LenderOffer(null, rate, available);
        assertEquals(a, b);
    }
    
    @Test
    public void testEqualsSelf() {
        assertEquals(offer, offer);
    }
    
    @Test
    public void testNameNotEqual() {
        LenderOffer other = new LenderOffer(UUID.randomUUID().toString(), rate, available);
        assertNotEquals(offer, other);
    }
    
    @Test
    public void testNullNameNotEqual() {
        LenderOffer other = new LenderOffer(null, rate, available);
        assertNotEquals(offer, other);
        assertNotEquals(other, offer);
    }
    
    @Test
    public void testRateNotEqual() {
        LenderOffer other = new LenderOffer(lenderName, rate=0.1, available);
        assertNotEquals(offer, other);
    }
    
    @Test
    public void testAmountNotEqual() {
        LenderOffer other = new LenderOffer(lenderName, rate, available+10.0);
        assertNotEquals(offer, other);
    }
    
    @Test
    public void testNotEqualsNull() {
        assertNotEquals(offer, null);
    }
    
    @Test
    public void testNotEqualsOtherClass() {
        assertNotEquals(offer, "hello");
    }
}
