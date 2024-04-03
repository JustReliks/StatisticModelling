package org.statisticModelling.schedulling_strategies;

import org.statisticModelling.ProgramScheduler;

public interface ProgramSchedulerStrategy {

    void doAction(ProgramScheduler scheduler) throws Exception;

}
