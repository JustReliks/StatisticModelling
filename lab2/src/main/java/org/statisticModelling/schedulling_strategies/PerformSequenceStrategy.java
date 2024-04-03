package org.statisticModelling.schedulling_strategies;

import lombok.RequiredArgsConstructor;
import org.statisticModelling.ProgramScheduler;
import org.statisticModelling.distribution.Distribution;
import org.statisticModelling.distribution.DistributionInitializer;
import org.statisticModelling.sequence.Sequence;

@RequiredArgsConstructor
public class PerformSequenceStrategy implements ProgramSchedulerStrategy {

    private final DistributionInitializer distributionInitializer;

    @Override
    public void doAction(ProgramScheduler scheduler) throws Exception {
        scheduler.getOut().println("Введите размер последовательности: ");
        int size = Integer.parseInt(scheduler.getIn().readLine());
        Distribution distribution = distributionInitializer.getDistribution(scheduler.getOut(), scheduler.getIn(), size);

        Sequence sequence = new Sequence(distribution, size);
        scheduler.setSchedulerStrategy(new GenerateSequenceStatisticsStrategy(sequence, distribution));

    }
}
