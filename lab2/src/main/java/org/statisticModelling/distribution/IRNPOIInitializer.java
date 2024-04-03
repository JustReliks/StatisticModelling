package org.statisticModelling.distribution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class IRNPOIInitializer implements DistributionInitializer {

    @Override
    public Distribution getDistribution(PrintStream printStream, BufferedReader bufferedReader, int size) throws IOException {
        printStream.println("L: ");
        int L = Integer.parseInt(bufferedReader.readLine());
        printStream.println("Type: ");
        int type = Integer.parseInt(bufferedReader.readLine());
        return switch (type) {
            case 1 -> new IRNPOI1(L);
            case 2 -> new IRNPOI2(L);
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String getName() {
        return "Пуассоновское распределение";
    }
}
