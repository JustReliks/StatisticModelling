package org.statisticModelling.distribution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class IRNBINInitializer implements DistributionInitializer {
    @Override
    public Distribution getDistribution(PrintStream printStream, BufferedReader bufferedReader, int size) throws IOException {
        printStream.println("N: ");
        int N = Integer.parseInt(bufferedReader.readLine());
        printStream.println("P: ");
        double P = Double.parseDouble(bufferedReader.readLine());
        if (size < 100) {
            return new IRNBINClassic(N, P);
        }
        return new IRNBINOptimized(N, P);
    }

    @Override
    public String getName() {
        return "Биномиальное распределение";
    }
}
