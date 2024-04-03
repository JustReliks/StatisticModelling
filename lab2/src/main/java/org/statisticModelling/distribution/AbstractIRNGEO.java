package org.statisticModelling.distribution;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.statisticModelling.graphics.Point;
import org.statisticModelling.sequence.Sequence;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class AbstractIRNGEO implements Distribution {

    private final double P;

    @Override
    public double calculateTheoreticalVariance() {
        return (1 - P) / P;
    }

    @Override
    public double calculateTheoreticalDispersion() {
        return (1 - P) / (P * P);
    }

    @Override
    public List<Point> distributionPoints(Sequence sequence) {
        List<Point> res = new ArrayList<>();
        NormalDistribution normalDistribution = new NormalDistribution(calculateTheoreticalVariance(), calculateTheoreticalDispersion());
        for (double j = 0; j <= 10; j += 0.25) {
            res.add(new Point(j, normalDistribution.cumulativeProbability(j)));
        }
        return res;
    }

    @Override
    public List<Point> distributionDensityPoints(Sequence sequence) {
        List<Point> res = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            double y = 0.5 * Math.pow(0.5, i - 1);
            res.add(new Point(i, y));
        }
        return res;
    }
}
