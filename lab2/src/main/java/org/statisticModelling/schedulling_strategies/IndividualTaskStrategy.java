package org.statisticModelling.schedulling_strategies;

import org.apache.commons.math3.distribution.GeometricDistribution;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.statisticModelling.ProgramScheduler;
import org.statisticModelling.distribution.Distribution;
import org.statisticModelling.distribution.IRNGEO1;
import org.statisticModelling.distribution.IRNGEO2;
import org.statisticModelling.distribution.IRNGEO3;
import org.statisticModelling.sequence.Sequence;

import java.util.Arrays;

public class IndividualTaskStrategy implements ProgramSchedulerStrategy {

    @Override
    public void doAction(ProgramScheduler scheduler) throws Exception {
        Distribution geom = new IRNGEO3(0.7);
        Sequence sequence = new Sequence(geom, 50);
        ChiSquareTest test = new ChiSquareTest();

        GeometricDistribution distribution = new GeometricDistribution(0.7);
        long[] real = Arrays.stream(distribution.sample(50)).mapToLong(value -> value + 1).toArray();
        long[] data = Arrays.stream(sequence.getSequence()).mapToLong(value -> (long) value + 1).toArray();
        double v1 = test.chiSquareTestDataSetsComparison(data, real);
        boolean v = test.chiSquareTest(Arrays.stream(data).mapToDouble(value -> value).toArray(), real, 0.05);
        scheduler.getOut().println(v);
        scheduler.getOut().println(v1);

        //Гипотерза H0 - Распределения не совпадают.
        // Т.к. мы получили false в результате метода, значит H0 - не верен. Значит две выборки
        // зависимы и имеют геометрическое распределение
        scheduler.setSchedulerStrategy(new SelectDistributionStrategy());

    }
}
