package org.example;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListBinarySearchScenario implements Scenario {
  private final int numElements;
  private final Supplier<List<BigDecimal>> supplier;
  private List<BigDecimal> data;

  @Override
  public void prepare() {
    if (data == null) {
      data = supplier.get();

      for (int i = 0; i < numElements; i++) {
        data.add(BigDecimal.valueOf(i));
      }
    }
  }

  @Override
  public void execute() {
    for (int i = 0; i < 10_000; i++) {
      final var result =
          Collections.binarySearch(
              data,
              BigDecimal.valueOf(
                  (int) Math.ceil((double) (i + 1) / ((double) 10_000 / numElements)) - 1));
    }
  }
}
