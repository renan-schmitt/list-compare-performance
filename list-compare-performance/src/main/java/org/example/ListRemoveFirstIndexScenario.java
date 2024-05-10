package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListRemoveFirstIndexScenario implements Scenario {
  private final int numElements;
  private final Supplier<List<BigDecimal>> supplier;
  private List<BigDecimal> data;

  @Override
  public void prepare() {
    if (data == null || data.isEmpty()) {
      data = supplier.get();

      for (int i = 0; i < numElements; i++) {
        data.add(BigDecimal.valueOf(i));
      }
    }
  }

  @Override
  public void execute() {
    while (!data.isEmpty()) {
      data.remove(0);
    }
  }
}
