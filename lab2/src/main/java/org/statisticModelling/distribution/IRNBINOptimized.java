package org.statisticModelling.distribution;

import org.apache.commons.math3.distribution.NormalDistribution;

public class IRNBINOptimized extends AbstractIRNBIN {

    private final NormalDistribution normalDistribution;

    public IRNBINOptimized(int N, double P) {
        super(N, P);

        normalDistribution = new NormalDistribution(N * P, Math.sqrt(N * P * (1.0 - P)) + 0.5);
    }

    @Override
    public double generate(int n) {
        return Math.round(normalDistribution.sample());
    }
}
