package com.ljd.hackajob.ratecalculator.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.ljd.hackajob.ratecalculator.model.exceptions.ExceptionWrapper;
import com.ljd.hackajob.ratecalculator.model.exceptions.MarketDataLoadingException;
import com.ljd.hackajob.ratecalculator.model.exceptions.MarketDataNotFoundException;
import com.ljd.hackajob.ratecalculator.model.exceptions.RateCalculatorException;

/**
 *
 * @author leodavison
 *
 */
public class ModelLoader {
    private ModelLoader() {
        // not meant to be instantiated
    }        

    public static List<LenderOffer> loadMarketModelFromCSV(String filePath) throws RateCalculatorException  {
        try (InputStream is=new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {
            // line-by-line, skip the first line (header), map to a LenderOffer, collect into a List
            return br.lines().skip(1).map(ModelLoader::lineToLenderOffer).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new MarketDataNotFoundException(filePath);
        } catch (IOException e) {
            throw new MarketDataLoadingException(filePath);
        } catch (ExceptionWrapper e) {
            throw e.getException();
        }
    }

    public static LenderOffer lineToLenderOffer(String line) {
        String[] parts = line.split(",");
        try {
            return new LenderOffer(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
        } catch (NumberFormatException e) {
            MarketDataLoadingException cause = new MarketDataLoadingException(e.getMessage());
            throw new ExceptionWrapper(cause);
        }
    }
}
