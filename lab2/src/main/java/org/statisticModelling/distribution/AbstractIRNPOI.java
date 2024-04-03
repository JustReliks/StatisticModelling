package org.statisticModelling.distribution;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.statisticModelling.graphics.Point;
import org.statisticModelling.sequence.Sequence;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.math3.util.CombinatoricsUtils.factorial;

@RequiredArgsConstructor
@Getter
public abstract class AbstractIRNPOI implements Distribution {

    private final int L;

    private NormalDistribution normalDistribution;

    @Override
    public double calculateTheoreticalVariance() {
        return L;
    }

    @Override
    public double calculateTheoreticalDispersion() {
        return L;
    }

    protected NormalDistribution getNormalDistribution() {
        if (normalDistribution == null) {
            normalDistribution = new NormalDistribution(L, L);
        }
        return normalDistribution;
    }

    @Override
    public List<Point> distributionPoints(Sequence sequence) {
        NormalDistribution normalDistribution = new NormalDistribution(calculateTheoreticalVariance(), calculateTheoreticalDispersion());
        List<Point> res = new ArrayList<>();
        for (double j = 0; j <= 100; j += 0.25) {
            double cdf = normalDistribution.cumulativeProbability(j);
            res.add(new Point(j, cdf));
        }
        return res;
    }

    @Override
    public List<Point> distributionDensityPoints(Sequence sequence) {
        List<Point> res = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            double y = Math.pow(10, i) * Math.exp(-10) / factorial(i);
            res.add(new Point(i, y));
        }
        return res;
    }
}
