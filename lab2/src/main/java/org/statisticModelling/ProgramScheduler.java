package org.statisticModelling;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.statisticModelling.schedulling_strategies.ProgramSchedulerStrategy;
import org.statisticModelling.schedulling_strategies.SelectDistributionStrategy;

import java.io.BufferedReader;
import java.io.PrintStream;

@RequiredArgsConstructor
@Data
public class ProgramScheduler {


    private final PrintStream out;
    private final BufferedReader in;
    private boolean running;
    private ProgramSchedulerStrategy schedulerStrategy;

    @SneakyThrows
    public void start() {
        running = true;
        this.schedulerStrategy = new SelectDistributionStrategy();
        while (running) {
            try {
                schedulerStrategy.doAction(this);
            } catch (Exception e) {
                out.println("Что то пошло не так, завершение работы...");
                e.printStackTrace(out);
                running = false;
            }
        }
    }


}
