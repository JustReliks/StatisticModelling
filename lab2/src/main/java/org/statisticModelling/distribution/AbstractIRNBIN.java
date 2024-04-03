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
public abstract class AbstractIRNBIN implements Distribution {

    private final int N;
    private final double P;

    @Override
    public double calculateTheoreticalVariance() {
        return N * P;
    }

    @Override
    public double calculateTheoreticalDispersion() {
        return N * P * (1 - P);
    }

    @Override
    public List<Point> distributionPoints(Sequence sequence) {
        NormalDistribution normalDistribution = new NormalDistribution(calculateTheoreticalVariance(), calculateTheoreticalDispersion());
        List<Point> res = new ArrayList<>();
        for (double i = 0; i <= 10; i += 0.25) {
            double v = normalDistribution.cumulativeProbability(i);
            res.add(new Point(i, v));
        }
        return res;
    }

    @Override
    public List<Point> distributionDensityPoints(Sequence sequence) {
        List<Point> res = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            double y = (double) factorial(10) / (factorial(i) * factorial(10 - i)) * Math.pow(P, i) * Math.pow(1 - P, 10 - i);
            res.add(new Point(i, y));
        }
        return res;
    }
}
