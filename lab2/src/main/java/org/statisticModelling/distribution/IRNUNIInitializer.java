package org.statisticModelling.distribution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class IRNUNIInitializer implements DistributionInitializer{
    @Override
    public Distribution getDistribution(PrintStream printStream, BufferedReader bufferedReader, int size) throws IOException {
        printStream.println("ILOW: ");
        int ilow = Integer.parseInt(bufferedReader.readLine());
        printStream.println("IUP: ");
        int iup = Integer.parseInt(bufferedReader.readLine());

        return new IRNUNI(ilow, iup);

    }

    @Override
    public String getName() {
        return "Равномерное распределение";
    }
}
