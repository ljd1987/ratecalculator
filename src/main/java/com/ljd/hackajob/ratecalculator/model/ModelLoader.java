package com.ljd.hackajob.ratecalculator.model;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        try(Stream<String> lines = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            return lines
                    .skip(1)
                    .map(ModelLoader::lineToLenderOffer)
                    .sorted((a,b)->a.getRate().compareTo(b.getRate()))
                    .collect(Collectors.toList());
        } catch (NoSuchFileException e) {
            throw new MarketDataNotFoundException(filePath);
        } catch (IOException  e) {
            throw new MarketDataLoadingException(e.getMessage());
        } catch(UncheckedIOException e) {
            throw new MarketDataLoadingException(e.getCause().getMessage());
        } catch (ExceptionWrapper e) {
            throw e.getException();
        }
    }

    private static LenderOffer lineToLenderOffer(String line) {
        String[] parts = line.split(",");
        try {
            return new LenderOffer(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
        } catch (NumberFormatException e) {
            MarketDataLoadingException cause = new MarketDataLoadingException(e.getMessage());
            throw new ExceptionWrapper(cause);
        }
    }
}
