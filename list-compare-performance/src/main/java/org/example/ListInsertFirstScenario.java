package org.example;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListInsertFirstScenario implements Scenario {

  private final int numElements;
  private final Supplier<List<BigDecimal>> supplier;
  private List<BigDecimal> data;

  @Override
  public void prepare() {
    data = supplier.get();
  }

  @Override
  public void execute() {
    addElements();
  }

  private void addElements() {
    for (int i = 0; i < numElements; i++) {
      data.add(0, BigDecimal.valueOf(i));
    }
  }
}
