package org.statisticModelling.schedulling_strategies;

import org.statisticModelling.ProgramScheduler;
import org.statisticModelling.distribution.*;

import java.util.Map;
import java.util.TreeMap;

public class SelectDistributionStrategy implements ProgramSchedulerStrategy {

    private static final Map<Integer, DistributionInitializer> distribution_commands = new TreeMap<>(Map.of(
            1, new IRNUNIInitializer(),
            2, new IRNBINInitializer(),
            3, new IRNGEOInitializer(),
            4, new IRNPOIInitializer()
    ));


    @Override
    public void doAction(ProgramScheduler scheduler) throws Exception {
        scheduler.getOut().println("Выберите распределение, которое будем строить:");
        distribution_commands.forEach((integer, distribution) -> scheduler.getOut().printf("%s. %s%n", integer, distribution.getName()));
        scheduler.getOut().printf("%s. %s%n", distribution_commands.size() + 1, "Индивидуальное задание");
        int key = Integer.parseInt(scheduler.getIn().readLine());
        if (key == distribution_commands.size() + 1) {
            scheduler.setSchedulerStrategy(new IndividualTaskStrategy());
        } else {
            DistributionInitializer distribution = distribution_commands.get(key);
            scheduler.setSchedulerStrategy(new PerformSequenceStrategy(distribution));
        }
    }
}
