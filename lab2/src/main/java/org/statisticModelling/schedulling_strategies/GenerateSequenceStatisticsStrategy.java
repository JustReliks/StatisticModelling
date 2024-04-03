package org.statisticModelling.schedulling_strategies;

import lombok.RequiredArgsConstructor;
import org.statisticModelling.ProgramScheduler;
import org.statisticModelling.distribution.Distribution;
import org.statisticModelling.graphics.DistributionDensitySchedule;
import org.statisticModelling.graphics.DistributionSchedule;
import org.statisticModelling.graphics.Histogram;
import org.statisticModelling.graphics.Table;
import org.statisticModelling.sequence.Sequence;

@RequiredArgsConstructor
public class GenerateSequenceStatisticsStrategy implements ProgramSchedulerStrategy {

    private final Sequence sequence;
    private final Distribution distribution;

    @Override
    public void doAction(ProgramScheduler scheduler) throws Exception {
        System.out.println(sequence.getDispersion());
        System.out.println(sequence.getVariance());
        String simpleName = distribution.getClass().getSimpleName();
        String[] headers = {"Оценка", simpleName, "Теоретическое значение", "Разница"};
        double theoreticalVariance = distribution.calculateTheoreticalVariance();
        double theoreticalDispersion = distribution.calculateTheoreticalDispersion();
        Object[][] data = new Object[][]{{"@M@", sequence.getVariance(),
                theoreticalVariance, Math.abs(theoreticalVariance - sequence.getVariance())},
                {"@D@", sequence.getDispersion(),
                        theoreticalDispersion, Math.abs(theoreticalDispersion - sequence.getDispersion())}};


        Histogram histogram = new Histogram(sequence.getSequence(), simpleName);
        DistributionDensitySchedule densitySchedule = new DistributionDensitySchedule(distribution.distributionDensityPoints(sequence), simpleName);
        DistributionSchedule schedule = new DistributionSchedule(distribution.distributionPoints(sequence), simpleName);
        Table table = new Table(data, headers);
        table.pack();
        table.setVisible(true);

        scheduler.setSchedulerStrategy(new SelectDistributionStrategy());
    }
}
