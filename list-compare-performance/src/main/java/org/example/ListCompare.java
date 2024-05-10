package org.example;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ListCompare {

  public static void compare() {
    System.out.println("Add Last");
    run(ListInsertScenario::new);

    System.out.println("Add First");
    run(ListInsertFirstScenario::new);

    System.out.println("Get");
    run(ListGetScenario::new);

    System.out.println("Contains");
    run(ListContainsScenario::new);

    System.out.println("Binary Search");
    run(ListBinarySearchScenario::new);

    System.out.println("Remove by index");
    run(ListRemoveByIndexScenario::new);

    System.out.println("Sort");
    run(ListSortScenario::new);
    run(ListRemoveFirstIndexScenario::new);
  }

  private static void run(
      final BiFunction<Integer, Supplier<List<BigDecimal>>, Scenario> scenarioGenerator) {
    System.out.println("  Time");
    run(ListCompare::runScenarioTime, scenarioGenerator);
  }

  private static void run(
      final Consumer<Scenario> consumer,
      final BiFunction<Integer, Supplier<List<BigDecimal>>, Scenario> scenarioGenerator) {
    System.out.println("    Array List");
    runScenario(ArrayList::new, consumer, scenarioGenerator);

    System.out.println("    Linked List");
    runScenario(LinkedList::new, consumer, scenarioGenerator);
  }

  private static void runScenario(
      final Supplier<List<BigDecimal>> listSupplier,
      final Consumer<Scenario> consumer,
      final BiFunction<Integer, Supplier<List<BigDecimal>>, Scenario> scenarioGenerator) {
    runScenarioWith(10, consumer, listSupplier, scenarioGenerator);
    runScenarioWith(100, consumer, listSupplier, scenarioGenerator);
    runScenarioWith(1_000, consumer, listSupplier, scenarioGenerator);
    runScenarioWith(10_000, consumer, listSupplier, scenarioGenerator);
    runScenarioWith(100_000, consumer, listSupplier, scenarioGenerator);
    runScenarioWith(1_000_000, consumer, listSupplier, scenarioGenerator);
  }

  private static void runScenarioWith(
      final int numElements,
      final Consumer<Scenario> consumer,
      final Supplier<List<BigDecimal>> listSupplier,
      final BiFunction<Integer, Supplier<List<BigDecimal>>, Scenario> scenarioGenerator) {
    System.out.print(
        "      " + NumberFormat.getNumberInstance().format(numElements) + " elements - ");
    consumer.accept(scenarioGenerator.apply(numElements, listSupplier));
  }

  private static void runScenarioTime(final Scenario scenario) {
    System.out.print(" Tempo: ");
    new TimeScenarioExecutor(scenario).execute();
  }
}
