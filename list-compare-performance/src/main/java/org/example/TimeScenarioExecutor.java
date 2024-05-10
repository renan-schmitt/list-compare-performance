package org.example;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.StopWatch;

@RequiredArgsConstructor
public class TimeScenarioExecutor {
  private final Scenario scenario;

  public void execute() {
    execute(10);
  }

  public void execute(final int interactions) {
    final var stopWatch = new StopWatch();

    scenario.prepare();
    System.gc();

    stopWatch.start();

    for (int i = 0; i < interactions; i++) {

      scenario.execute();

      stopWatch.suspend();

      scenario.prepare();
      System.gc();

      stopWatch.resume();
    }

    stopWatch.stop();

    System.out.println(
        stopWatch.formatTime()
            + " ("
            + stopWatch.getTime()
            + " in millis, "
            + stopWatch.getNanoTime()
            + " in nano)");
  }
}
