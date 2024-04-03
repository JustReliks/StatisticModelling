package org.statisticModelling.distribution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class IRNGEOInitializer implements DistributionInitializer {
    @Override
    public Distribution getDistribution(PrintStream printStream, BufferedReader bufferedReader, int size) throws IOException {
        printStream.println("P: ");
        double p = Double.parseDouble(bufferedReader.readLine());
        printStream.println("Type: ");
        int type = Integer.parseInt(bufferedReader.readLine());

        return switch (type) {
            case 1 -> new IRNGEO1(p);
            case 2 -> new IRNGEO2(p);
            case 3 -> new IRNGEO3(p);
            default -> throw new RuntimeException();
        };
    }

    @Override
    public String getName() {
        return "Геометрическое распределение";
    }
}
