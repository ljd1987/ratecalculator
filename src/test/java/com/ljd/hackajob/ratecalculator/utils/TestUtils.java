package com.ljd.hackajob.ratecalculator.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.ljd.hackajob.ratecalculator.model.LenderOffer;

public class TestUtils {
    private static final String CSV_HEADER = "Lender,Rate,Available";

    public static final Random RAND = new Random(System.currentTimeMillis());

    public static LenderOffer generateLenderOffer() {
        String lenderName = UUID.randomUUID().toString();
        double rate = RAND.nextDouble();
        double available = 10.0 + (RAND.nextDouble() * 1000);
        return new LenderOffer(lenderName, rate, available);
    }

    public static List<LenderOffer> generateLenderOffers(int count) {
        List<LenderOffer> lenderOffers = new ArrayList<>(count);

        for (int i=0;  i<count; i++) {
            lenderOffers.add(generateLenderOffer());
        }

        return lenderOffers;
    }

    private static String generateCSV(List<LenderOffer> lenderOffers) {
        StringBuilder csv = new StringBuilder(CSV_HEADER).append("\n");
        
        lenderOffers.forEach(lender->{
            csv.append(lender.getLender()).append(",");
            csv.append(lender.getRate().toPlainString()).append(",");
            csv.append(lender.getAvailalbe().toPlainString()).append("\n");
        });
        
        return csv.toString();
    }
    
    public static File generateCSVFile(List<LenderOffer> lenderOffers) throws IOException {
        File file = File.createTempFile("marketData", ".csv");
        file.deleteOnExit();
        String csvStr = generateCSV(lenderOffers);
        Files.write(file.toPath(), csvStr.getBytes(StandardCharsets.UTF_8));
        return file;
    }
}
