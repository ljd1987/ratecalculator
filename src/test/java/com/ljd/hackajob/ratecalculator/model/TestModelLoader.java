package com.ljd.hackajob.ratecalculator.model;

import static com.ljd.hackajob.ratecalculator.utils.TestUtils.generateCSVFile;
import static com.ljd.hackajob.ratecalculator.utils.TestUtils.generateLenderOffers;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ljd.hackajob.ratecalculator.model.exceptions.MarketDataLoadingException;
import com.ljd.hackajob.ratecalculator.model.exceptions.MarketDataNotFoundException;
import com.ljd.hackajob.ratecalculator.model.exceptions.RateCalculatorException;

public class TestModelLoader {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testLoadCSV() throws IOException, RateCalculatorException {
        List<LenderOffer> lenderOffers = generateLenderOffers(8);        
        File csvFile = generateCSVFile(lenderOffers);
                
        lenderOffers = lenderOffers.stream().sorted((a,b)->a.getRate().compareTo(b.getRate())).collect(Collectors.toList());
        List<LenderOffer> loadedOffers = ModelLoader.loadMarketModelFromCSV(csvFile.getPath());        
        assertEquals(lenderOffers, loadedOffers);
    }
    
    @Test
    public void testLoadCSVFileNotFound() throws RateCalculatorException {
        exception.expect(MarketDataNotFoundException.class);
        ModelLoader.loadMarketModelFromCSV("doesntexist");
    }
    
    @Test
    public void testLoadCSVParseError() throws IOException, RateCalculatorException {
        File invalidDataFile = File.createTempFile("marketData", ".csv");
        invalidDataFile.deleteOnExit();
        Files.write(invalidDataFile.toPath(), "a,b,c\nfoo,bar,100\nLeo,0.034,notanumber".getBytes(StandardCharsets.UTF_8));
        
        exception.expect(MarketDataLoadingException.class);
        ModelLoader.loadMarketModelFromCSV(invalidDataFile.getPath());
    }
}
