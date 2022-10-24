package org.example;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryRunner {

  private static final int NON_POLLUTION_LIST_INITIAL_CAPACITY = 1_000_000;
  private static final int POLLUTION_LIST_INITIAL_CAPACITY = 100_000;
  private final List<List<String>> infiniteList = new ArrayList<>();
  private List<String> stringsList = new ArrayList<>();

  // Please use following VM arguments
  //-Xmx200m -XX:+UseSerialGC -XX:SurvivorRatio=1 -XX:NewRatio=1 -Xlog:gc*
  public static void main(String[] args) throws InterruptedException {
    OutOfMemoryRunner outOfMemoryRunner = new OutOfMemoryRunner();
    outOfMemoryRunner.doDemo();
  }

  private void doDemo() throws InterruptedException {
    Thread.sleep(10_000L);
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      stringsList = new ArrayList<>(NON_POLLUTION_LIST_INITIAL_CAPACITY);
      infiniteList.add(new ArrayList<>(POLLUTION_LIST_INITIAL_CAPACITY));
      Thread.sleep(1000L);
    }
  }
}
