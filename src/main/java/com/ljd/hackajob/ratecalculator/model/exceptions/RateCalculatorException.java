package com.ljd.hackajob.ratecalculator.model.exceptions;

/**
 * 
 * @author leodavison
 *
 */
public abstract class RateCalculatorException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public abstract String getExitMessage();
    public abstract int getExitCode();
}
