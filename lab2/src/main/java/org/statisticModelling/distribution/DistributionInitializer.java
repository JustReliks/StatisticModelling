package org.statisticModelling.distribution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public interface DistributionInitializer {

    Distribution getDistribution(PrintStream printStream, BufferedReader bufferedReader, int size) throws IOException;
    String getName();


}
