package org.statisticModelling.distribution;

import lombok.RequiredArgsConstructor;
import org.statisticModelling.graphics.Point;
import org.statisticModelling.sequence.Sequence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class IRNUNI implements Distribution {

    private final int ILOW;
    private final int IUP;

    @Override
    public double generate(int n) {
        return getN() * Math.random() + ILOW;
    }

    private int getN() {
        return IUP - ILOW + 1;
    }

    @Override
    public double calculateTheoreticalVariance() {
        return (double) (ILOW + IUP) / 2;
    }

    @Override
    public double calculateTheoreticalDispersion() {
        return Math.pow(IUP - ILOW + 1, 2) / 12;
    }

    @Override
    public List<Point> distributionPoints(Sequence sequence) {
        return List.of(new Point(ILOW, (double) 1 / getN()), new Point(IUP, (double) 1 / getN()));
    }

    @Override
    public List<Point> distributionDensityPoints(Sequence sequence) {
        return Arrays.stream(sequence.getSequence())
                .mapToObj(operand -> new Point((operand - ILOW) / (double) 1 / IUP, operand))
                .collect(Collectors.toList());
    }
}
