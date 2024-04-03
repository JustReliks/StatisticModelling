package org.statisticModelling.distribution;

import org.statisticModelling.graphics.Point;
import org.statisticModelling.sequence.Sequence;

import java.util.List;

public interface Distribution {

    double generate(int n);

    double calculateTheoreticalVariance();

    double calculateTheoreticalDispersion();

    List<Point> distributionPoints(Sequence sequence);

    List<Point> distributionDensityPoints(Sequence sequence);

}
